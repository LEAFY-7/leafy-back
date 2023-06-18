package bucheon.leafy.domain.qnareply.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;


import javax.transaction.Transactional;
import java.util.List;

public interface QnaReplyService {
    int getCount(Integer cid) throws Exception;
    int write(QnaReplyDto qnaReplyDto) throws Exception;
    int remove(Integer cid, Integer bno, String user_id) throws Exception;
    List<QnaReplyDto> getList(Integer cid) throws Exception;
    QnaReplyDto read(Integer cid) throws Exception;
    int modify(QnaReplyDto qnaReplyDto) throws Exception;
}
