package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.Feed;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
@Mapper
public interface FeedMapper {

    public List<Feed> findFeedListFirst();

    public List<Feed> findFeedListScroll(Long id);

    // 사용자가 특정 feed_id로 조회했을 시 해당 feed가 없을수도 있기때문에 optional 사용
    public Feed findFeedById(Long id);

    public Long saveFeed(Feed feed);

    public void editFeed(Feed feed);

    public void hardDeleteFeed(Long id);
    public int softDeleteFeed(Long id);
}
