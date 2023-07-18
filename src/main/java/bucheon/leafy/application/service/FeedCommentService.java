package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedCommentMapper;
import bucheon.leafy.domain.feed.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.response.FeedCommentResponse;
import bucheon.leafy.exception.FeedCommentDataAccessException;
import bucheon.leafy.exception.FeedCommentNotFoundException;
import bucheon.leafy.util.request.ScrollRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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

        return request.getCommentId();
    }

    public Map<String, Object> updateComment(Long commentId, Long userId, Long feedId, FeedCommentRequest request) {
        if( feedCommentMapper.editComment(commentId, feedId, userId, request) == 1 ) {
            FeedCommentResponse response = feedCommentMapper.findCommentById(commentId);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", response);
            responseMap.put("message", "댓글 수정 완료");
            return responseMap;
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public String deleteComment(Long commentId, Long userId, Long feedId) {
        if( feedCommentMapper.deleteComment(commentId, userId, feedId) == 1 ) {
            return "댓글 삭제 완료";
        } else {
            throw new FeedCommentDataAccessException();
        }
    }
}
