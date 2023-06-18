package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserInfoRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.userInfo.UserInfo;
import bucheon.leafy.exception.UserLikeNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public void saveLikeInfo(Long userId, Feed feed) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        UserInfo userLike = UserInfo.of(user, feed);

        userInfoRepository.save(userLike);
    }

    public void deleteLikeInfo(Long userId, Feed feed) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        UserInfo userLike = userInfoRepository.findByUserAndFeed(user, feed)
                .orElseThrow(UserLikeNotFoundException::new);

        userInfoRepository.delete(userLike);
    }

}
