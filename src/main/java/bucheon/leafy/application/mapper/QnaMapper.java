package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeSaveResponse;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.QnaEditResponse;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.domain.qna.response.QnaStatusResponse;
import bucheon.leafy.domain.reply.response.QnaReplyEditResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {
    int count();
    QnaResponse findById(Long qnaId);   //클릭해서 읽기(select)
    boolean deleteById(Long qnaId);  //삭제
    QnaSaveResponse save(@Param("userId")Long userId ,@Param("qnaSaveRequest")QnaSaveRequest qnaSaveRequest);   //저장
    QnaEditResponse editById(@Param("qnaId")Long qnaId, @Param("qnaEditRequest") QnaEditRequest qnaEditRequest);//수정
    Long findQnaById(Long qnaId); //qnaId가지고 오기
    int  editByIdQnaStatus(Long qnaId);//상태 변경
    List<PageResponse> adminSelectAll(PageRequest pageRequest);   //관리자리스트
    List<PageResponse> pageFindById(@Param("qnaId")Long qnaId, @Param("userId")Long userId, @Param("pageRequest")PageRequest pageRequest);//리스트
    int viewCnt(Long qnaId);
}
