package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;
import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.qna.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.qna.reply.request.QnaReplyEditRequest;
import bucheon.leafy.domain.qna.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.qna.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplySaveResponse;
import bucheon.leafy.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnaReplyMapper;
    private final AlarmService alarmService;
    private final QnaCommentMapper qnaCommentMapper;
    public void remove(Long qnaReplyId,AuthUser user, Long qnaCommentId) {

        Long userId = user.getUserId();

        if(qnaReplyMapper.deleteByQnaReplyId(qnaReplyId, userId, qnaCommentId) !=1 ) {
            throw new RemoveFailedException();
        }
    }
    public QnaReplySaveResponse write( QnaReplySaveRequest qnaReplySaveRequest, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        QnaCommentResponse result = qnaCommentMapper.findIsDeleteById(qnaCommentId) ;
        if ( result == null ) { throw new QnaCommentNotFoundException(); }
        if (qnaReplyMapper.saveQnaReply(qnaReplySaveRequest, userId, qnaCommentId) != 1) {
            throw new WriteFailedException();
        }


        QnaReplySaveResponse qnaReplyResponse = qnaReplyMapper.findAfterQnaReplySave(qnaReplySaveRequest.getQnaReplyId());
        // 알림 발송
        Long userIdByQnaId = qnaReplyMapper.findUserIdByQnaCommentId(qnaCommentId);
        if(qnaReplyMapper.findUserId(userIdByQnaId) != null){ alarmService.createAlarm(userIdByQnaId , AlarmType.QNA_REPLY, qnaReplyResponse.getQnaReplyId()); }

        return qnaReplyResponse;

    }
    public QnaReplyEditResponse modify(Long qnaReplyId, QnaReplyEditRequest qnaReplyEditRequest, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        QnaReplyResponse result = qnaReplyMapper.findIsDeleteById(qnaReplyId);
        if (result == null) { throw new QnaReplyNotFoundException(); }
        if(qnaReplyMapper.editQnaReply(qnaReplyId, qnaReplyEditRequest, userId, qnaCommentId) != 1){
            throw new ModifyFailedException();
        }
        QnaReplyEditResponse editResult = qnaReplyMapper.findAfterQnaReplyEdit(qnaReplyId);

        return editResult;
    }
}