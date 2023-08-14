package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.reply.QnaReplyDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    public void remove(Long qnaReplyId, Long userId) { qnareplyMapper.delete(qnaReplyId, userId); }
    public void write(QnaReplyDto qnaReplyDto) { qnareplyMapper.insert(qnaReplyDto); }
    public void modify(QnaReplyDto qnaReplyDto) { qnareplyMapper.update(qnaReplyDto); }
    public List<QnaReplyDto> getList(Long qnaCommentId) {
        return qnareplyMapper.selectAll(qnaCommentId);
    }

}