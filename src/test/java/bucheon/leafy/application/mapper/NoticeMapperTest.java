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