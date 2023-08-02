package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FeedReplyMapper {

    List<FeedReplyResponse> findReplyListFirst(@Param("feedId") Long feedId, @Param("feedCommentId") Long feedCommentId, ScrollRequest scrollRequest);

    List<FeedReplyResponse> findReplyListScroll(@Param("feedId") Long feedId, @Param("feedCommentId") Long feedCommentId, ScrollRequest scrollRequest);

    FeedReplyResponse findReplyById(Long feedReplyId);

    Long saveReply(@Param("userId") Long userId, @Param("feedId") Long feedId, @Param("feedCommentId") Long feedCommentId, FeedReplyRequest request);

    int editReply(@Param("userId") Long userId, @Param("feedId") Long feedId, @Param("feedCommentId") Long feedCommentId, @Param("feedReplyId") Long feedReplyId, FeedReplyRequest request);

    void deleteAllReplies();

    int deleteReply(Long userId, Long feedId, Long feedCommentId, Long feedReplyId);
}
