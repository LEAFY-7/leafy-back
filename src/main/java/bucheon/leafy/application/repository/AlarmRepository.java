package bucheon.leafy.application.repository;

import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Optional<Alarm> findByUser(User user);

}
