package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.QnaReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QnaReplyMapperTest {

    @Autowired
    QnaReplyMapper qnaReplyMapper;

    @Test
    void deleteByQnaReplyId() {
        qnaReplyMapper.deleteByQnaReplyId(1L,11111L);

    }
    @Test
    void save() {
        qnaReplyMapper.save(new QnaReplyDto("Sample reply 1",11111L, 4L));
    }

    @Test
    void edit() {
        qnaReplyMapper.edit(new QnaReplyDto("asdf",1L,11111L));

    }

}