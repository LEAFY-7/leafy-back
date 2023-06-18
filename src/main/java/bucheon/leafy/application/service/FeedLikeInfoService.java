package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedLikeInfoService {

    private final UserRepository userRepository;
    private final FeedLikeInfoRepository feedLikeInfoRepository;

    public void saveLikeInfo(Long userId, Feed feed) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        FeedLikeInfo userLike = FeedLikeInfo.of(user, feed);

        feedLikeInfoRepository.save(userLike);
    }

    public void deleteLikeInfo(Long userId, Feed feed) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        FeedLikeInfo userLike = feedLikeInfoRepository.findByUserAndFeed(user, feed)
                .orElseThrow(UserLikeNotFoundException::new);

        feedLikeInfoRepository.delete(userLike);
    }

}
