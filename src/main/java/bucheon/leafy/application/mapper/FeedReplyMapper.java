package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.feed.dto.request.FeedReplyRequest;
import bucheon.leafy.domain.feed.dto.response.FeedReplyResponse;

import java.util.List;

public interface FeedReplyMapper {

    List<FeedReplyResponse> findReplyList();

    Long saveReply(FeedReplyRequest request);

    int editReply(FeedReplyRequest request);

    void hardDeleteReply();

    int softDeleteReply(Long id);
}
