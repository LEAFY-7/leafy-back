package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

}
