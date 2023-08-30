package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;


import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;


import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.WriteFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    private final AlarmService alarmService;
    public void remove(AuthUser user, Long qnaId, Long qnaCommentId) {
        Long userId = user.getUserId();

        if( qnacommentMapper.deleteByQnaCommentId(userId,qnaId, qnaCommentId) !=1 ){
            throw new RemoveFailedException();
        }
    }

    public QnaCommentSaveResponse write(QnaCommentSaveRequest qnaCommentSaveRequest, AuthUser user, Long qnaId) {
        Long userId = user.getUserId();

        if (qnacommentMapper.saveQnaComment(qnaCommentSaveRequest, userId, qnaId) != 1) {
            throw new WriteFailedException();
        }

        // 알림 발송
        Long userIdByQnaId = qnacommentMapper.findUserIdByQnaId(qnaId);
        alarmService.createAlarm(userIdByQnaId , AlarmType.QNA_REPLY, qnaId);


        QnaCommentSaveResponse qnaSaveResponse = qnacommentMapper.qnaSaveFind(userId);
        qnacommentMapper.editByIdQnaStatus(qnaId);
        return qnaSaveResponse;
    }

    public QnaCommentEditResponse modify(QnaCommentEditRequest qnaCommentEditRequest , Long qnaCoomentId,  AuthUser user) {
        Long userId = user.getUserId();

        if(qnacommentMapper.editByQnaCommentId(qnaCommentEditRequest, qnaCoomentId) != 1){
            throw new ModifyFailedException();
        }

        QnaCommentEditResponse editResult = qnacommentMapper.qnaCommentEditFind(userId);

        return editResult;
    }
}


