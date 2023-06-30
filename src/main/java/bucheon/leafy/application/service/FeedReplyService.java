package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedReplyMapper;
import bucheon.leafy.domain.feed.dto.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.dto.response.FeedReplyResponse;
import bucheon.leafy.exception.FeedCommentDataAccessException;
import bucheon.leafy.exception.FeedCommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedReplyService {

    private final FeedReplyMapper mapper;

    public List<FeedReplyResponse> getReplies(Long commentId) {
        return mapper.findReplyList(commentId);
    }

    public FeedReplyResponse findReplyById(Long replyId) {
        return mapper.findReplyById(replyId).orElseThrow(FeedCommentNotFoundException::new);
    }

    public Long saveReply(Long feedId, Long commentId, FeedReplyRequest request) {
        request.setFeedId(feedId);
        request.setCommentId(commentId);
        return mapper.saveReply(request);
    }

    public Long updateReply(Long feedId, Long commentId, Long replyId, FeedReplyRequest request) {
        request.setFeedId(feedId);
        request.setCommentId(commentId);
        request.setReplyId(replyId);
        if( mapper.editReply(request) == 1 ) {
            return replyId;
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public void deleteReply(Long replyId) {
        mapper.deleteReply(replyId);
    }
}
