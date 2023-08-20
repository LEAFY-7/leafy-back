package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.qna.QnaDto;
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
    public void remove(Long qnaReplyId, Long userId) { qnareplyMapper.deleteByQnaReplyId(qnaReplyId, userId); }
    public void write(QnaReplyDto qnaReplyDto) { qnareplyMapper.save(qnaReplyDto); }
    public QnaReplyDto getRead(Long qnaCommentId){  return qnareplyMapper.findByQnaCommentId( qnaCommentId);}
    public void modify(QnaReplyDto qnaReplyDto) { qnareplyMapper.edit(qnaReplyDto); }
}