package bucheon.leafy.application.service;

import bucheon.leafy.util.SortStatus;
import bucheon.leafy.util.request.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaServiceTest {

    @Autowired
    QnaService qnaService;
    @Test
    void remove() {
        qnaService.remove(2L);
    }

    @Test
    void write() {
        QnaDto qnaDto = new QnaDto("contents", "title", 33334L);
        qnaService.write(qnaDto);
    }

    @Test
    void admingetList() {
        PageRequest pageRequest = new PageRequest(10, 10, 10L, "someColumn", SortStatus.DESC);
        qnaService.admingetList(pageRequest);
    }

    @Test
    void getList() {
        PageRequest pageRequest = new PageRequest(10, 10, 10L, "someColumn", SortStatus.DESC);
        qnaService.getList(1L,11111L,pageRequest);
    }

    @Test
    void getRead() { qnaService.getRead(1L);
    }

    @Test
    void modify() {
        QnaDto qnaDto = new QnaDto("edit contens","edit title",22222L);
        qnaService.modify(qnaDto,2L);
    }

    @Test
    void getQnaById() {
        qnaService.getQnaById(1L);
    }

}