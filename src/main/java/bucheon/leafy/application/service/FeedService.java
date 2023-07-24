package bucheon.leafy.application.service;

import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.mapper.FeedImageMapper;
import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.mapper.FeedTagMapper;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.request.FeedTagRequest;
import bucheon.leafy.domain.feed.response.*;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation.FeedMonthlyResponse;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.path.S3Path;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static bucheon.leafy.domain.feed.response.PopularTagInformation.*;
import static bucheon.leafy.path.S3Path.FEED_PATH;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedRepository feedRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final ImageComponent imageComponent;
    private final FeedImageMapper feedImageMapper;
    private final FeedTagMapper feedTagMapper;

    private String imagePath = FEED_PATH;

    public ScrollResponse getFeeds(ScrollRequest scrollRequest) {
        if (scrollRequest.hasKey()) {
            List<FeedResponse> responseList = feedMapper.findFeedListScroll(scrollRequest);
            ScrollResponse scrollResponse = new ScrollResponse(scrollRequest, responseList);
            return scrollResponse;
        } else if (scrollRequest.getKey() == null) {
             feedMapper.findFeedListFirst(scrollRequest);
            return null;
        } else {
            return null;
        }
    }

    public FeedResponse getFeedById(Long feedId) {
        return Optional.of(feedMapper.findFeedById(feedId)).orElseThrow(FeedNotFoundException::new);
    }

    public List<FeedTagResponse> findTagList(Long feedId) {
        return feedTagMapper.findTagList(feedId);
    }

    public Long saveFeed(Long userId, FeedRequest request, List<String> tagList) {
        feedMapper.saveFeed(userId, request);
        Long feedId = request.getFeedId();
        saveFeedTag(feedId, tagList);
        initFeedLike(feedId);

        return feedId;
    }

    public void initFeedLike(Long feedId) {
        FeedLikeCount likeCount = FeedLikeCount.builder().likeCount(0L).build();
        Optional<Feed> feed = feedRepository.findById(feedId);

        likeCount.initFeed(feed.orElseThrow());

        feedLikeRepository.save(likeCount);
    }

    public void saveFeedTag(Long feedId, List<String> tagList) {
        List<FeedTagRequest> requestList = new ArrayList<>();

        for(String tag : tagList) {
            FeedTagRequest tagRequest = FeedTagRequest.builder().feedId(feedId).tag(tag).build();
            requestList.add(tagRequest);
        }

        feedTagMapper.saveTag(requestList);
    }

    public Map<String, Object> updateFeed(Long feedId, Long userId, FeedRequest request) {
        if( feedMapper.editFeed(feedId, userId, request) == 1 ) {
            FeedResponse response = feedMapper.findFeedById(feedId);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", response);
            responseMap.put("message", "피드 수정 완료");
            return responseMap;
        } else {
            throw new FeedDataAccessException();
        }
    }

    public String deleteFeed(Long feedId, Long userId) {
        if( feedMapper.deleteFeed(feedId, userId) == 1 ) {
            return "피드 삭제 완료";
        } else {
            throw new FeedDataAccessException();
        }
    }

    public List<FeedMonthlyResponse> getCountGroupByMonthly(Long userId) {
        List<FeedMonthlyInformation> feedMonthlyInformation = feedRepository.groupByMonthlyCountByUserId(userId);

        return feedMonthlyInformation.stream()
                .map(FeedMonthlyResponse::of)
                .collect(Collectors.toList());
    }

    public List<FeedImageResponse> getFeedImages(Long feedId) {

        List<FeedImageResponse> responseList =  feedImageMapper.findImageList(feedId);

        for(FeedImageResponse response : responseList) {

            String imageUrl = imageComponent.getImageUrl(FEED_PATH, response.getImageName());

            response.setImageUrl(imageUrl);
        }

        return responseList;
    }

    public void saveImage(Long feedId, List<MultipartFile> imageList) {
        List<FeedImageRequest> requestList = new ArrayList<>();

        List<String> imageNameList = imageComponent.uploadImage(FEED_PATH, imageList);

        for(String imageName : imageNameList) {
            FeedImageRequest request = FeedImageRequest.builder().imageName(imageName).build();

            requestList.add(request);
        }

        feedImageMapper.saveImage(feedId, requestList);
    }

    public String deleteFeedImage(Long feedId, Long imageId) {
        String imageName = feedImageMapper.findImage(imageId);

        imageComponent.deleteImage(FEED_PATH, imageName);

        if(feedImageMapper.deleteImage(feedId, imageId) == 1) {
            return "이미지 삭제 완료";
        }  else {
        throw new FeedDataAccessException();
        }
    }

    public List<PopularTagResponse> getPopularTags() {
        List<PopularTagInformation> popularTags = feedRepository.getPopular10TagsInTop100Feeds();
        return popularTags.stream()
                .map(PopularTagResponse::of)
                .collect(Collectors.toList());
    }
}
