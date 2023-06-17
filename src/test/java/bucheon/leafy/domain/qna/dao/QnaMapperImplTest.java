//package bucheon.leafy.domain.qna.dao;
//
//import bucheon.leafy.application.mapper.QnaMapper;
//import bucheon.leafy.domain.qna.domain.QnaDto;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class QnaMapperImplTest {
//
//    @Autowired
//    private QnaMapper qnaMapper;
//    @Test
//    void count() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        Assertions.assertThat(qnaMapper.insertQna(qnaDto)).isEqualTo(1);
//        assertTrue(qnaMapper.countQna() == 1);
//
//       assertTrue(qnaMapper.insertQna(qnaDto)==1);
//       assertTrue(qnaMapper.countQna()==2);
//
//    }
//    @Test
//    public void deleteAll() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.deleteAllQna()==1);
//        assertTrue(qnaMapper.countQna()==0);
//
//        qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.deleteAllQna()==2);
//        assertTrue(qnaMapper.countQna()==0);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        Integer id = qnaMapper.selectAllQna().get(0).getId();
//        assertTrue(qnaMapper.deleteQna(id, qnaDto.getUserId())==1);
//        assertTrue(qnaMapper.countQna()==0);
//
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        id = qnaMapper.selectAllQna().get(0).getId();
//        assertTrue(qnaMapper.deleteQna(id, qnaDto.getUserId()+"222")==0);
//        assertTrue(qnaMapper.countQna()==1);
//
//        assertTrue(qnaMapper.deleteQna(id, qnaDto.getUserId())==1);
//        assertTrue(qnaMapper.countQna()==0);
//
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        id = qnaMapper.selectAllQna().get(0).getId();
//        assertTrue(qnaMapper.deleteQna(id+1, qnaDto.getUserId())==0);
//        assertTrue(qnaMapper.countQna()==1);
//    }
//
//    @Test
//    public void insert() throws Exception {
//        qnaMapper.deleteAllQna();
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//
//        qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.countQna()==2);
//
//        qnaMapper.deleteAllQna();
//        qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.countQna()==1);
//    }
//
//    @Test
//    public void selectAll() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        List<QnaDto> list = qnaMapper.selectAllQna();
//        assertTrue(list.size() == 0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//
//        list = qnaMapper.selectAllQna();
//        assertTrue(list.size() == 1);
//
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        list = qnaMapper.selectAllQna();
//        assertTrue(list.size() == 2);
//    }
//
//    @Test
//    public void select() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//
//        Integer id = qnaMapper.selectAllQna().get(0).getId();
//        qnaDto.setId(id);
//        QnaDto qnaDto2 = qnaMapper.selectQna(id);
//        assertTrue(qnaDto.equals(qnaDto2));
//    }
//
//    @Test
//    public void selectPage() throws Exception {
//        qnaMapper.deleteAllQna();
//
//        for (int i = 1; i <= 10; i++) {
//            QnaDto qnaDto = new QnaDto("1"+i, "no content"+i, "asdf");
//            qnaMapper.insertQna(qnaDto);
//        }
//
//        Map map = new HashMap();
//        map.put("offset", 0);
//        map.put("pageSize", 3);
//
//        List<QnaDto> list = qnaMapper.selectPageQna(map);
//        assertTrue(list.get(0).getTitle().equals("10"));
//        assertTrue(list.get(1).getTitle().equals("9"));
//        assertTrue(list.get(2).getTitle().equals("8"));
//
//        map = new HashMap();
//        map.put("offset", 0);
//        map.put("pageSize", 1);
//
//        list = qnaMapper.selectPageQna(map);
//        assertTrue(list.get(0).getTitle().equals("10"));
//
//        map = new HashMap();
//        map.put("offset", 7);
//        map.put("pageSize", 3);
//
//        list = qnaMapper.selectPageQna(map);
//        assertTrue(list.get(0).getTitle().equals("3"));
//        assertTrue(list.get(1).getTitle().equals("2"));
//        assertTrue(list.get(2).getTitle().equals("1"));
//    }
//
//    @Test
//    public void update() throws Exception {
//        qnaMapper.deleteAllQna();
//        QnaDto qnaDto = new QnaDto("1", "no content", "12");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//
//        Integer id = qnaMapper.selectAllQna().get(0).getId();
//        System.out.println("id = " + id);
//        qnaDto.setId(id);
//        qnaDto.setTitle("yes title");
//        assertTrue(qnaMapper.updateQna(qnaDto)==1);
//
//        QnaDto qnaDto2 = qnaMapper.selectQna(id);
//        assertTrue(qnaDto.equals(qnaDto2));
//    }
//
//    @Test
//    public void increaseViewCnt() throws Exception {
//        qnaMapper.deleteAllQna();
//        assertTrue(qnaMapper.countQna()==0);
//
//        QnaDto qnaDto = new QnaDto("1", "no content", "asdf");
//        assertTrue(qnaMapper.insertQna(qnaDto)==1);
//        assertTrue(qnaMapper.countQna()==1);
//
//        Integer id = qnaMapper.selectAllQna().get(0).getId();
//        assertTrue(qnaMapper.increaseViewCntQna(id)==1);
//
//        qnaDto = qnaMapper.selectQna(id);
//        assertTrue(qnaDto!=null);
//        assertTrue(qnaDto.getViewCnt() == 1);
//
//        assertTrue(qnaMapper.increaseViewCntQna(id)==1);
//        qnaDto = qnaMapper.selectQna(id);
//        assertTrue(qnaDto!=null);
//        assertTrue(qnaDto.getViewCnt() == 2);
//    }
//
//}
