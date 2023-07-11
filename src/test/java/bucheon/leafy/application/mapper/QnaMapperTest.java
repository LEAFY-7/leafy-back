package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
<<<<<<< HEAD
import bucheon.leafy.domain.qna.SearchHandler;
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD

import java.util.List;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class QnaMapperTest {

    @Autowired
    private QnaMapper qnaMapper;

    // 현재 시간
    @Test
    void count() {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.count());

        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(2, qnaMapper.count());
    }

    @Test
    void deleteAll() {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));

        assertEquals(1, qnaMapper.deleteAll());

<<<<<<< HEAD
        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.insert(qnaDto));

        assertEquals(2, qnaMapper.deleteAll());
    }

    @Test
    void deleteTest() {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));
        Long id = qnaMapper.selectAll().get(0).getId();
        assertEquals(1, qnaMapper.delete(id, qnaDto.getUserId()));
        assertEquals(0, qnaMapper.count());
    }

    @Test
    void insertTest() {
        qnaMapper.deleteAll();
<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(2, qnaMapper.count());

        qnaMapper.deleteAll();
<<<<<<< HEAD
        qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.count());
    }

    @Test
    void selectAllTest() {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

        List<QnaDto> list = qnaMapper.selectAll();
        assertEquals(0, list.size());

<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("dfsgf", "contents", 11111L);
=======
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));

        list = qnaMapper.selectAll();
        assertEquals(1, list.size());

        assertEquals(1, qnaMapper.insert(qnaDto));
        list = qnaMapper.selectAll();
        assertEquals(2, list.size());
    }

    @Test
    void selectTest() {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

<<<<<<< HEAD
        QnaDto qnaDto = new QnaDto("false", "contents", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        Long id = qnaMapper.selectAll().get(0).getId();
        QnaDto qnaDtoList = qnaMapper.select(id);
        System.out.println(qnaMapper.select(id));
        System.out.println(qnaDtoList);

=======
        QnaDto qnaDto = new QnaDto(false, "콘텐츠", "제목 없음", "콘텐츠 없음", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        Long id = qnaMapper.selectAll().get(0).getId();
        List<QnaDto> qnaDtoList = qnaMapper.select(id);
        System.out.println(qnaMapper.select(id));
        System.out.println(qnaDtoList);

        assertFalse(qnaDtoList.isEmpty());
        assertTrue(compareRelevantFields(qnaDto, qnaDtoList.get(0)));
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    }

    @Test
    void selectPageTest() {
        qnaMapper.deleteAll();

        for (int i = 1; i <= 10; i++) {
<<<<<<< HEAD
            QnaDto qnaDto = new QnaDto("dfsgf", ""+i, 11111L);
            qnaMapper.insert(qnaDto);
        }

        SearchHandler searchHandler = new SearchHandler(1,5);

        List<QnaDto> list = qnaMapper.searchSelectPage(searchHandler);
=======
            QnaDto qnaDto = new QnaDto(false, "contents", "no title", "" + i, 11111L);
            qnaMapper.insert(qnaDto);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<QnaDto> list = qnaMapper.selectPage(map);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals("10", list.get(0).getTitle());
        assertEquals("9", list.get(1).getTitle());
        assertEquals("8", list.get(2).getTitle());

<<<<<<< HEAD
    }



    @Test
    void updateTest() {
        qnaMapper.deleteAll();
        QnaDto qnaDto = new QnaDto("false", "contents", 11111L);
=======
        map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = qnaMapper.selectPage(map);
        assertEquals("10", list.get(0).getTitle());

        map = new HashMap<>();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = qnaMapper.selectPage(map);
        assertEquals("3", list.get(0).getTitle());
        assertEquals("2", list.get(1).getTitle());
        assertEquals("1", list.get(2).getTitle());
    }

    @Test
    void updateTest() {
        qnaMapper.deleteAll();
        QnaDto qnaDto = new QnaDto(false, "contents", "no title", "no content", 11111L);
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        assertEquals(1, qnaMapper.insert(qnaDto));

        Long id = qnaMapper.selectAll().get(0).getId();
        qnaDto.setId(id);
        qnaDto.setTitle("yes title");
        qnaDto.setUserId(11111L);
        assertEquals(1, qnaMapper.update(qnaDto));
    }

    private boolean compareRelevantFields(QnaDto expected, QnaDto actual) {
        return Objects.equals(expected.getContents(), actual.getContents()) &&
<<<<<<< HEAD
=======
                Objects.equals(expected.getQnaStatus(), actual.getQnaStatus()) &&
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
                Objects.equals(expected.getTitle(), actual.getTitle()) &&
                Objects.equals(expected.getUserId(), actual.getUserId());
    }
}