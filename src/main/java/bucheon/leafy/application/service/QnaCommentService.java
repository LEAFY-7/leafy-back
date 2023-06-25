//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.QnaCommentMapper;
//
//import bucheon.leafy.domain.qna.QnaCommentDto;
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
//public class QnaCommentService {
//
//    private final QnaCommentMapper qnaCommentMapper;
//
//    public int remove(Integer id, String userId) throws Exception {
//        return qnaCommentMapper.delete(id, userId);
//    }
//
//    public int write(QnaCommentDto qnaCommentMapper) throws Exception {
//        return qnaCommentMapper.insert(qnaCommentMapper);
//    }
//
//    public int userId(QnaCommentDto qnaCommentMapper) throws Exception {
//        return qnaCommentMapper.insert(qnaCommentMapper);
//    }
//    public List<QnaCommentDto> getList() throws Exception {
//        return qnaCommentMapper.selectAll();
//    }
//
//    public List<QnaCommentDto> getPage(Map map) throws Exception {
//        return qnaCommentMapper.selectPage(map);
//    }
//    public int modify(QnaCommentDto qnaCommentDto) throws Exception {
//        return qnaCommentMapper.update(qnaCommentDto);
//    }
//
//
//    public List<QnaCommentDto> getsearchSelectPage(SearchCondition sc) throws Exception {
//        return qnaCommentMapper.searchSelectPage(sc);
//    }
//}
