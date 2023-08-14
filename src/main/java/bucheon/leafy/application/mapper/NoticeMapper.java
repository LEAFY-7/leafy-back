package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NoticeMapper {
    NoticeDto findById(Long id);    //id찾기
    int viewCnt(Long id);   //view카운트
    int deleteById(Long id);    //삭제
    Long save(NoticeDto noticeDto);// 저장
    int editById( NoticeDto noticeDto);//수정
    List<PageResponse> pageFindById(PageRequest pageRequest);//리스트 보여주기
    Long findTableIdByUserId(Long userId);
    //    NoticeDto searchSelectPage(PageRequest pageRequest);    //공지사항 검색 나중에 추가

}
