package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FollowRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FollowNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 나를 팔로우 한 사람들
    public List<FollowersResponse> getFollowers(User user, Pageable pageable) {
        List<Follow> followers = followRepository.findAllByFollowing(user, pageable);

        // fetch 조인을 하기 위해서 id를 추출 ( N+1 문제 때문에 )
        List<Long> ids = followers.stream()
                .map(f -> f.getFollower().getId()).collect(Collectors.toList());

        List<User> followUsers = userRepository.findAllWithUserImageByIdIn(ids);

        return followUsers.stream()
                .map(f -> FollowersResponse.of(f))
                .collect(Collectors.toList());
    }

    // 내가 팔로우 한 사람들
    public List<FollowersResponse> getFollowings(User user, Pageable pageable) {
        List<Follow> followers = followRepository.findAllByFollower(user, pageable);

        // fetch 조인을 하기 위해서 id를 추출 ( N+1 문제 때문에 )
        List<Long> ids = followers.stream()
                .map(f -> f.getFollowing().getId()).collect(Collectors.toList());

        List<User> followUsers = userRepository.findAllWithUserImageByIdIn(ids);

        return followUsers.stream()
                .map(f -> FollowersResponse.of(f))
                .collect(Collectors.toList());
    }

    public ResponseEntity follow(User user, Long userId) {
        User followTarget = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Follow follow = Follow.of(user, followTarget);
        followRepository.save(follow);

        return ResponseEntity.status(200).body("팔로우 성공");
    }

    public ResponseEntity unfollow(User user, Long userId) {
        User followTarget = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Follow follow = followRepository.findByFollowerAndFollowing(user, followTarget)
                .orElseThrow(FollowNotFoundException::new);

        followRepository.delete(follow);

        return ResponseEntity.status(200).body("언팔로우 성공");
    }
}
