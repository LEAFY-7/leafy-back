package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.QnaStatus;
import bucheon.leafy.domain.qna.QnaType;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.QnaAlamResponse;
import bucheon.leafy.domain.qna.response.QnaEditResponse;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {
    int count(Long userId);
    int adminCount();
    QnaResponse selectById(Long qnaId);   //클릭해서 읽기(select)
    int deleteById(Long qnaId);  //삭제
    Long save(@Param("userId")Long userId , @Param("qnaStatus") QnaType qnaStatus, @Param("qnaSaveRequest")QnaSaveRequest qnaSaveRequest);   //저장
    QnaSaveResponse selectAfterSave(Long qnaId);   //저장 조회오기
    Long editById(@Param("qnaId")Long qnaId, @Param("qnaEditRequest") QnaEditRequest qnaEditRequest, @Param("userId")Long userId);//수정
    QnaEditResponse selectAfterEdit(Long qnaId);    //수정 조회오기
    QnaResponse findQnaById(Long qnaId); //qnaId가지고 오기
    List<PageResponse> adminSelectAll(@Param("pageRequest")PageRequest pageRequest);   //관리자리스트
    List<PageResponse> pageFindById(@Param("userId")Long userId, @Param("pageRequest")PageRequest pageRequest);//리스트
    int viewCount(Long qnaId);
    QnaResponse selectIsDeleteTrueAndFalseById(@Param("qnaId")Long qnaId, @Param("userId")Long userId);
    QnaAlamResponse selectQnaCommentIdAndQnaReplyIdByQnaId(Long qnaId);
}
