package bucheon.leafy.application.service;

import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.mapper.FeedImageMapper;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.path.S3Path;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedRepository feedRepository;
    private final ImageComponent imageComponent;
    private final FeedImageMapper feedImageMapper;

    private String imagePath = S3Path.FEED_PATH;

    public List<FeedResponse> getFeeds(ScrollRequest scrollRequest) {
        if (scrollRequest.hasKey()) {
            return feedMapper.findFeedListScroll(scrollRequest);
        } else if (scrollRequest.getKey() == null) {
            return feedMapper.findFeedListFirst(scrollRequest);
        } else {
            return null;
        }
    }

    public FeedResponse getFeedById(Long feedId) {
        return Optional.of(feedMapper.findFeedById(feedId)).orElseThrow(FeedNotFoundException::new);
    }

    public Long saveFeed(Long userId, FeedRequest request) {
        feedMapper.saveFeed(userId, request);

        return request.getFeedId();
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

    public List<String> getFeedImages(Long feedId) {
        List<String> imageUrlList = new ArrayList<>();

        List<String> imageNameList =  feedImageMapper.findImageList(feedId);

        for(String imageName : imageNameList) {

            String imageUrl = imageComponent.getImageUrl(imagePath, imageName);

            imageUrlList.add(imageUrl);
        }

        return imageUrlList;
    }

    public List<String> saveFeedImage(Long feedId, List<MultipartFile> imageList) {
        List<String> imageUrlList = new ArrayList<>();
        List<FeedImageRequest> requestList = new ArrayList<>();

        List<String> imageNameList = imageComponent.uploadImage(imagePath, imageList);

        for(String imageName : imageNameList) {
            FeedImageRequest request = FeedImageRequest.builder().feedId(feedId).imageName(imageName).build();

            requestList.add(request);

            String imageUrl = imageComponent.getImageUrl(imagePath, imageName);

            imageUrlList.add(imageUrl);
        }

        feedImageMapper.saveImage(requestList);

        return imageUrlList;
    }

    public String deleteFeedImage(Long feedId, String imageName) {
        imageComponent.deleteImage(imagePath, imageName);

        if(feedImageMapper.deleteImage(feedId, imageName) == 1) {
            return "이미지 삭제 완료";
        }  else {
        throw new FeedDataAccessException();
        }
    }

    public List<String> getPopularTags() {
        return feedRepository.getPopular10TagsInTop100Feeds();
    }
}
