package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;


@Mapper
public interface NoticeMapper {

    int count();
<<<<<<< HEAD

    NoticeDto select(Long id);
    int increaseViewCnt(Long id);

    int delete(Long id, Long userId);

    int insert(NoticeDto noticeDto) ;

    int update(NoticeDto noticeDto);

    List<NoticeDto> selectAll();
    List<NoticeDto> selectPage(Map map);
=======
    int deleteAll();
    List<NoticeDto> select(Long id);
    int delete(Long id, Long userId);
    int insert(NoticeDto noticeDto) ;
    int update(NoticeDto noticeDto);
    List<NoticeDto> selectPage(Map<String ,Integer>map);
    List<NoticeDto> selectAll();

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
}
