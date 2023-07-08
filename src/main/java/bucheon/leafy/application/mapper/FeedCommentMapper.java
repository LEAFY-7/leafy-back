package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.dto.response.FeedCommentResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FeedCommentMapper {

    List<FeedCommentResponse> findCommentList(Long feedId);

    Optional<FeedCommentResponse> findCommentById(Long commentId);

    Long saveComment(FeedCommentRequest request);

    int editComment(FeedCommentRequest request);

    void deleteAllComments();

    void deleteComment(Long commentId);
}
