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

    @Query(value = "SELECT ft.tag " +
            "FROM ( " +
            "SELECT f.*, flc.like_count " +
            "FROM feed f " +
            "INNER JOIN feed_like_count flc ON f.feed_id = flc.feed_id " +
            "WHERE f.created_at >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK) " +
            "ORDER BY flc.like_count DESC " +
            "LIMIT 100 " +
            ") AS top_100_feeds " +
            "INNER JOIN feed_Tag ft ON top_100_feeds.feed_id = ft.feed_id " +
            "GROUP BY ft.tag " +
            "ORDER BY count(*) DESC " +
            "LIMIT 10;",
            nativeQuery = true)
    List<String> getPopular10TagsInTop100Feeds();


}
