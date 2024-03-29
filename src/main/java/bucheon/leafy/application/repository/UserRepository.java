package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @EntityGraph( attributePaths = {"userImage", "userBackgroundImage"})
    Optional<User> findById(Long userId);

    @Override
    @EntityGraph( attributePaths = {"userImage", "userBackgroundImage"} )
    List<User> findAllById(Iterable<Long> userIds);

    @EntityGraph( attributePaths = {"userImage", "userBackgroundImage"})
    Optional<User> findByEmail(String email);

    Boolean existsByNickName(String nickName);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<User> findByEmailAndPhone(String email, String phone);

    @EntityGraph( attributePaths = {"userImage", "userBackgroundImage"} )
    Optional<User> findByFeedsId(Long feedId);

    @EntityGraph( attributePaths = {"userImage", "userBackgroundImage", "feeds"} )
    Optional<User> findWithFeedsById(Long userId);

    Optional<User> findByNameAndPhone(String name, String phone);

    Optional<User> findByProviderId(String username);

    @Query("select count(f) from User u left join u.feeds f where u.id = :userId")
    Long countFeedById(Long userId);
}
