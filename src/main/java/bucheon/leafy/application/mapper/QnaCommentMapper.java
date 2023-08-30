package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaCommentMapper {

    int deleteByQnaCommentId(@Param("userId")Long userId, @Param("qnaId")Long qnaId, @Param("qnaCommentId") Long qnaCommentId) ;
    int saveQnaComment(@Param("qnaCommentSaveRequest")QnaCommentSaveRequest qnaCommentSaveRequest, @Param("userId") Long userId, @Param("qnaId")Long qnaId);
    QnaCommentSaveResponse qnaSaveFind(Long userId);    //저장 조회하기
    int editByQnaCommentId(@Param("qnaCommentEditRequest") QnaCommentEditRequest qnaCommentEditRequest, @Param("qnaCommentId")Long qnaCommentId) ;
    QnaCommentEditResponse qnaCommentEditFind(Long userId);    //수정 조회오기
    int  editByIdQnaStatus(Long qnaId);//상태 변경
    Long findUserIdByQnaId(Long qnaCommentId);

}
