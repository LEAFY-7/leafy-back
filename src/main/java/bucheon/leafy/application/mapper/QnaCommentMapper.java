package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaType;
import bucheon.leafy.domain.qna.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.qna.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.qna.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.qna.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.qna.comment.response.QnaCommentSaveResponse;
import bucheon.leafy.domain.qna.QnaStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QnaCommentMapper {

    int deleteByQnaCommentId(@Param("userId")Long userId, @Param("qnaId")Long qnaId, @Param("qnaCommentId") Long qnaCommentId) ;
    int saveQnaComment(@Param("qnaCommentSaveRequest")QnaCommentSaveRequest qnaCommentSaveRequest, @Param("userId") Long userId, @Param("qnaId")Long qnaId);
    QnaCommentSaveResponse findAfterQnaCommentSave(Long qnaCommentId);    //저장 조회하기
    int editByQnaCommentId(@Param("qnaCommentEditRequest") QnaCommentEditRequest qnaCommentEditRequest, @Param("qnaCommentId")Long qnaCommentId, @Param("userId")Long userId, @Param("qnaId")Long qnaId) ;
    QnaCommentEditResponse findAfterQnaCommentEdit(Long qnaCommentId);    //수정 조회오기
    int editByIdQnaStatus(@Param("qnaId")Long qnaId, @Param("qnaStatus")QnaType qnaStatus);//상태 변경
    List<QnaCommentResponse> findByQnaId(Long qnaId);
    Long findUserIdByQnaId(Long qnaCommentId);
    QnaCommentResponse findIsDeleteById(@Param("qnaCommentId")Long qnaCommentId);
    Long findUserId(Long userId);
    List<Long>findQnaCommentIdByQnaId(Long qnaId);

}
