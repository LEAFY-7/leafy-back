package bucheon.leafy.application.mapper;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.qna.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class QnaMapperTest extends IntegrationTestSupport {

    @Autowired
    private QnaMapper qnaMapper;


    @Test
    public void count() throws Exception {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==1);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==2);
    }

    @Test
    void deleteAll() throws Exception {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count() == 0);

        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto) == 1);
        assertTrue(qnaMapper.deleteAll() == 1);


        qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto) == 1);
        assertTrue(qnaMapper.insert(qnaDto) == 1);
        assertTrue(qnaMapper.deleteAll() == 2);

    }

    @Test
    public void deleteTest() throws Exception {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        Integer id = qnaMapper.selectAll().get(0).getId();
        assertTrue(qnaMapper.delete(id, qnaDto.getUser_user_id())==1);
        assertTrue(qnaMapper.count()==0);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        id = qnaMapper.selectAll().get(0).getId();
        assertTrue(qnaMapper.delete(id, qnaDto.getUser_user_id())==0);
        assertTrue(qnaMapper.count()==1);

        assertTrue(qnaMapper.delete(id, qnaDto.getUser_user_id())==1);
        assertTrue(qnaMapper.count()==0);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        id = qnaMapper.selectAll().get(0).getId();
        assertTrue(qnaMapper.delete(id+1, qnaDto.getUser_user_id())==0);
        assertTrue(qnaMapper.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        qnaMapper.deleteAll();
        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);

        qnaDto = new QnaDto(1,"no title", "no content", 22222);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==2);

        qnaMapper.deleteAll();
        qnaDto = new QnaDto(1,"no title", "no content", 33333);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        assertTrue(qnaMapper.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        List<QnaDto> list = qnaMapper.selectAll();
        assertTrue(list.size() == 0);

        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);

        list = qnaMapper.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(qnaMapper.insert(qnaDto)==1);
        list = qnaMapper.selectAll();
        assertTrue(list.size() == 2);
    }

    @Test
    public void selectTest() throws Exception {
        qnaMapper.deleteAll();
        assertTrue(qnaMapper.count()==0);

        QnaDto qnaDto = new QnaDto(1,"title1","aasdfsdf",2);
        assertTrue(qnaMapper.insert(qnaDto)==1);
        System.out.println(qnaMapper.count());

        Integer id = qnaMapper.selectAll().get(0).getId();
        QnaDto qnaDto2 = qnaMapper.select(id);
        System.out.println(qnaMapper.select(id));
        System.out.println(qnaDto2);
        assertTrue(qnaDto.equals(qnaDto2));
    }

    @Test
    public void selectPageTest() throws Exception {
        qnaMapper.deleteAll();

        for (int i = 1; i <= 10; i++) {
            QnaDto qnaDto = new QnaDto(1,"no title"+i, "no content", 11111);
            qnaMapper.insert(qnaDto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<QnaDto> list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = qnaMapper.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void updateTest() throws Exception {
        qnaMapper.deleteAll();
        QnaDto qnaDto = new QnaDto(1,"no title", "no content", 11111);
        assertTrue(qnaMapper.insert(qnaDto)==1);

        Integer id = qnaMapper.selectAll().get(0).getId();
        qnaDto.setId(id);
        qnaDto.setTitle("yes title");
        qnaDto.setUser_user_id(11111);
        assertTrue(qnaMapper.update(qnaDto)==1);


        QnaDto qnaDto1 = qnaMapper.select(id);;
        assertTrue(qnaDto.equals(qnaDto1));
    }

}
