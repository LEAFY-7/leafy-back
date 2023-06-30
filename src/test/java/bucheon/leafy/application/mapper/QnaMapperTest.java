package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class QnaMapperTest {

    @Autowired
    private QnaMapper qnaMapper;

    // 현재 시간
    @Test
    void count()  {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

        QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.count());

        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(2, qnaMapper.count());
    }

    @Test
    void deleteAll()  {
        qnaMapper.deleteAll();
        assertEquals(0, qnaMapper.count());

        QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        assertEquals(1, qnaMapper.deleteAll());

        qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.insert(qnaDto));

        assertEquals(2, qnaMapper.deleteAll());
    }

    @Test
    void deleteTest()  {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        Long id = qnaMapper.selectAll().get(0).getId();
        assertTrue(qnaMapper.delete(id, qnaDto.getUserId())==1);
        assertTrue(qnaMapper.count()==0);


    }


    @Test
    void insertTest()  {
        qnaMapper.deleteAll();
        QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));

        qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(2, qnaMapper.count());

        qnaMapper.deleteAll();
        qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertEquals(1, qnaMapper.insert(qnaDto));
        assertEquals(1, qnaMapper.count());
    }

    @Test
    void selectAllTest()  {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        List<QnaDto> list = qnaMapper.selectAll();
        assertTrue(list.size() == 0);

        QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertTrue(qnaMapper.insert(qnaDto)==1);

        list = qnaMapper.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        list = qnaMapper.selectAll();
        assertTrue(list.size() == 2);
    }


    @Test
    void selectTest () {
        qnaMapper.deleteAll();
        assertTrue (qnaMapper.count () == 0);

        QnaDto qnaDto = new QnaDto(false, "콘텐츠", "제목 없음", "콘텐츠 없음", 11111L);
        assertTrue (qnaMapper.insert (qnaDto) == 1);

        Long id = qnaMapper.selectAll().get(0).getId();
        List<QnaDto> qnaDto2 = qnaMapper.select(id);
        System.out.println(qnaMapper.select(id));
        System.out.println (qnaDto2);

        assertFalse(qnaDto2.isEmpty());
        assertTrue(compareRelevantFields(qnaDto, qnaDto2.get(0)));
    }

    @Test
    void selectPageTest()  {
        qnaMapper.deleteAll();

        for (int i = 1; i <= 10; i++) {
            QnaDto qnaDto = new QnaDto(false,"contents","no title", ""+i, 11111L);
            qnaMapper.insert(qnaDto);
        }


        Map<String, Integer> map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<QnaDto> list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap<>();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap<>();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    void updateTest()  {
        qnaMapper.deleteAll();
            QnaDto qnaDto = new QnaDto(false,"contents","no title", "no content", 11111L);
        assertTrue(qnaMapper.insert(qnaDto) == 1);

        Long id = qnaMapper.selectAll().get(0).getId();
        qnaDto.setId(id);
        qnaDto.setTitle("yes title");
        qnaDto.setUserId(11111L);
        assertTrue(qnaMapper.update(qnaDto)==1);

    }

    private boolean compareRelevantFields(QnaDto expected, QnaDto actual) {
        return Objects.equals(expected.getContents(), actual.getContents()) &&
                Objects.equals(expected.getQnaStatus(), actual.getQnaStatus()) &&
                Objects.equals(expected.getTitle(), actual.getTitle()) &&
                Objects.equals(expected.getUserId(), actual.getUserId());
    }


}
