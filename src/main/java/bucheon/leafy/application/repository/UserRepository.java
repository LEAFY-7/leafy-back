package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"})
    Optional<User> findById(Long userId);

    @Override
    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"} )
    List<User> findAllById(Iterable<Long> userIds);

    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"})
    Optional<User> findByEmail(String email);

    Boolean existsByNickName(String nickName);

    Boolean existsByEmail(String email);

    Optional<User> findByEmailAndPhone(String email, String phone);

    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"} )
    Optional<User> findByFeedsId(Long feedId);
}
