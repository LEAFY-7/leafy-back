package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedlikeinfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class FeedLikeService {

    private final UserRepository userRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeInfoRepository feedLikeInfoRepository;


    // 레코드에 직접적으로 락을 걸기 때문에 성능이 떨어질 수 있음
    // 추후에 saveLikeInfo 메서드를 다른 트랜잭션(컨트롤러 레이어)으로 변경을 하거나 낙관적 락으로 변경을 고려
    public void saveLike(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = increaseLikeCount(feedId);
        saveLikeInfo(user, feed);
    }


    public Feed increaseLikeCount(Long feedId) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(feedId)
                .orElseThrow(FeedNotFoundException::new);

        feedLikeCount.like();
        return feedLikeCount.getFeed();
    }


    public void deleteLike(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = decreaseLikeCount(feedId);
        deleteLikeInfo(user, feed);
    }


    public Feed decreaseLikeCount(Long feedId) {
        FeedLikeCount feedLikeCount = feedLikeRepository.findByFeedId(feedId)
                .orElseThrow(FeedNotFoundException::new);

        feedLikeCount.likeCancel();
        return feedLikeCount.getFeed();
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
