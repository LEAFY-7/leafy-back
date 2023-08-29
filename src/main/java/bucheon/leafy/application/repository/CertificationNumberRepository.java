package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.CertificationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CertificationNumberRepository extends JpaRepository<CertificationNumber, Long> {

    boolean existsByEmailAndNumberAndCreatedAtBetween(String email, String number, LocalDateTime startTime, LocalDateTime endTime);

    void deleteByEmail(String email);

}
