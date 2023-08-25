package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.domain.reply.request.QnaReplyEditReqeust;
import bucheon.leafy.domain.reply.request.QnaReplySaveReqeust;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    public void remove(Long qnaReplyId, Long userId) { qnareplyMapper.deleteByQnaReplyId(qnaReplyId, userId); }
    public void write(QnaReplySaveReqeust qnaReplySaveReqeust) { qnareplyMapper.save(qnaReplySaveReqeust); }
    public QnaReplyResponse getRead(Long qnaCommentId){  return qnareplyMapper.findByQnaCommentId( qnaCommentId);}
    public void modify(Long qnaId,QnaReplyEditReqeust qnaReplyEditReqeust) { qnareplyMapper.edit(qnaId, qnaReplyEditReqeust); }
}