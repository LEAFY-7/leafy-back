package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
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
        QnaSaveRequest qnaSaveRequest = new QnaSaveRequest("contents","title");
        qnaMapper.save(1L, qnaSaveRequest);
    }

    @Test
    void count() {
        qnaMapper.count();
        System.out.println("값"+ qnaMapper.count());

    }

    @Test
    void findById() {

    }

    @Test
    void deleteById() {
        qnaMapper.deleteById(4L);
    }

    @Test
    void editById() {

    }

    @Test
    void findQnaById() {
        qnaMapper.findQnaById(4L);
    }

    @Test
    void editByIdQnaStatus() {
        qnaMapper.editByIdQnaStatus(4L);
    }

    @Test
    void adminSelectAll() {

    }

    @Test
    void pageFindById() {

    }

    @Test
    void viewCnt() {
        qnaMapper.viewCnt(4L);
    }
}