package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedRepository feedRepository;

    public List<FeedResponse> getFeeds() {
        return mapper.findFeedListFirst();
    }

    public List<FeedResponse> getFeeds(Long feedId) {
        return mapper.findFeedListScroll(feedId);
    }

    public FeedResponse getFeedById(Long feedId) {
        return mapper.findFeedById(feedId).orElseThrow(FeedNotFoundException::new);
    }

    public Long saveFeed(FeedRequest request) {
        return mapper.saveFeed(request);
    }

    public Long updateFeed(Long feedId, FeedRequest request) {
        request.setFeed_id(feedId);
        if( mapper.editFeed(request) == 1 ) {
            return feedId;
        } else {
            throw new FeedDataAccessException();
        }
    }

    public boolean deleteFeed(Long feedId) { return mapper.softDeleteFeed(feedId) == 1; }

    public List<FeedMonthlyResponse> getCountGroupByMonthly(Long userId) {
        List<FeedMonthlyInformation> feedMonthlyInformation = feedRepository.groupByMonthlyCountByUserId(userId);

        return feedMonthlyInformation.stream()
                .map(FeedMonthlyResponse::of)
                .collect(Collectors.toList());
    }
}
