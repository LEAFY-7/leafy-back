package bucheon.leafy.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    @Test
    void remove() {
        noticeService.remove(21L);
    }

    @Test
    void write() {
//         noticeService.write(22222L , new NoticeDto("rrr", "rrr", 22222L));

    }

    @Test
    void getList() {
//        noticeService.getList();
    }

    @Test
    void getRead() {
        noticeService.getRead(21L);
    }

    @Test
    void modify() {
        noticeService.modify(new NoticeDto(21L,"change title ","change contents"));
    }

}