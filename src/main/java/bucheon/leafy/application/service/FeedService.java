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
import bucheon.leafy.domain.feed.response.*;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.PopularTagResponse.PopularTagInformation;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.FeedAuthorResponse;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.FeedUserNotSameException;
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


    // 피드 리스트 조회
    public ScrollResponse getMainFeedsWhenNotLogin(ScrollRequest scrollRequest) {
        LinkedList<FeedResponse> feeds = feedMapper.findFeeds(scrollRequest);
        List<Long> feedIds = getFeedIds(feeds);
        List<FeedImageResponse> feedImages = getFeedImages(feedIds);

        injectFeedImages(feeds, feedImages);

        ScrollRequest next = getNextKey(feeds, scrollRequest);
        return ScrollResponse.of(next, feeds);
    }


    public ScrollResponse getMainFeedsWhenLogin(Long userId, ScrollRequest scrollRequest) {
        LinkedList<FeedResponse> feeds = feedMapper.findFeedsToFollowers(userId);

        List<Long> feedIds = getFeedIds(feeds);
        Integer nextKeySize = scrollRequest.size - feeds.size();

        LinkedList<FeedResponse> notFollowersFeeds = feedMapper.findFeedsNotInFollowersFeeds(feedIds, nextKeySize);
        feeds.addAll(notFollowersFeeds);
  
        List<FeedImageResponse> feedImages = getFeedImages(feedIds);
        injectFeedImages(feeds, feedImages);

        ScrollRequest next = getNextKey(feeds, scrollRequest);
        return ScrollResponse.of(next, feeds);
    }

    // getFeeds 에 findFeedsToFollowers 조회 데이터 not in 으로 필터링 추가 해야함
    public ScrollResponse getFeeds(ScrollRequest scrollRequest) {
        if(scrollRequest.hasKey()){
            LinkedList<FeedResponse> feedResponses = feedMapper.findFeedsFirst(scrollRequest);
            List<Long> feedIds = getFeedIds(feedResponses);

            List<FeedImageResponse> feedImages = getFeedImages(feedIds);
            injectFeedImages(feedResponses, feedImages);

            ScrollRequest nextScrollRequest = getNextKey(feedResponses, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, feedResponses);
        } else {
            LinkedList<FeedResponse> feedResponses = feedMapper.findFeeds(scrollRequest);
            List<Long> feedIds = getFeedIds(feedResponses);

            List<FeedImageResponse> feedImages = getFeedImages(feedIds);
            injectFeedImages(feedResponses, feedImages);

            ScrollRequest nextScrollRequest = getNextKey(feedResponses, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, feedResponses);
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
        FeedResponse feedResponse = feedMapper.findFeedById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        List<FeedTagResponse> tagList = findTags(feedId);
        List<FeedImageResponse> feedImages = getFeedImages(feedId);;
        FeedAuthorResponse feedAuthorResponse = feedMapper.findUserByFeedId(feedId);

        FeedByIdResponse feedByIdResponse = FeedByIdResponse.builder()
                .feedResponse(feedResponse)
                .feedAuthorResponse(feedAuthorResponse)
                .tagResponses(tagList)
                .build();

        feedByIdResponse.getFeedResponse().getFeedImages().addAll(feedImages);

        return feedByIdResponse;
    }

    // 피드 등록
    public void saveFeed(Long userId, FeedRequest request) throws IOException {
        if ( feedMapper.saveFeed(userId, request) > 0 ) {
            Long feedId = request.getFeedId();
            saveFeedTags(feedId, request.getTags());
            saveFeedImages(feedId, request.getImages());
            initFeedLike(feedId);
        } else {
            throw new FeedDataAccessException();
        }
    }

    // 피드 수정
    public void updateFeed(Long feedId, Long userId, FeedRequest request) throws IOException {
        if (feedMapper.editFeed(feedId, userId, request) == 1) {

            deleteFeedTags(feedId);
            saveFeedTags(feedId, request.getTags());
            deleteFeedImages(feedId);
            saveFeedImages(feedId, request.getImages());

        }
        else if (userId != feedMapper.findUserByFeedId(feedId).getUserId()) {
            throw new FeedUserNotSameException();
        }
        else {
            throw new FeedDataAccessException();
        }
    }

    // 피드 삭제
    public void deleteFeed(Long feedId, Long userId) {
        FeedAuthorResponse feedAuthorResponse = feedMapper.findUserByFeedId(feedId);

        if( userId != feedAuthorResponse.getUserId() )
            throw new FeedUserNotSameException();
        else {
            int i = feedMapper.deleteFeed(feedId, userId);
            if (i == 1) {
                deleteFeedImages(feedId);
            } else {
                throw new FeedDataAccessException();
            }
        }
    }

    // 피드 태그 조회
    public List<FeedTagResponse> findTags(Long feedId) {
        return feedTagMapper.findFeedTags(feedId);
    }


    // 피드 태그 저장
    public void saveFeedTags(Long feedId, List<String> saveFeedList) {
        if (saveFeedList != null){
            feedTagMapper.saveFeedTag(feedId, saveFeedList);
        }
    }

    // 피드 이미지 저장
    public void saveFeedImages(Long feedId, List<MultipartFile> imageList) throws IOException {
        if (imageList != null) {
            List<FeedImageRequest> requestList = new ArrayList<>();
            List<String> imageNameList = imageComponent.uploadImages(FEED_PATH, imageList);

            for (MultipartFile image : imageList) {
                BufferedImage inputImage = ImageIO.read(image.getInputStream());
                int imageHeight = inputImage.getHeight();

                for (String imageName : imageNameList) {
                    FeedImageRequest request = FeedImageRequest.builder().imageName(imageName).imageHeight(imageHeight).build();
                    requestList.add(request);
                }
            }

            feedImageMapper.saveFeedImage(feedId, requestList);
        }
    }

    // 피드 태그 삭제
    public void deleteFeedTags(Long feedId) {
        feedTagMapper.deleteFeedTag(feedId);
    }

    // 피드 이미지 삭제
    public void deleteFeedImages(Long feedId) {
        List<FeedImageResponse> feedImageResponses = feedImageMapper.findFeedImages(feedId);
        List<String> imageNameList = feedImageResponses.stream()
                .map(FeedImageResponse::getImage)
                .collect(Collectors.toList());

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

    public Long countByUserId(Long userId) {
        return userRepository.countFeedById(userId);
    }

    // 피드 이미지 조회
    public List<FeedImageResponse> getFeedImages(Long feedId) {
        return feedImageMapper.findFeedImages(feedId);
    }


    private List<FeedImageResponse> getFeedImages(List<Long> feedIds) {
        return feedImageMapper.findFeedImagesByFeedId(feedIds);
    }

    private List<Long> getFeedIds(LinkedList<FeedResponse> feeds) {
        return feeds.stream()
                .map(FeedResponse::getFeedId)
                .collect(Collectors.toList());
    }

    private void injectFeedImages(LinkedList<FeedResponse> feeds, List<FeedImageResponse> feedImages) {
        for (FeedResponse feed : feeds) {
            for (FeedImageResponse feedImage : feedImages) {
                if (feed.getFeedId() == feedImage.getFeedId()) {
                    feed.getFeedImages().add(feedImage);
                }
            }
        }

        feeds.stream().forEach(FeedResponse::insertDefaultImage);
    }
}
