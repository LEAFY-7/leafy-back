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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<FeedResponse> getFeeds() {
        return feedMapper.findFeedListFirst();
    }

    public List<FeedResponse> getFeeds(Long feedId) {
        return feedMapper.findFeedListScroll(feedId);
    }

    public FeedResponse getFeedById(Long feedId) {
        return Optional.of(feedMapper.findFeedById(feedId)).orElseThrow(FeedNotFoundException::new);
    }

    public Long saveFeed(Long userId, FeedRequest request) {
        request.setUserId(userId);
        feedMapper.saveFeed(request);

        return request.getFeedId();
    }

    public Long updateFeed(Long userId, Long feedId, FeedRequest request) {
        request.setUserId(userId);
        request.setFeedId(feedId);
        if( feedMapper.editFeed(request) == 1 ) {
            return feedId;
        } else {
            throw new FeedDataAccessException();
        }
    }

    public boolean deleteFeed(Long feedId) { return feedMapper.softDeleteFeed(feedId) == 1; }

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

    public void deleteFeedImage(Long feedId, String imageName) {
        imageComponent.deleteImage(imagePath, imageName);

        feedImageMapper.deleteImage(feedId, imageName);
    }
}
