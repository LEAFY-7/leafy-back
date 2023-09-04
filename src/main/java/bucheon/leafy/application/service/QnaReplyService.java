package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;
import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.qna.reply.request.QnaReplyEditRequest;
import bucheon.leafy.domain.qna.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.qna.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplySaveResponse;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.QnaCommentNotFoundException;
import bucheon.leafy.exception.QnaNotFoundException;
import bucheon.leafy.exception.WriteFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    private final AlarmService alarmService;
    private final QnaCommentMapper qnaCommentMapper;
    public void remove(Long qnaReplyId,AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        qnareplyMapper.deleteByQnaReplyId(qnaReplyId, userId, qnaCommentId); }
    public QnaReplySaveResponse write( QnaReplySaveRequest qnaReplySaveRequest, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        QnaCommentResponse result = qnaCommentMapper.selectIsDeleteTrueAndFalseById(qnaCommentId) ;
        if ( result == null ) { throw new QnaCommentNotFoundException(); }
        if (qnareplyMapper.saveQnaReply(qnaReplySaveRequest, userId, qnaCommentId) != 1) {
            throw new WriteFailedException();
        }

        // 알림 발송
        Long userIdByQnaId = qnareplyMapper.selectUserIdByQnaCommentId(qnaCommentId);
        alarmService.createAlarm(userIdByQnaId , AlarmType.QNA_REPLY, qnaCommentId);

        QnaReplySaveResponse qnaReplyResponse = qnareplyMapper.selectAfterQnaReplySave(qnaReplySaveRequest.getQnaReplyId());

        return qnaReplyResponse;

    }
//    public QnaReplyResponse getRead(Long qnaReplyId, AuthUser user,Long qnaCommentId){
//       Long userId = user.getAuthorities();
//
//        return qnareplyMapper.findByQnaCommentId(qnaReplyId, userId, qnaCommentId);}
    public QnaReplyEditResponse modify(Long qnaReplyId, QnaReplyEditRequest qnaReplyEditRequest, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        QnaReplyResponse result = qnareplyMapper.selectIsDeleteTrueAndFalseById(qnaReplyId);
        if (result == null || result.equals(0)) { throw new QnaNotFoundException(); }
        if(qnareplyMapper.editQnaReply(qnaReplyId, qnaReplyEditRequest, userId, qnaCommentId) != 1){
            throw new ModifyFailedException();
        }
        QnaReplyEditResponse editResult = qnareplyMapper.selectAfterQnaReplyEdit(qnaReplyId);

        return editResult;
    }
}