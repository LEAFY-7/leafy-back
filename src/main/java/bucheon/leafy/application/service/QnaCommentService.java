package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;


import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.comment.response.QnaCommentResponse;
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
    private final QnaMapper qnaMapper;

    public void remove(Long qnaCommentId, AuthUser user) {
        Long userId = user.getUserId();

        if( qnacommentMapper.deleteByQnaCommentId(qnaCommentId, userId) !=1 ){
            throw new RemoveFailedException();
        }
    }

    public QnaCommentSaveResponse write(AuthUser user, QnaCommentSaveRequest qnaCommentSaveRequest) {

        Long userId = user.getUserId();

        if (qnacommentMapper.save(qnaCommentSaveRequest, userId) != 1) {
            throw new WriteFailedException();
        }

        QnaCommentSaveResponse qnaSaveResponse = qnacommentMapper.qnaSaveFind(qnaCommentSaveRequest);
        // 답변상태 완료 코드작성하기
        qnaMapper.editByIdQnaStatus(qnaSaveResponse.getQnaId());

        return qnaSaveResponse;

    }

    public QnaCommentEditResponse modify(Long qnaReplyId, QnaCommentEditRequest qnaCommentEditRequest , AuthUser user) {
        Long userId = user.getUserId();

        if(qnacommentMapper.editByQnaCommentId(qnaReplyId, qnaCommentEditRequest) != 1){
            throw new ModifyFailedException();
        }

        QnaCommentEditResponse editResult = qnacommentMapper.qnaCommentEditFind(qnaCommentEditRequest);

        return editResult;
    }
}


