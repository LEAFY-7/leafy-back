package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedCommentMapper;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.exception.FeedCommentDataAccessException;
import bucheon.leafy.exception.FeedCommentNotFoundException;
import bucheon.leafy.exception.FeedDataAccessException;
import bucheon.leafy.util.request.ScrollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedCommentService {

    private final FeedCommentMapper mapper;

    public List<FeedCommentResponse> getComments(ScrollRequest scrollRequest) {
        return mapper.findCommentList(feedId);
    }

    public FeedCommentResponse getCommentById(Long commentId) {
        return mapper.findCommentById(commentId).orElseThrow(FeedCommentNotFoundException::new);
    }

    public Long saveComment(Long feedId, FeedCommentRequest request) {
        request.setFeedId(feedId);
        return mapper.saveComment(request);
    }

    public String updateComment(Long commentId, FeedCommentRequest request) {
        request.setCommentId(commentId);
        if( mapper.editComment(request) == 1 ) {
            return "댓글 수정 완료";
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public String deleteComment(Long commentId) {
        if( mapper.deleteComment(feedId); == 1 ) {
            return "피드 삭제 완료";
        } else {
            throw new FeedDataAccessException();
        }
    }
}
