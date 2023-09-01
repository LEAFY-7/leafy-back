package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;

import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
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

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    private final AlarmService alarmService;
    public void remove(Long qnaReplyId,AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        qnareplyMapper.deleteByQnaReplyId(qnaReplyId, userId, qnaCommentId); }
    public QnaReplySaveResponse write( QnaReplySaveRequest qnaReplySaveRequest, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();

        if (qnareplyMapper.saveQnaReply(qnaReplySaveRequest, userId, qnaCommentId) != 1) {
            throw new WriteFailedException();
        }

        // 알림 발송
        Long userIdByQnaId = qnareplyMapper.findUserIdByQnaCommentId(qnaCommentId);
        alarmService.createAlarm(userIdByQnaId , AlarmType.QNA_REPLY, qnaCommentId);
;

        QnaReplySaveResponse qnaReplyResponse = qnareplyMapper.selectAfterQnaReplySave(qnaReplySaveRequest.getQnaReplyId());

        return qnaReplyResponse;

    }
//    public QnaReplyResponse getRead(Long qnaReplyId, AuthUser user,Long qnaCommentId){
//       Long userId = user.getAuthorities();
//
//        return qnareplyMapper.findByQnaCommentId(qnaReplyId, userId, qnaCommentId);}
    public QnaReplyEditResponse modify(Long qnaReplyId, QnaReplyEditReqeust qnaReplyEditReqeust, AuthUser user, Long qnaCommentId) {
        Long userId = user.getUserId();
        if(qnareplyMapper.editQnaReply(qnaReplyId, qnaReplyEditReqeust, userId, qnaCommentId) != 1){
            throw new ModifyFailedException();
        }
        QnaReplyEditResponse editResult = qnareplyMapper.selectAfterQnaReplyEdit(qnaReplyId);

        return editResult;
    }
}