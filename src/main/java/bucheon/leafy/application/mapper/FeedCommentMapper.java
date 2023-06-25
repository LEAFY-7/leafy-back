package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.FeedComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {

    List<FeedComment> findCommentList();

    void saveComment(FeedComment feedComment);

    void editComment(FeedComment feedComment);

    void hardDeleteComment();

    int softDeleteComment(Long id);

}
