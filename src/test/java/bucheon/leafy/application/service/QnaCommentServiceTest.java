package bucheon.leafy.application.service;

import bucheon.leafy.domain.comment.QnaCommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QnaCommentServiceTest {
    @Autowired
    QnaCommentService qnaCommentService;
    @Test
    void remove() { qnaCommentService.remove(1L,11111L); }
    @Test
    void write() {
        qnaCommentService.write(new QnaCommentDto("comment",11111L,1L));
    }
    @Test
    void modify() {
        qnaCommentService.modify(new QnaCommentDto("comment modify",11111L,1L));
    }
}