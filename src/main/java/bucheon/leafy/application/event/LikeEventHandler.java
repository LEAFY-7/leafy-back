package bucheon.leafy.application.event;

import bucheon.leafy.application.event.request.LikeCancelEvent;
import bucheon.leafy.application.event.request.LikeEvent;
import bucheon.leafy.application.repository.*;
import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedlikeinfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeEventHandler {

    private final UserRepository userRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeInfoRepository feedLikeInfoRepository;
    private final FeedRepository feedRepository;
    private final AlarmRepository alarmRepository;

    @Async
    @Transactional
    @EventListener(value = LikeEvent.class)
    public void increaseLikeCount(LikeEvent likeEvent) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(likeEvent.getFeedId())
                .orElseThrow(FeedNotFoundException::new);
        feedLikeCount.like();
    }

    @Async
    @Transactional
    @EventListener(value = LikeEvent.class)
    public void saveLikeInfo(LikeEvent likeEvent) {
        User user = userRepository.findById(likeEvent.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(likeEvent.getFeedId())
                .orElseThrow(FeedNotFoundException::new);

        FeedLikeInfo userLike = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(userLike);
    }

    @Async
    @Transactional
    @EventListener(value = LikeEvent.class)
    public void saveFeedLikeAlarm(LikeEvent likeEvent){
        User user = userRepository.findById(likeEvent.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (user.getIsAllNotifications()) {
            Alarm alarm = Alarm.of(user, AlarmType.FEED_LIKE, likeEvent.getFeedId());
            alarmRepository.save(alarm);
        }
    }

    @Async
    @Transactional
    @EventListener(value = LikeCancelEvent.class)
    public void decreaseLikeCount(LikeCancelEvent likeCancelEvent) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(likeCancelEvent.getFeedId())
                .orElseThrow(FeedNotFoundException::new);
        feedLikeCount.likeCancel();
    }

    @Async
    @Transactional
    @EventListener(value = LikeCancelEvent.class)
    public void deleteLikeInfo(LikeCancelEvent likeCancelEvent) {
        FeedLikeInfo userLike = feedLikeInfoRepository.findByUserIdAndFeedId(likeCancelEvent.getUserId(), likeCancelEvent.getFeedId())
                .orElseThrow(UserLikeNotFoundException::new);
        feedLikeInfoRepository.delete(userLike);
    }

}
