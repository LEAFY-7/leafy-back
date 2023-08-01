package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedCommentMapper;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.exception.FeedCommentDataAccessException;
import bucheon.leafy.exception.FeedCommentNotFoundException;
import bucheon.leafy.util.request.ScrollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentMapper feedCommentMapper;

    public List<FeedCommentResponse> getComments(Long feedId, ScrollRequest scrollRequest) {
        if (scrollRequest.hasKey()) {
            return feedCommentMapper.findCommentListScroll(feedId, scrollRequest);
        } else if (scrollRequest.getKey() == null) {
            return feedCommentMapper.findCommentListFirst(feedId, scrollRequest);
        } else {
            return null;
        }
    }

    public FeedCommentResponse getCommentById(Long commentId) {
        return Optional.of(feedCommentMapper.findCommentById(commentId)).orElseThrow(FeedCommentNotFoundException::new);
    }

    public Long saveComment(Long userId, Long feedId, FeedCommentRequest request) {
        feedCommentMapper.saveComment(userId, feedId, request);

        return request.getFeedCommentId();
    }

    public FeedCommentResponse updateComment(Long userId, Long feedId, Long commentId, FeedCommentRequest request) {
        if( feedCommentMapper.editComment(userId, feedId, commentId, request) == 1 ) {
            FeedCommentResponse response = feedCommentMapper.findCommentById(commentId);
            return response;
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public String deleteComment(Long userId, Long feedId, Long commentId) {
        if( feedCommentMapper.deleteComment(userId, feedId, commentId) == 1 ) {
            return "댓글 삭제 완료";
        } else {
            throw new FeedCommentDataAccessException();
        }
    }
}
