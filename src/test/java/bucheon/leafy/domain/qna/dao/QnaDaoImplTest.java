package bucheon.leafy.domain.qna.dao;

import bucheon.leafy.domain.qna.domain.QnaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaDaoImplTest {

    @Autowired
    private QnaDao qnaDao;
    @Test
    public void count() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.count()==1);

       assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.count()==2);
    }

    @Test
    public void deleteAllTest() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.deleteAll()==1);
        assertTrue(qnaDao.count()==0);

        qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.deleteAll()==2);
        assertTrue(qnaDao.count()==0);
    }

    @Test
    public void deleteTest() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        Integer bno = qnaDao.selectAll().get(0).getBno();
        assertTrue(qnaDao.delete(bno, qnaDto.getUser_id())==1);
        assertTrue(qnaDao.count()==0);

        assertTrue(qnaDao.insert(qnaDto)==1);
        bno = qnaDao.selectAll().get(0).getBno();
        assertTrue(qnaDao.delete(bno, qnaDto.getUser_id()+"222")==0);
        assertTrue(qnaDao.count()==1);

        assertTrue(qnaDao.delete(bno, qnaDto.getUser_id())==1);
        assertTrue(qnaDao.count()==0);

        assertTrue(qnaDao.insert(qnaDto)==1);
        bno = qnaDao.selectAll().get(0).getBno();
        assertTrue(qnaDao.delete(bno+1, qnaDto.getUser_id())==0);
        assertTrue(qnaDao.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        qnaDao.deleteAll();
        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);

        qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.count()==2);

        qnaDao.deleteAll();
        qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        List<QnaDto> list = qnaDao.selectAll();
        assertTrue(list.size() == 0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);

        list = qnaDao.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(qnaDao.insert(qnaDto)==1);
        list = qnaDao.selectAll();
        assertTrue(list.size() == 2);
    }

    @Test
    public void selectTest() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);

        Integer bno = qnaDao.selectAll().get(0).getBno();
        qnaDto.setBno(bno);
        QnaDto qnaDto2 = qnaDao.select(bno);
        assertTrue(qnaDto.equals(qnaDto2));
    }

    @Test
    public void selectPageTest() throws Exception {
        qnaDao.deleteAll();

        for (int i = 1; i <= 10; i++) {
            QnaDto qnaDto = new QnaDto(1+i, "no content"+i, "asdf");
            qnaDao.insert(qnaDto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<QnaDto> list = qnaDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = qnaDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = qnaDao.selectPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void updateTest() throws Exception {
        qnaDao.deleteAll();
        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);

        Integer bno = qnaDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);
        qnaDto.setBno(bno);
        qnaDto.setTitle("yes title");
        assertTrue(qnaDao.update(qnaDto)==1);

        QnaDto qnaDto2 = qnaDao.select(bno);
        assertTrue(qnaDto.equals(qnaDto2));
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        qnaDao.deleteAll();
        assertTrue(qnaDao.count()==0);

        QnaDto qnaDto = new QnaDto(1, "no content", "asdf");
        assertTrue(qnaDao.insert(qnaDto)==1);
        assertTrue(qnaDao.count()==1);

        Integer bno = qnaDao.selectAll().get(0).getBno();
        assertTrue(qnaDao.increaseViewCnt(bno)==1);

        qnaDto = qnaDao.select(bno);
        assertTrue(qnaDto!=null);
        assertTrue(qnaDto.getView_cnt() == 1);

        assertTrue(qnaDao.increaseViewCnt(bno)==1);
        qnaDto = qnaDao.select(bno);
        assertTrue(qnaDto!=null);
        assertTrue(qnaDto.getView_cnt() == 2);
    }
}
