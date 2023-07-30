package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    NoticeDto select(Long id);
    int viewCnt(Long id);
    int delete(Long id);
    Long insert(NoticeDto noticeDto) ;
    int update(NoticeDto noticeDto);
    List<NoticeDto> selectAll();
}
