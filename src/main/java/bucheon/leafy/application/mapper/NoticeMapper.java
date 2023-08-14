package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NoticeMapper {
    NoticeDto findById(Long id);
    int viewCnt(Long id);
    int delete(Long id);
    NoticeDto searchSelectPage(PageRequest pageRequest);
    Long insert(NoticeDto noticeDto) ;
    int update(NoticeDto noticeDto);
    PageResponse selectAll();
    Long findTableIdByUserId(Long userId);
}
