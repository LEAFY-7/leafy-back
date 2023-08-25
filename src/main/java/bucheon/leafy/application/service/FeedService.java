package bucheon.leafy.application.service;

import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.controller.response.FeedByIdResponse;
import bucheon.leafy.application.mapper.FeedImageMapper;
import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.mapper.FeedTagMapper;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
import bucheon.leafy.domain.feed.response.*;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.PopularTagResponse.PopularTagInformation;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bucheon.leafy.path.S3Path.FEED_PATH;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final ImageComponent imageComponent;
    private final FeedImageMapper feedImageMapper;
    private final FeedTagMapper feedTagMapper;

    private String imagePath = FEED_PATH;

    // 피드 리스트 조회
    public ScrollResponse getMainFeedsWhenNotLogin(ScrollRequest scrollRequest) {
        LinkedList<FeedResponse> feeds = feedMapper.findFeedList(scrollRequest);
        ScrollRequest next = getNextKey(feeds, scrollRequest);
        return ScrollResponse.of(next, feeds);
    }

    public ScrollResponse getMainFeedsWhenLogin(Long userId, ScrollRequest scrollRequest) {
        LinkedList<FeedResponse> feeds = feedMapper.findFeedsToFollowers(userId);

        List<Long> feedIds = feeds.stream()
                .map(FeedResponse::getFeedId)
                .collect(Collectors.toList());

        Integer nextKeySize = scrollRequest.size - feeds.size();

        LinkedList<FeedResponse> notFollowersFeeds = feedMapper.findFeedsNotInFollowersFeeds(feedIds, nextKeySize);
        feeds.addAll(notFollowersFeeds);

        ScrollRequest next = getNextKey(feeds, scrollRequest);
        return ScrollResponse.of(next, feeds);
    }


    public ScrollResponse getFeeds(ScrollRequest scrollRequest) {
        if(scrollRequest.hasKey()){
            LinkedList<FeedResponse> responseList = feedMapper.findFeedList(scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(responseList, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, responseList);
        } else {
            LinkedList<FeedResponse> responseList = feedMapper.findFeedListFirst(scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(responseList, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, responseList);
        }
    }

    private ScrollRequest getNextKey(LinkedList<FeedResponse> responseList, ScrollRequest scrollRequest){
        if(responseList.size() < ScrollRequest.size){
            return scrollRequest.next(ScrollRequest.NONE_KEY);
        } else {
            long nextKey = responseList.getLast().getFeedId();
            return scrollRequest.next(nextKey);
        }
    }

    // 피드 상세 조회
    public FeedByIdResponse getFeedById(Long feedId) {
        FeedByIdResponse response = FeedByIdResponse.builder()
                .feedResponse(Optional.of(feedMapper.findFeedById(feedId)).orElseThrow(FeedNotFoundException::new))
                .tagResponseList(findTagList(feedId))
                .feedImageResponseList(getFeedImages(feedId)).build();

        Long userId = response.getFeedResponse().getUserId();
        Optional<User> user = Optional.of(userRepository.findById(userId)).orElseThrow(UserNotFoundException::new);
        response.getFeedResponse().setUserName(user.map(User::getName).orElseThrow(UserNotFoundException::new));
        return response;
    }

    // 피드 등록
    public void saveFeed(Long userId, FeedRequest request) throws IOException {
        feedMapper.saveFeed(userId, request);
        Long feedId = request.getFeedId();
        saveFeedTags(feedId, request.getTagList());
        saveFeedImages(feedId, request.getImageList());
        initFeedLike(feedId);
    }

    // 피드 수정
    public void updateFeed(Long feedId, Long userId, FeedRequest request) throws IOException {
        if( feedMapper.editFeed(feedId, userId, request) == 1 ) {
            deleteFeedTags(feedId);
            saveFeedTags(feedId, request.getTagList());
            deleteFeedImages(feedId);
            saveFeedImages(feedId, request.getImageList());
        } else {
            throw new FeedDataAccessException();
        }
    }

    // 피드 삭제
    public void deleteFeed(Long feedId, Long userId) {
        if( feedMapper.deleteFeed(feedId, userId) != 1 ) throw new FeedDataAccessException();
    }

    // 피드 태그 조회
    public List<FeedTagResponse> findTagList(Long feedId) {
        return feedTagMapper.findFeedTagList(feedId);
    }

    // 피드 이미지 조회
    public List<FeedImageResponse> getFeedImages(Long feedId) {
        List<FeedImageResponse> responseList =  feedImageMapper.findFeedImageList(feedId);

        for(FeedImageResponse response : responseList) {
            String imageUrl = imageComponent.getImageUrl(FEED_PATH, response.getImageName());
            response.setImageUrl(imageUrl);
        }

        return responseList;
    }

    // 피드 태그 저장
    public void saveFeedTags(Long feedId, List<FeedTagRequest> saveFeedList) {
        feedTagMapper.saveFeedTag(feedId, saveFeedList);
    }

    // 피드 이미지 저장
    public void saveFeedImages(Long feedId, List<MultipartFile> imageList) throws IOException {
        List<FeedImageRequest> requestList = new ArrayList<>();
        List<String> imageNameList = imageComponent.uploadImages(imagePath, imageList);

        for(MultipartFile image : imageList) {
            BufferedImage inputImage = ImageIO.read(image.getInputStream());
            int imageHeight = inputImage.getHeight();

            for(String imageName : imageNameList) {
                FeedImageRequest request = FeedImageRequest.builder().imageName(imageName).imageHeight(imageHeight).build();
                requestList.add(request);
            }
        }

        feedImageMapper.saveFeedImage(feedId, requestList);
    }

    // 피드 태그 삭제
    public void deleteFeedTags(Long feedId) {
        feedTagMapper.deleteFeedTag(feedId);
    }

    // 피드 이미지 삭제
    public void deleteFeedImages(Long feedId) {
        List<FeedImageResponse> responseList = feedImageMapper.findFeedImageList(feedId);
        List<String> imageNameList = new ArrayList<>();

        for(FeedImageResponse response : responseList) {
            imageNameList.add(response.getImageName());
        }

        imageComponent.deleteImages(FEED_PATH, imageNameList);
        feedImageMapper.deleteFeedImage(feedId);
    }

    // 피드 좋아요 초기화
    public void initFeedLike(Long feedId) {
        FeedLikeCount likeCount = FeedLikeCount.builder().likeCount(0L).build();
        Optional<Feed> feed = feedRepository.findById(feedId);

        likeCount.initFeed(feed.orElseThrow());

        feedLikeRepository.save(likeCount);
    }

    public List<FeedMonthlyResponse> getCountGroupByMonthly(Long userId) {
        List<FeedMonthlyInformation> feedMonthlyResponse = feedRepository.groupByMonthlyCountByUserId(userId);

        return feedMonthlyResponse.stream()
                .map(FeedMonthlyResponse::of)
                .collect(Collectors.toList());
    }

    public List<PopularTagResponse> getPopularTags() {
        List<PopularTagInformation> popularTags = feedRepository.getPopular10TagsInTop100Feeds();
        return popularTags.stream()
                .map(PopularTagResponse::of)
                .collect(Collectors.toList());
    }

    public Integer getCountByUserId(Long userId) {
        User user = userRepository.findWithFeedsById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<Feed> feeds = user.getFeeds();

        if (feeds == null) return 0;
        return feeds.size();
    }
}
