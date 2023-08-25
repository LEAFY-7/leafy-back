package bucheon.leafy.application.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaReplyMapperTest {

    @Autowired
    QnaReplyMapper qnaReplyMapper;

    @Test
    void deleteByQnaReplyId() {
        qnaReplyMapper.deleteByQnaReplyId(1L,11111L);

    }
    @Test
    void saveById() {
        qnaReplyMapper.saveById(new QnaReplyDto("Sample reply 1",11111L, 4L));
    }

    @Test
    void edit() {
        qnaReplyMapper.editById(new QnaReplyDto("asdf",1L,11111L));

    }

}