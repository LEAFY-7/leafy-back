package bucheon.leafy.application.repository;

import bucheon.leafy.domain.userblock.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

}
