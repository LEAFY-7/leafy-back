package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
