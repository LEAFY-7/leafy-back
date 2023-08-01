package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedReplyMapper;
import bucheon.leafy.domain.feed.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.response.FeedReplyResponse;
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
public class FeedReplyService {

    private final FeedReplyMapper feedReplyMapper;

    public List<FeedReplyResponse> getReplies(Long feedId, Long commentId, ScrollRequest scrollRequest) {
        if (scrollRequest.hasKey()) {
            return feedReplyMapper.findReplyListScroll(feedId, commentId, scrollRequest);
        } else if (scrollRequest.getKey() == null) {
            return feedReplyMapper.findReplyListFirst(feedId, commentId, scrollRequest);
        } else {
            return null;
        }
    }

    public FeedReplyResponse findReplyById(Long replyId) {
        return Optional.of(feedReplyMapper.findReplyById(replyId)).orElseThrow(FeedCommentNotFoundException::new);
    }

    public Long saveReply(Long userId, Long feedId, Long commentId, FeedReplyRequest request) {
        feedReplyMapper.saveReply(userId, feedId, commentId, request);

        return request.getFeedReplyId();
    }

    public Map<String, Object> updateReply(Long userId, Long feedId, Long commentId, Long replyId, FeedReplyRequest request) {
        if( feedReplyMapper.editReply(userId, feedId, commentId, replyId, request) == 1 ) {
            FeedReplyResponse response = feedReplyMapper.findReplyById(commentId);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("data", response);
            responseMap.put("message", "대댓글 수정 완료");
            return responseMap;
        } else {
            throw new FeedCommentDataAccessException();
        }
    }

    public String deleteReply(Long userId, Long feedId, Long commentId, Long replyId) {
        if( feedReplyMapper.deleteReply(userId, feedId, commentId, replyId) == 1 ) {
            return "대댓글 삭제 완료";
        } else {
            throw new FeedCommentDataAccessException();
        }
    }
}