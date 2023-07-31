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

    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "userImage")
    List<User> findAllWithUserImageByIdIn(List<Long> ids);

    @EntityGraph( attributePaths = {"userImage", "address", "userBackgroundImage"})
    Optional<User> findByNickName(String nickName);
}
