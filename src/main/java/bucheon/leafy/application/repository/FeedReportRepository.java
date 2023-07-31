package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feedreport.FeedReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedReportRepository extends JpaRepository<FeedReport, Long> {

}
