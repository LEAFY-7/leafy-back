package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedLikeService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeInfoRepository feedLikeInfoRepository;

    public void saveLike(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        increaseLikeCount(feed);
        saveLikeInfo(user, feed);
    }

    public void deleteLike(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        decreaseLikeCount(feed);
        deleteLikeInfo(user, feed);
    }

    public void increaseLikeCount(Feed feed) {
        Long likeCount = feed.getFeedLikeCount().getLikeCount();
        FeedLikeCount feedLikeCount = FeedLikeCount.of(likeCount);
        feedLikeCount.like();
        feedLikeRepository.save(feedLikeCount);
    }

    public void decreaseLikeCount(Feed feed) {
        Long likeCount = feed.getFeedLikeCount().getLikeCount();
        FeedLikeCount feedLikeCount = FeedLikeCount.of(likeCount);
        feedLikeCount.likeCancel();
        feedLikeRepository.save(feedLikeCount);
    }

    public void saveLikeInfo(User user, Feed feed) {
        FeedLikeInfo userLike = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(userLike);
    }

    public void deleteLikeInfo(User user, Feed feed) {
        FeedLikeInfo userLike = feedLikeInfoRepository.findByUserAndFeed(user, feed)
                .orElseThrow(UserLikeNotFoundException::new);
        feedLikeInfoRepository.delete(userLike);
    }

}
