package bucheon.leafy.application.mapper;


import bucheon.leafy.util.request.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaMapperTest {

    @Autowired
    QnaMapper qnaMapper;

    @Test
    void save() {
        QnaDto qnaDto = new QnaDto("contents", "title", 33334L);
        qnaMapper.save(qnaDto);
    }

    @Test
    void count() {
        qnaMapper.count();
        System.out.println("값"+ qnaMapper.count());

    }

    @Test
    void findById() {
        QnaDto qnaDto = qnaMapper.findById(1L, 11111L);
        if (qnaDto != null) {
            System.out.println("Found QnaDto: " + qnaDto);
        } else {
            System.out.println("QnaDto not found.");
        }
    }

    @Test
    void deleteById() {
        qnaMapper.deleteById(2L);
    }

    @Test
    void editById() {
       QnaDto qnaDto = new QnaDto("edit contens","edit title",22222L);
       qnaMapper.editById(qnaDto,2L);
    }

    @Test
    void findQnaById() {
        qnaMapper.findQnaById(1L);
    }

    @Test
    void editByIdQnaStatus() {
        qnaMapper.editByIdQnaStatus(1L);
    }

    @Test
    void adminSelectAll() {
        PageRequest pageRequest = new PageRequest(10, 10, 10L, null, null);
        System.out.println("답" + pageRequest);
        qnaMapper.adminSelectAll(pageRequest);
    }

    @Test
    void pageFindById() {
        PageRequest pageRequest = new PageRequest(10, 10, 10L, null, null);
        qnaMapper.pageFindById(1L,11111L, pageRequest);
    }

    @Test
    void viewCnt() {
        qnaMapper.viewCnt(22222L);
    }
}