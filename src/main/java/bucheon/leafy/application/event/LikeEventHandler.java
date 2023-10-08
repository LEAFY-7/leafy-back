package bucheon.leafy.application.event;

import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedlikeinfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class LikeEventHandler {

    private final UserRepository userRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeInfoRepository feedLikeInfoRepository;
    private final FeedRepository feedRepository;

    @Async
    @Transactional
    @TransactionalEventListener(value = LikeEvent.class)
    public void likeHandle(LikeEvent likeEvent) {
        increaseLikeCount(likeEvent.getFeedId());
    }

    @Async
    @Transactional
    @TransactionalEventListener(value = LikeEvent.class)
    public void likeInfoHandle(LikeEvent likeEvent) {
        saveLikeInfo(likeEvent.getUserId(), likeEvent.getFeedId());
    }

    @Async
    @Transactional
    @TransactionalEventListener(value = LikeCancelEvent.class)
    public void likeCancelHandle(LikeCancelEvent likeCancelEvent) {
        decreaseLikeCount(likeCancelEvent.getFeedId());
    }

    @Async
    @Transactional
    @TransactionalEventListener(value = LikeCancelEvent.class)
    public void likeInfoCancelHandle(LikeCancelEvent likeCancelEvent) {
        deleteLikeInfo(likeCancelEvent.getUserId(), likeCancelEvent.getFeedId());
    }

    public void increaseLikeCount(Long feedId) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(feedId)
                .orElseThrow(FeedNotFoundException::new);
        feedLikeCount.like();
    }

    public void decreaseLikeCount(Long feedId) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(feedId)
                .orElseThrow(FeedNotFoundException::new);
        feedLikeCount.likeCancel();
    }

    public void saveLikeInfo(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        FeedLikeInfo userLike = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(userLike);
    }

    public void deleteLikeInfo(Long userId, Long feedId) {
        FeedLikeInfo userLike = feedLikeInfoRepository.findByUserIdAndFeedId(userId, feedId)
                .orElseThrow(UserLikeNotFoundException::new);
        feedLikeInfoRepository.delete(userLike);
    }
}
