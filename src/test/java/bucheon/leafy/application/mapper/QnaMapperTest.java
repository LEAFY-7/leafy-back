package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.QnaDto;

import bucheon.leafy.util.request.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static bucheon.leafy.util.SortStatus.DESC;

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
        qnaMapper.findById(1L,1111L);
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
        qnaMapper.adminSelectAll(new PageRequest(10,10,10L,"오름차순" , DESC));
    }

    @Test
    void pageFindById() {
    }

    @Test
    void viewCnt() {
        qnaMapper.viewCnt(22222L);
    }
}