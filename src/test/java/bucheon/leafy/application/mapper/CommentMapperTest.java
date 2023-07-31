package bucheon.leafy.application.mapper;

import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.domain.comment.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;


    @Test
    void delete() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);

        int insertResult = commentMapper.insert(commentDto);
        assertEquals(1, insertResult);


        List<CommentDto> id = commentMapper.selectId();
        assertNotNull(id.get(0).getId());

        commentMapper.delete(id.get(0).getId(), commentDto.getUserId());
    }

    @Test
    void insert() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);
        assertEquals(1,commentMapper.insert(commentDto));

    }

    @Test
    void select() {
        commentMapper.deleteAll();

        for (int i = 1; i <= 10; i++) {
            CommentDto commentDto = new CommentDto("asdfqwf"+i, 22224L, 1L);
            commentMapper.insert(commentDto);
        }

        List<CommentDto> id = commentMapper.selectId();
//        CommentDto insertResult = commentMapper.select(id.get(0).getId());
//        assertNotNull(insertResult);
    }

    @Test
    void update() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);
        assertEquals(1,commentMapper.insert(commentDto));

        List<CommentDto> id = commentMapper.selectId();

        commentDto.setId(id.get(0).getId());
        commentDto.setComment("String");
        commentDto.setQnaId(id.get(0).getQnaId());
        assertEquals(1,commentMapper.update(commentDto));
    }
}