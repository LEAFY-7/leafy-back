package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaCommentMapper {
    int deleteByQnaCommentId(@Param("qnaCommentId") Long qnaCommentId,@Param("userId") Long userId) ;
    int save(@Param("qnaCommentSaveRequest") QnaCommentSaveRequest qnaCommentSaveRequest, @Param("userId") Long userId);
    QnaCommentSaveResponse qnaSaveFind(QnaCommentSaveRequest qnaCommentSaveRequest);
    int editByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId , @Param("qnaCommentEditRequest") QnaCommentEditRequest qnaCommentEditRequest) ;
    QnaCommentEditResponse qnaCommentEditFind(QnaCommentEditRequest qnaCommentEditRequest);    //수정 조회오기

}
