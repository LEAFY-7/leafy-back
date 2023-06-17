package bucheon.leafy.domain.qnareply.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService {
    @Autowired
    private final QnaMapper qnaMapper ;
    @Autowired
    private final QnaReplyMapper qnaReplyMapper;

    @Override
    public int remove(Integer rid, Integer reply_reply, String user_id) throws Exception {
        int rowCnt = qnaMapper.updateCommentCnt(reply_reply, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
        rowCnt = qnaReplyMapper.delete(rid, user_id);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }
    @Override
    public int write(QnaReplyDto qnaReplyDto) throws Exception {
        qnaMapper.updateCommentCnt( qnaReplyDto.getReply_reply(),1);
        return qnaReplyMapper.insert(qnaReplyDto);
    }
    @Override
    public List<QnaReplyDto> getList(Integer rid) throws Exception {
        return qnaReplyMapper.selectAll(rid);
    }

    @Override
    public QnaReplyDto read(Integer rid) throws Exception {
        return qnaReplyMapper.select(rid);
    }
    @Override
    public int modify(QnaReplyDto qnaReplyDto) throws Exception {
        return qnaReplyMapper.update(qnaReplyDto);
    }
}
