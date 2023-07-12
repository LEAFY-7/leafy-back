package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface FeedReplyMapper {

    List<FeedReplyResponse> findReplyList(Long commentId);

    Optional<FeedReplyResponse> findReplyById(Long replyId);

    Long saveReply(FeedReplyRequest request);

    int editReply(FeedReplyRequest request);

    void deleteAllReplies();

    void deleteReply(Long replyId);
}
