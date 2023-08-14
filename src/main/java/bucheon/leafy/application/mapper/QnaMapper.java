package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {
    long count();
    QnaDto findById(Long qnaId);   //클릭해서 읽기(select)
    boolean deleteById(Long qnaId);  //삭제
    Long save(QnaDto qnaDto);   //저장
    int editById(@Param("qnaDto") QnaDto qnaDto, @Param("id") Long id);//수정
    QnaDto findQnaById(Long qnaId); //qnaId가지고 오기
    int editByIdQnaStatus(Long qnaId);//상태 변경
    PageResponse adminSelectAll(PageRequest pageRequest);   //관리자리스트
    List<PageResponse> pageFindById(@Param("id")Long qnaId, @Param("pageRequest")PageRequest pageRequest);//리스트
    int viewCnt(Long qnaId);
}
