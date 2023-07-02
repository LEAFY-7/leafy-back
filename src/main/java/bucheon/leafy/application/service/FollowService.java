package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FollowRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.FollowNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static bucheon.leafy.exception.enums.ExceptionKey.FOLLOW;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 나를 팔로우 한 사람들
    public List<FollowersResponse> getFollowers(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<Follow> followers = followRepository.findAllByFollowing(user, pageable);

        List<Long> ids = getFollowersId(followers);

        List<User> followUsers = userRepository.findAllWithUserImageByIdIn(ids);

        return followUsers.stream()
                .map(FollowersResponse::of)
                .collect(Collectors.toList());
    }

    // 내가 팔로우 한 사람들
    public List<FollowersResponse> getFollowings(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<Follow> followings = followRepository.findAllByFollower(user, pageable);

        List<Long> ids = getFollowingsId(followings);

        List<User> followUsers = userRepository.findAllWithUserImageByIdIn(ids);

        return followUsers.stream()
                .map(FollowersResponse::of)
                .collect(Collectors.toList());
    }


    public void follow(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User followTarget = userRepository.findById(targetUserId)
                .orElseThrow(UserNotFoundException::new);

        followRepository.findByFollowerAndFollowing(user, followTarget)
                .ifPresent(f -> {
                    throw new ExistException(FOLLOW);
                });

        Follow follow = Follow.of(user, followTarget);

        followRepository.save(follow);
    }

    public void unfollow(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        User followTarget = userRepository.findById(targetUserId)
                .orElseThrow(UserNotFoundException::new);

        Follow follow = followRepository.findByFollowerAndFollowing(user, followTarget)
                .orElseThrow(FollowNotFoundException::new);

        followRepository.delete(follow);
    }

    // fetch 조인을 하기 위해서 id를 추출 ( N+1 문제 때문에 )

    private List<Long> getFollowersId(List<Follow> followers) {
        return followers.stream()
                .map(f -> f.getFollower().getId())
                .collect(Collectors.toList());
    }

    private List<Long> getFollowingsId(List<Follow> followings) {
        return followings.stream()
                .map(f -> f.getFollowing().getId())
                .collect(Collectors.toList());
    }

}
