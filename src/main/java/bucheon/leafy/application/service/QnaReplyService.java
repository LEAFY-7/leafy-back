//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.QnaMapper;
//import bucheon.leafy.application.mapper.QnaReplyMapper;
//import bucheon.leafy.domain.qnareply.QnaReplyDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class QnaReplyService  {
//
//    @Autowired
//    private final QnaMapper qnaMapper ;
//    @Autowired
//    private final QnaReplyMapper qnaReplyMapper;
//
//
//    public int remove(Integer cid, Integer bno, String user_id) throws Exception {
//        int rowCnt = qnaMapper.updateCommentCntQna(bno, -1);
//        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//        rowCnt = qnaReplyMapper.delete(cid, user_id);
//        System.out.println("rowCnt = " + rowCnt);
//        return rowCnt;
//    }
//
//    public int write(QnaReplyDto qnaReplyDto) throws Exception {
//        qnaMapper.updateCommentCntQna(qnaReplyDto.getId(),1);
////                throw new Exception("test");
//        return qnaReplyMapper.insert(qnaReplyDto);
//    }
//
//    public List<QnaReplyDto> getList(Integer cid) throws Exception {
////        throw new Exception("test");
//        return qnaReplyMapper.selectAll(cid);
//    }
//
//    public QnaReplyDto read(Integer cid) throws Exception {
//        return qnaReplyMapper.select(cid);
//    }
//
//    public int modify(QnaReplyDto qnaReplyDto) throws Exception {
//        return qnaReplyMapper.update(qnaReplyDto);
//    }
//}
