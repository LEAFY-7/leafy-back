package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;


import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;

import bucheon.leafy.domain.qna.QnaType;
import bucheon.leafy.domain.qna.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.qna.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.qna.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.qna.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.qna.comment.response.QnaCommentSaveResponse;


import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static bucheon.leafy.domain.qna.QnaType.DONE;


@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    private final AlarmService alarmService;
    private final QnaMapper qnaMapper;
    public void remove(AuthUser user, Long qnaId, Long qnaCommentId) {
        Long userId = user.getUserId();

        if( qnacommentMapper.deleteByQnaCommentId(userId,qnaId, qnaCommentId) !=1 ){
            throw new RemoveFailedException();
        }
    }

    public QnaCommentSaveResponse write(QnaCommentSaveRequest qnaCommentSaveRequest, AuthUser user, Long qnaId) {
        Long userId = user.getUserId();
        QnaType qnaStatus = DONE;

        QnaResponse result = qnaMapper.findIsDeleteById(qnaId);
        if (result == null) { throw new QnaNotFoundException(); }

        if (qnacommentMapper.saveQnaComment(qnaCommentSaveRequest, userId, qnaId) != 1) {
            throw new WriteFailedException();
        }




        // 기본 상태로 업데이트된 Qna 정보 조회
        QnaCommentSaveResponse qnaSaveResponse = qnacommentMapper.findAfterQnaCommentSave(qnaCommentSaveRequest.getQnaCommentId());
        // 알림 발송
        Long userIdByQnaId = qnacommentMapper.findUserIdByQnaId(qnaId);
        if(qnacommentMapper.findUserId(userIdByQnaId) != null){ alarmService.createAlarm(userIdByQnaId , AlarmType.QNA_COMMENT, qnaSaveResponse.getQnaCommentId()); }
        // 사용자가 관리자 권한을 가지고 있거나, 로그인한 경우
        if (user != null && user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            qnacommentMapper.editByIdQnaStatus(qnaId, qnaStatus); // 상태 업데이트
        }

        return qnaSaveResponse;
    }

    public QnaCommentEditResponse modify(QnaCommentEditRequest qnaCommentEditRequest , Long qnaCommentId,  AuthUser user, Long qnaId) {
        Long userId = user.getUserId();

        QnaCommentResponse result = qnacommentMapper.findIsDeleteById(qnaCommentId);
        if (result == null) { throw new QnaCommentNotFoundException(); }

        if(qnacommentMapper.editByQnaCommentId(qnaCommentEditRequest, qnaCommentId, userId, qnaId) != 1){
            throw new ModifyFailedException();
        }

        QnaCommentEditResponse editResult = qnacommentMapper.findAfterQnaCommentEdit(qnaCommentId);

        return editResult;
    }
}


