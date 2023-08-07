package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.CertificationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationNumberRepository extends JpaRepository<CertificationNumber, Long> {
}
