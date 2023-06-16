package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.FeedComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {

    public List<FeedComment> findCommentList();

    public void saveComment(FeedComment feedComment);

    public void editComment(FeedComment feedComment);

    public void hardDeleteComment();
    public int softDeleteComment(Long id);

}
