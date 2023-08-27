package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.domain.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplySaveResponse;
import bucheon.leafy.exception.WriteFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    public void remove(Long qnaReplyId) { qnareplyMapper.deleteByQnaReplyId(qnaReplyId); }
    public QnaReplySaveResponse write(QnaReplySaveRequest qnaReplySaveRequest) {

        qnareplyMapper.save(qnaReplySaveRequest);

        if (qnareplyMapper.save(qnaReplySaveRequest) != 1) {
            throw new WriteFailedException();
        }
        QnaReplySaveResponse qnaReplyResponse = qnareplyMapper.saveResponse(qnaReplySaveRequest);

        return qnaReplyResponse;

    }
    public QnaReplyResponse getRead(Long qnaCommentId){  return qnareplyMapper.findByQnaCommentId( qnaCommentId);}
    public void modify(Long qnaReplyId,String comment) { qnareplyMapper.edit(qnaReplyId, comment); }
}