package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedCommentMapper;
import bucheon.leafy.domain.feed.dto.request.FeedCommentRequest;
import bucheon.leafy.domain.feed.dto.response.FeedCommentResponse;
import bucheon.leafy.exception.FeedDataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedCommentService {

    private final FeedCommentMapper mapper;

    public List<FeedCommentResponse> getComments(Long feedId) {
        return mapper.findCommentList(feedId);
    }

    public Long saveComment(Long feedId, FeedCommentRequest request) {
        request.setFeed_id(feedId);
        return mapper.saveComment(request);
    }

    public Long updateComment(Long feedId, Long commentId, FeedCommentRequest request) {
        request.setFeed_id(feedId);
        request.setComment_id(commentId);
        if( mapper.editComment(request) == 1 ) {
            return commentId;
        } else {
            throw new FeedDataAccessException();
        }
    }

    public boolean deleteComment(Long feedId, Long commentId) {
        return mapper.softDeleteComment(feedId, commentId) == 1;
    }
}
