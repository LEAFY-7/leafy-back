//package bucheon.leafy.application.service;

//import bucheon.leafy.application.mapper.QnaMapper;
//import bucheon.leafy.domain.qna.QnaDto;
//import bucheon.leafy.domain.qna.SearchCondition;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class QnaService {
//
//    private final QnaMapper qnaMapper;
//
//    public int getCount() throws Exception {
//        return qnaMapper.count();
//    }
//    public int remove(Integer id, String userId) throws Exception {
//        return qnaMapper.delete(id, userId);
//    }
//
//    public int write(QnaDto QnaDto) throws Exception {
//        return qnaMapper.insert(QnaDto);
//    }
//
//    public int userId(QnaDto qnaDto) throws Exception {
//        return qnaMapper.insert(qnaDto);
//    }
//    public List<QnaDto> getList() throws Exception {
//        return qnaMapper.selectAll();
//    }
//    public QnaDto read(Integer id) throws Exception {
//        QnaDto qnaDto = qnaMapper.select(id);
//        qnaMapper.increaseViewCnt(id);
//        return qnaDto;
//    }
//    public List<QnaDto> getPage(Map map) throws Exception {
//        return qnaMapper.selectPage(map);
//    }
//    public int modify(QnaDto qnaDto) throws Exception {
//        return qnaMapper.update(qnaDto);
//    }
//
//    public List<QnaDto> getsearchSelectPage(SearchCondition sc) throws Exception {
//        return qnaMapper.searchSelectPage(sc);
//    }
//}
