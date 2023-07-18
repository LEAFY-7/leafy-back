package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedReplyMapper {

    List<FeedReplyResponse> findReplyListFirst(@Param("feedId") Long feedId, @Param("commentId") Long commentId, ScrollRequest scrollRequest);

    List<FeedReplyResponse> findReplyListScroll(@Param("feedId") Long feedId, @Param("commentId") Long commentId, ScrollRequest scrollRequest);

    FeedReplyResponse findReplyById(Long replyId);

    Long saveReply(@Param("userId") Long userId, @Param("feedId") Long feedId, @Param("commentId") Long commentId, FeedReplyRequest request);

    int editReply(@Param("replyId") Long replyId, @Param("userId") Long userId, @Param("feedId") Long feedId, @Param("commentId") Long commentId, FeedReplyRequest request);

    void deleteAllReplies();

    int deleteReply(@Param("replyId") Long replyId, @Param("userId") Long userId, @Param("feedId") Long feedId, @Param("commentId") Long commentId);
}
