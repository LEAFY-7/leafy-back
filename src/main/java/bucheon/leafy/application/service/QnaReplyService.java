package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.reply.request.QnaReplyEditReqeust;
import bucheon.leafy.domain.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplySaveResponse;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.WriteFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    public void remove(Long qnaReplyId,AuthUser user) {
        Long userId = user.getUserId();

        qnareplyMapper.deleteByQnaReplyId(qnaReplyId); }
    public QnaReplySaveResponse write(AuthUser user, QnaReplySaveRequest qnaReplySaveRequest) {
        Long userId = user.getUserId();

        if (qnareplyMapper.save(qnaReplySaveRequest, userId) != 1) {
            throw new WriteFailedException();
        }
        QnaReplySaveResponse qnaReplyResponse = qnareplyMapper.saveResponse(qnaReplySaveRequest);

        return qnaReplyResponse;

    }
    public QnaReplyResponse getRead(Long qnaCommentId){

        return qnareplyMapper.findByQnaCommentId( qnaCommentId);}
    public QnaReplyEditResponse modify(Long qnaReplyId, QnaReplyEditReqeust qnaReplyEditReqeust, AuthUser user) {
        Long userId = user.getUserId();
        if(qnareplyMapper.edit(qnaReplyId, qnaReplyEditReqeust) != 1){
            throw new ModifyFailedException();
        }
        QnaReplyEditResponse editResult = qnareplyMapper.qnaReplyEditFind(qnaReplyEditReqeust);

        return editResult;
    }
}