package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedReportRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feedreport.FeedReport;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedReportService {

    private final UserRepository userRepository;
    private final FeedReportRepository feedReportRepository;
    private final FeedRepository feedRepository;

    public List<FeedResponse> getReportedFeeds(Long userId, Pageable pageable) {
        List<FeedReport> feedReports = feedReportRepository.findByUserId(userId, pageable);

        List<Feed> feeds = feedReports.stream()
                .map(FeedReport::getFeed)
                .collect(Collectors.toList());

        return feedRepository.findAllFeedWithUserIdByFeedIn(feeds);
    }

    public void reportFeed(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        FeedReport feedReport = FeedReport.of(user, feed);
        feedReportRepository.save(feedReport);
    }

    public void reportCancelFeed(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        feedReportRepository.deleteByUserAndFeed(user, feed);
    }

}
