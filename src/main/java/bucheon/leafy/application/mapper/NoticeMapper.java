package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD

import java.util.List;

@Mapper
public interface NoticeMapper {
    NoticeDto findById(Long id);
    int viewCnt(Long id);
    int delete(Long id);
    Long insert(NoticeDto noticeDto) ;
    int update(NoticeDto noticeDto);
    List<NoticeDto> selectAll();
=======
import java.util.List;
import java.util.Map;


@Mapper
public interface NoticeMapper {

    int count();

    NoticeDto select(Long id);
    int increaseViewCnt(Long id);

    int delete(Long id, Long userId);

    int insert(NoticeDto noticeDto) ;

    int update(NoticeDto noticeDto);

    List<NoticeDto> selectAll();
    List<NoticeDto> selectPage(Map map);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
}
