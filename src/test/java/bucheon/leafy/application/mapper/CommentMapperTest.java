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

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1,commentMapper.insert(commentDto));

        List<CommentDto> resultList = commentMapper.selectAll();

        int insertResult = commentMapper.insert(commentDto);
        assertEquals(1, insertResult);

        Long id = resultList.get(0).getId();
        commentMapper.delete(id, resultList.get(0).getUserId());
        assertEquals(1,commentMapper.count() );

    }

    @Test
    void insert() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1,commentMapper.insert(commentDto));

    }


    @Test
    void select() {
        commentMapper.deleteAll();

        commentMapper.deleteAll();
        for( int i=1; i <= 10; i++ ){
            CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
            commentMapper.insert(commentDto);
        }

        List<CommentDto> resultList = commentMapper.selectAll();
        Long id = resultList.get(0).getId();

        List<CommentDto> insertResult = commentMapper.select(id);
        assertEquals(1, insertResult.size());
    }

    @Test
    void update() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1,commentMapper.insert(commentDto));


        Long id = commentMapper.selectAll().get(0).getId();
        commentDto.setId(id);
        commentDto.setComment("yes title");
        commentDto.setUserId(12L);
        assertEquals(1,commentMapper.update(commentDto));
    }
}