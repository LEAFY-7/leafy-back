package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.AlarmRepository;
import bucheon.leafy.application.repository.FollowRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.util.response.JpaPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static bucheon.leafy.domain.alarm.AlarmType.NEW_FOLLOW;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final AlarmRepository alarmRepository;

    // 나를 팔로우 한 사람들
    public JpaPageResponse getFollowers(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Page<Follow> followers = followRepository.findAllByFollowing(user, pageable);

        List<User> followUsers = getFollowersInfo( followers.getContent() );

        List<FollowersResponse> followersResponses = followUsers.stream()
                .map(FollowersResponse::of)
                .collect(Collectors.toList());

        return JpaPageResponse.of(followersResponses, pageable, followers.getTotalElements());
    }

    // 내가 팔로우 한 사람들
    public JpaPageResponse getFollowings(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Page<Follow> followings = followRepository.findAllByFollower(user, pageable);

        List<User> followUsers = getFollowingsInfo(followings.getContent());

        List<FollowersResponse> followersResponses = followUsers.stream()
                .map(FollowersResponse::of)
                .collect(Collectors.toList());

        return JpaPageResponse.of(followersResponses, pageable, followings.getTotalElements());
    }


    public void follow(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User followTarget = userRepository.findById(targetUserId)
                .orElseThrow(UserNotFoundException::new);

        Boolean exists = followRepository.existsByFollowerAndFollowing(user, followTarget);

        if (!exists) {
            Follow follow = Follow.of(user, followTarget);
            followRepository.save(follow);

            if (user.getIsAllNotifications()) {
                Alarm alarm = Alarm.of(followTarget, NEW_FOLLOW, follow.getId());
                alarmRepository.save(alarm);
            }
        }

    }

    public void unfollow(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User followTarget = userRepository.findById(targetUserId)
                .orElseThrow(UserNotFoundException::new);

        followRepository.findByFollowerAndFollowing(user, followTarget)
                .ifPresent(f -> followRepository.delete(f));
    }

    public Long getFollowerCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return followRepository.countByFollower(user);
    }

    public Long getFollowingCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return followRepository.countByFollowing(user);
    }


    // fetch 조인을 하기 위해서 id를 추출 ( N+1 문제 때문에 )
    private List<User> getFollowersInfo(List<Follow> followers) {
        List<Long> ids = followers.stream()
                .map(f -> f.getFollower().getId())
                .collect(Collectors.toList());

        return userRepository.findAllById(ids);
    }

    private List<User> getFollowingsInfo(List<Follow> followings) {
        List<Long> ids = followings.stream()
                .map(f -> f.getFollowing().getId())
                .collect(Collectors.toList());

        return userRepository.findAllById(ids);
    }

}
