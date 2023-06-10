package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.board.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void findBoardList() {
        List<Board> boardList =  boardMapper.findBoardList();

        for (Board board : boardList) {
            System.out.println(board.getId() + board.getTitle());
        }
    }

    @Test
    void findBoardById() {
        //when
        Board board = boardMapper.findBoardById(1L);

        //then
        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo(1L);
    }

    @Test
    void saveBoard() {
        //given
        Board board = Board.builder()
                .title("새제목")
                .contents("새내용")
                .isHide(false)
                .build();
        //when
        Board result = boardMapper.saveBoard(board);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    void editBoard() {

    }

    @Test
    void deleteBoard() {
    }
}