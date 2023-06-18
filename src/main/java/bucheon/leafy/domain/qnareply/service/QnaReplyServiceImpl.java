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
    public int getCount(Integer cid) throws Exception {
        return qnaReplyMapper.count(cid);
    }
    @Override
    @Transactional()
    public int remove(Integer cid, Integer bno, String user_id) throws Exception {
        int rowCnt = qnaMapper.updateReplyCnt(bno, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
        rowCnt = qnaReplyMapper.delete(cid, user_id);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }
    @Override
    @Transactional
    public int write(QnaReplyDto qnaReplyDto) throws Exception {
        qnaMapper.updateReplyCnt( qnaReplyDto.getRid(),1);
        return qnaReplyMapper.insert(qnaReplyDto);
    }
    @Override
    public List<QnaReplyDto> getList(Integer cid) throws Exception {

        return qnaReplyMapper.selectAll(cid);
    }
    @Override
    public QnaReplyDto read(Integer cid) throws Exception {
        return qnaReplyMapper.select(cid);
    }
    @Override
    public int modify(QnaReplyDto qnaReplyDto) throws Exception {
        return qnaReplyMapper.update(qnaReplyDto);
    }
}
