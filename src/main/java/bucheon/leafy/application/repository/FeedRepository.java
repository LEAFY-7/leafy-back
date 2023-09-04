package bucheon.leafy.application.repository;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feed.response.PopularTagResponse.PopularTagInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static bucheon.leafy.domain.feed.response.FeedMonthlyResponse.FeedMonthlyInformation;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query(value = "SELECT YEAR(created_at) AS year, MONTH(created_at) AS month, COUNT(feed_id) AS count " +
            "FROM feed " +
            "WHERE created_at >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) " +
            "AND user_id = :userId " +
            "GROUP BY year, month",
            nativeQuery = true)
    List<FeedMonthlyInformation> groupByMonthlyCountByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT ft.tag as tag, count(ft.tag) as count " +
            "FROM ( " +
            "SELECT f.*, flc.like_count " +
            "FROM feed f " +
            "INNER JOIN feed_like_count flc ON f.feed_id = flc.feed_id " +
            "WHERE f.created_at >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK) " +
            "ORDER BY flc.like_count DESC " +
            "LIMIT 100 " +
            ") AS top_100_feeds " +
            "INNER JOIN feed_tag ft ON top_100_feeds.feed_id = ft.feed_id " +
            "GROUP BY ft.tag " +
            "ORDER BY count(*) DESC " +
            "LIMIT 10;",
            nativeQuery = true)
    List<PopularTagInformation> getPopular10TagsInTop100Feeds();

    @Query(value = "SELECT new bucheon.leafy.domain.feed.response.FeedResponse( " +
            "f.id, f.title, f.content, f.species, f.nickname, f.temperature, " +
            "f.humidity, f.waterAmount, f.wateringPeriod, f.feedType, f.createdAt, " +
            "f.modifiedAt, u.id, u.nickName, ui.image, flc.likeCount " +
            ") FROM User u " +
            "INNER JOIN u.feeds f " +
            "INNER JOIN f.feedLikeCount flc " +
            "LEFT JOIN u.userImage ui " +
            "WHERE f IN :feeds")
    List<FeedResponse> findAllFeedWithUserIdByFeedIn(@Param("feeds") List<Feed> feeds);

}
