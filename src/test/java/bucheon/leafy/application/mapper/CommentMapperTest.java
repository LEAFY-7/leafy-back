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

<<<<<<< HEAD
=======

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    @Autowired
    CommentMapper commentMapper;


    @Test
    void delete() {
        commentMapper.deleteAll();

<<<<<<< HEAD
        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);
=======
        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1,commentMapper.insert(commentDto));

        List<CommentDto> resultList = commentMapper.selectAll();
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde

        int insertResult = commentMapper.insert(commentDto);
        assertEquals(1, insertResult);

<<<<<<< HEAD

        List<CommentDto> id = commentMapper.selectId();
        assertNotNull(id.get(0).getId());

        commentMapper.delete(id.get(0).getId(), commentDto.getUserId());
=======
        Long id = resultList.get(0).getId();
        commentMapper.delete(id, resultList.get(0).getUserId());
        assertEquals(1,commentMapper.count() );

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }

    @Test
    void insert() {
        commentMapper.deleteAll();

<<<<<<< HEAD
        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);
=======
        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        assertEquals(1,commentMapper.insert(commentDto));

    }

<<<<<<< HEAD
=======

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    @Test
    void select() {
        commentMapper.deleteAll();

<<<<<<< HEAD
        for (int i = 1; i <= 10; i++) {
            CommentDto commentDto = new CommentDto("asdfqwf"+i, 22224L, 1L);
            commentMapper.insert(commentDto);
        }

        List<CommentDto> id = commentMapper.selectId();
//        CommentDto insertResult = commentMapper.select(id.get(0).getId());
//        assertNotNull(insertResult);
=======
        commentMapper.deleteAll();
        for( int i=1; i <= 10; i++ ){
            CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
            commentMapper.insert(commentDto);
        }

        List<CommentDto> resultList = commentMapper.selectAll();
        Long id = resultList.get(0).getId();

        List<CommentDto> insertResult = commentMapper.select(id);
        assertEquals(1, insertResult.size());
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }

    @Test
    void update() {
        commentMapper.deleteAll();

<<<<<<< HEAD
        CommentDto commentDto = new CommentDto("asdfqwf", 22224L, 1L);
        assertEquals(1,commentMapper.insert(commentDto));

        List<CommentDto> id = commentMapper.selectId();

        commentDto.setId(id.get(0).getId());
        commentDto.setComment("String");
        commentDto.setQnaId(id.get(0).getQnaId());
=======
        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1,commentMapper.insert(commentDto));


        Long id = commentMapper.selectAll().get(0).getId();
        commentDto.setId(id);
        commentDto.setComment("yes title");
        commentDto.setUserId(12L);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        assertEquals(1,commentMapper.update(commentDto));
    }
}