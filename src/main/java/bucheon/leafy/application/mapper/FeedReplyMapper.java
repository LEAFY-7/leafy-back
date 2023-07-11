package bucheon.leafy.application.mapper;

<<<<<<< HEAD
import bucheon.leafy.domain.feed.dto.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.dto.response.FeedReplyResponse;
=======
import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
>>>>>>> d09b628b834277ae4f8b6d50286d36f4bfa02928
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface FeedReplyMapper {

    List<FeedReplyResponse> findReplyList(Long commentId);

    Optional<FeedReplyResponse> findReplyById(Long replyId);

    Long saveReply(FeedReplyRequest request);

    int editReply(FeedReplyRequest request);
<<<<<<< HEAD
=======
    
>>>>>>> d09b628b834277ae4f8b6d50286d36f4bfa02928
    void deleteAllReplies();

    void deleteReply(Long replyId);
}
