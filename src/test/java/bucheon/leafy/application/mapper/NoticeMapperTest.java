<<<<<<< HEAD
package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.notice.NoticeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoticeMapperTest {


    @Autowired
    private NoticeMapper noticeMapper;
    @Test
    void count()  {
        noticeMapper.deleteAll();
        assertEquals(0, noticeMapper.count());

        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));
        assertEquals(1, noticeMapper.count());

        assertEquals(1, noticeMapper.insert(noticeDto));
        assertEquals(2, noticeMapper.count());
    }

    @Test
    void deleteAll()  {
        noticeMapper.deleteAll();
        assertEquals(0, noticeMapper.count());

        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));

        assertEquals(1, noticeMapper.deleteAll());

        noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));
        assertEquals(1, noticeMapper.insert(noticeDto));

        assertEquals(2, noticeMapper.deleteAll());
    }

    @Test
    void deleteTest()  {
        noticeMapper.deleteAll();
        assertEquals(0,noticeMapper.count());

        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));

        long id = noticeMapper.selectAll().get(0).getId();
        long userid = noticeDto.getUserId();
//        assertEquals(1,noticeMapper.delete(id,userid));
        boolean deleteNumber = noticeMapper.selectAll().get(0).isDelete();
        assertEquals(true, deleteNumber);
        assertEquals(1,noticeMapper.count());

    }


    @Test
    void insertTest()  {
        noticeMapper.deleteAll();
        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));

        noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));
        assertEquals(2, noticeMapper.count());

        noticeMapper.deleteAll();
        noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));
        assertEquals(1, noticeMapper.count());
    }


    @Test
    void selectTest() {
        noticeMapper.deleteAll();
        assertEquals(0, noticeMapper.count());

        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);
        assertEquals(1, noticeMapper.insert(noticeDto));

        Long id = noticeMapper.selectAll().get(0).getId();
        NoticeDto noticeDto2 = noticeMapper.select(id);
        System.out.println(noticeDto2);

        assertNotNull(noticeDto2);
        assertTrue(compareRelevantFields(noticeDto, noticeDto2));
    }


    @Test
    void updateTest()  {
        noticeMapper.deleteAll();
        NoticeDto noticeDto = new NoticeDto("contents",false,"title", 11111L);


        assertEquals(1,noticeMapper.insert(noticeDto));

//        Long id = noticeMapper.selectAll().get(0).getId();
        NoticeDto noticeDto1 = new NoticeDto(1L, "alertContents", true, "yes title");

        System.out.println(noticeMapper.update(noticeDto1));

        assertEquals(1,noticeMapper.update(noticeDto1));

    }

    private boolean compareRelevantFields(NoticeDto expected, NoticeDto actual) {
        return Objects.equals(expected.getContents(), actual.getContents()) &&
                Objects.equals(expected.getTitle(), actual.getTitle()) &&
                Objects.equals(expected.getUserId(), actual.getUserId());
    }
}
=======
package bucheon.leafy.application.mapper;//package bucheon.leafy.application.mapper;
//
//import bucheon.leafy.domain.notice.NoticeDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class NoticeMapperTest {
//
//
//    @Autowired
//    private NoticeMapper noticeMapper;
//    @Test
//    void count()  {
//        noticeMapper.deleteAll();
//        assertEquals(0, noticeMapper.count());
//
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        assertEquals(1, noticeMapper.count());
//
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        assertEquals(2, noticeMapper.count());
//    }
//
//    @Test
//    void deleteAll()  {
//        noticeMapper.deleteAll();
//        assertEquals(0, noticeMapper.count());
//
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//
//        assertEquals(1, noticeMapper.deleteAll());
//
//        noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        assertEquals(1, noticeMapper.insert(noticeDto));
//
//        assertEquals(2, noticeMapper.deleteAll());
//    }
//
//    @Test
//    void deleteTest()  {
//        noticeMapper.deleteAll();
//        assertEquals(0,noticeMapper.count());
//
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        Long id = noticeMapper.selectAll().get(0).getId();
//        assertEquals(1,noticeMapper.delete(id, noticeDto.getUserId()));
//        assertEquals(0,noticeMapper.count());
//
//    }
//
//
//    @Test
//    void insertTest()  {
//        noticeMapper.deleteAll();
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//
//        noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        assertEquals(2, noticeMapper.count());
//
//        noticeMapper.deleteAll();
//        noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1, noticeMapper.insert(noticeDto));
//        assertEquals(1, noticeMapper.count());
//    }
//
//
//    @Test
//    void selectTest () {
//        noticeMapper.deleteAll();
//        assertEquals(0,noticeMapper.count());
//
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals (1,noticeMapper.insert (noticeDto));
//
//        Long id = noticeMapper.selectAll().get(0).getId();
//        List<NoticeDto> noticeDto2 = noticeMapper.select(id);
//        System.out.println(noticeMapper.select(id));
//        System.out.println (noticeDto2);
//
//        assertFalse(noticeDto2.isEmpty());
//        assertTrue(compareRelevantFields(noticeDto, noticeDto2.get(0)));
//    }
//
//    @Test
//    void selectPageTest()  {
//        noticeMapper.deleteAll();
//
//        for (int i = 1; i <= 10; i++) {
//            NoticeDto noticeDto = new NoticeDto(false,"contents",false, ""+i, 11111L);
//            noticeMapper.insert(noticeDto);
//        }
//
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("offset", 0);
//        map.put("pageSize", 3);
//
//        List<NoticeDto> list = noticeMapper.selectPage(map);
//        assertEquals("10", list.get(0).getTitle());
//        assertEquals("9",list.get(1).getTitle());
//        assertEquals("8",list.get(2).getTitle());
//
//        map = new HashMap<>();
//        map.put("offset", 0);
//        map.put("pageSize", 1);
//
//        list = noticeMapper.selectPage(map);
//        assertEquals("10",list.get(0).getTitle());
//
//        map = new HashMap<>();
//        map.put("offset", 7);
//        map.put("pageSize", 3);
//
//        list = noticeMapper.selectPage(map);
//        assertEquals("3",list.get(0).getTitle());
//        assertEquals("2",list.get(1).getTitle());
//        assertEquals("1",list.get(2).getTitle());
//    }
//
//    @Test
//    void updateTest()  {
//        noticeMapper.deleteAll();
//        NoticeDto noticeDto = new NoticeDto(false,"contents",false, "no content", 11111L);
//        assertEquals(1,noticeMapper.insert(noticeDto));
//
//        Long id = noticeMapper.selectAll().get(0).getId();
//        noticeDto.setId(id);
//        noticeDto.setTitle("yes title");
//        noticeDto.setUserId(11111L);
//        assertEquals(1,noticeMapper.update(noticeDto));
//
//    }
//
//    private boolean compareRelevantFields(NoticeDto expected, NoticeDto actual) {
//        return Objects.equals(expected.getContents(), actual.getContents()) &&
//                Objects.equals(expected.getTitle(), actual.getTitle()) &&
//                Objects.equals(expected.getUserId(), actual.getUserId());
//    }
//}
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
