package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        qnaCommentMapper.save(new QnaCommentSaveRequest(1L,"String",1L),1L);
    }

    @Test
    void editByQnaCommentId() {
    }
}