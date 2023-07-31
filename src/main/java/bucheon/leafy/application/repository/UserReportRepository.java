package bucheon.leafy.application.repository;

import bucheon.leafy.domain.userreport.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {

}
