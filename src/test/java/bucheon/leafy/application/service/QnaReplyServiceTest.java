package bucheon.leafy.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaReplyServiceTest {
    @Autowired
    QnaReplyService qnaReplyService;

    @Test
    void remove() {
        qnaReplyService.remove(1L,11111L);
    }
    @Test
    void write() {
        qnaReplyService.write(new QnaReplyDto("Sample reply 1",11111L, 4L));
    }
    @Test
    void modify() {
        qnaReplyService.modify(new QnaReplyDto("asdf",1L,11111L));
    }
}