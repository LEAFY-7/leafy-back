package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.UserBackgroundImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBackgroundImageRepository extends JpaRepository<UserBackgroundImage, Long> {

}
