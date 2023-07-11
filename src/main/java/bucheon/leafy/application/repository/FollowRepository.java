package bucheon.leafy.application.repository;

import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFollower(User follower, Pageable pageable);
    List<Follow> findAllByFollowing(User following, Pageable pageable);

    Optional<Follow> findByFollowerAndFollowing(@Param("follower") User follower, @Param("following") User following);

    Long countByFollower(User follower);

    Long countByFollowing(User following);
}