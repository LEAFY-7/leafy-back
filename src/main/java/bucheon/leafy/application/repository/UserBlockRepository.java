package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.userblock.UserBlock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

    @EntityGraph(attributePaths = "blockUser")
    List<UserBlock> findByUser(User user, Pageable pageable);
    void deleteByUserAndBlockUser(User user, User blockUser);

}
