package bucheon.leafy.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class NoticeServiceTest {
    @Autowired
    NoticeService noticeService;
    @Test
    void remove() {
//        noticeService.remove(id);
    }

    @Test
    void write() {
    }

    @Test
    void getList() {
    }

    @Test
    void getRead() {
    }

    @Test
    void modify() {
    }
}