package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query(value = "SELECT YEAR(created_at) AS year, MONTH(created_at) AS month, COUNT(feed_id) AS count " +
            "FROM Feed " +
            "WHERE created_at >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) " +
            "AND user_id = :userId " +
            "GROUP BY year, month",
            nativeQuery = true)
    List<FeedMonthlyInformation> groupByMonthlyCountByUserId(@Param("userId") Long userId);


}