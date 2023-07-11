package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
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
}
