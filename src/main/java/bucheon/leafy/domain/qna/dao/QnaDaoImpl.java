package bucheon.leafy.domain.qna.dao;

import bucheon.leafy.domain.qna.domain.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnaDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "bucheon.leafy.domain.qna.dao.QnaDao.";

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }
    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }
    public QnaDto select(Integer bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    } // T selectOne(String statement, Object parameter)

    @Override
    public int deleteForAdmin(){return session.delete(namespace+"deleteForAdmin");}
    @Override
    public int delete(Integer bno, String user_id) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("user_id", user_id);
        return session.delete(namespace+"delete", map);
    }
    @Override
    public int insert(QnaDto dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    }
    @Override
    public int update(QnaDto dto) throws Exception {
        return session.update(namespace+"update", dto);
    }
    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    } // int update(String statement, Object parameter)

    @Override
    public List<QnaDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public List<QnaDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

}
