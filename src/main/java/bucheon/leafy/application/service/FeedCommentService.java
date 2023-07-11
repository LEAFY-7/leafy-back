package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedCommentMapper;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.exception.FeedCommentDataAccessException;
import bucheon.leafy.exception.FeedCommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedCommentService {

    private final FeedCommentMapper mapper;

    public List<FeedCommentResponse> getComments(Long feedId) {
        return mapper.findCommentList(feedId);
    }

    public FeedCommentResponse findCommentById(Long commentId) {
        return mapper.findCommentById(commentId).orElseThrow(FeedCommentNotFoundException::new);
    }

    public Long saveComment(Long feedId, FeedCommentRequest request) {
        request.setFeedId(feedId);
        return mapper.saveComment(request);
    }

    public Long updateComment(Long feedId, Long commentId, FeedCommentRequest request) {
        request.setFeedId(feedId);
        request.setCommentId(commentId);
        if( mapper.editComment(request) == 1 ) {
            return commentId;
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public void deleteComment(Long commentId) {
        mapper.deleteComment(commentId);
    }
}
