package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.util.SortStatus;
import bucheon.leafy.util.request.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeMapperTest {

    @Autowired
    NoticeMapper noticeMapper;

    @Test
    void findById() {
        noticeMapper.findById(1L);
    }

    @Test
    void viewCnt() {
        noticeMapper.viewCnt(1L);
    }

    @Test
    void deleteById() {
        noticeMapper.deleteById(21L);
    }

    @Test
    void save() {
        int savedNoticeId = noticeMapper.save(new NoticeDto("rrr", "rrr", 22222L));
        System.out.println(savedNoticeId);
    }

    @Test
    void editById() {
        noticeMapper.editById(new NoticeDto(21L,"change title ","change contents"));
    }

    @Test
    void pageFindById() {
        PageRequest pageRequest = new PageRequest(10, 10, 10L, null,null );
        noticeMapper.pageFindById(pageRequest);

    }
}
