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
    int count();
    NoticeDto findById(Long noticeId);    //id찾기
    int viewCnt(Long noticeId);   //view카운트
    int deleteById(Long noticeId);    //삭제
    int save(NoticeDto noticeDto);// 저장
    int editById(NoticeDto noticeDto);//수정
    List<PageResponse> pageFindById(@Param("pageRequest") PageRequest pageRequest);//리스트 보여주기
    List<Long> findAllUserIds();
    //    NoticeDto searchSelectPage(PageRequest pageRequest);    //공지사항 검색 나중에 추가

}
