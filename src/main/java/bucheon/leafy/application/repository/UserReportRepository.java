package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.userreport.UserReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {

    @EntityGraph(attributePaths = "reportUser")
    List<UserReport> findByUser(User user, Pageable pageable);
    void deleteByUserAndReportUser(User user, User reportUser);

    Boolean existsByUserAndReportUser(User user, User reportUser);
}
