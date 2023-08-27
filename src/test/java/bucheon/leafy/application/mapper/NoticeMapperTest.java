package bucheon.leafy.application.mapper;

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
    void pageFindById() {


    }
}
