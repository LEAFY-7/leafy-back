package bucheon.leafy.domain.qnareply.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;


import javax.transaction.Transactional;
import java.util.List;

public interface QnaReplyService {
    int write(QnaReplyDto qnaReplyDto) throws Exception;
    int remove(Integer rid, Integer reply_reply, String user_id) throws Exception;
    List<QnaReplyDto> getList(Integer rid) throws Exception;
    QnaReplyDto read(Integer reply_reply) throws Exception;
    int modify(QnaReplyDto qnaReplyDto) throws Exception;
}
