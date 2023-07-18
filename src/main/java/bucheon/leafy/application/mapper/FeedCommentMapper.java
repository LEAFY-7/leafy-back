package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FeedCommentMapper {

    List<FeedCommentResponse> findCommentListFirst(@Param("feedId") Long feedId, ScrollRequest scrollRequest);

    List<FeedCommentResponse> findCommentListScroll(@Param("feedId") Long feedId, ScrollRequest scrollRequest);

    FeedCommentResponse findCommentById(Long commentId);

    Long saveComment(@Param("userId") Long userId, @Param("feedId") Long feedId, FeedCommentRequest request);

    int editComment(@Param("commentId") Long commentId, @Param("userId") Long userId, @Param("feedId") Long feedId, FeedCommentRequest request);

    void deleteAllComments();

    int deleteComment(Long commentId, Long userId, Long feedId);
}
