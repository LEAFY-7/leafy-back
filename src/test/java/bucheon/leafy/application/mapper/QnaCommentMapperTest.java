package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QnaCommentMapperTest {
    @Autowired
    QnaCommentMapper qnaCommentMapper;

    @Test
    void deleteByQnaCommentId() {
        qnaCommentMapper.deleteByQnaCommentId(1L,11111L);
    }

    @Test
    void save() {
        qnaCommentMapper.save(new QnaCommentDto("save",11111L,5L));
    }

    @Test
    void editByQnaCommentId() {
        qnaCommentMapper.editByQnaCommentId(new QnaCommentDto(1L,"HELLO",11111L));
    }
}