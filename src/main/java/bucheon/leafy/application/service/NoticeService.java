//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.NoticeMapper;
//import bucheon.leafy.domain.notice.NoticeDto;
//import bucheon.leafy.domain.qna.SearchCondition;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class NoticeService {
//
//    private final NoticeMapper noticeMapper;
//
//    public int remove(Integer id, String userId) throws Exception {
//        return noticeMapper.delete(id, userId);
//    }
//
//    public int write(NoticeDto noticeDto) throws Exception {
//        return noticeMapper.insert(noticeDto);
//    }
//
//    public int userId(NoticeDto noticeDto) throws Exception {
//        return noticeMapper.insert(noticeDto);
//    }
//    public List<NoticeDto> getList() throws Exception {
//        return noticeMapper.selectAll();
//    }
//    public NoticeDto read(Integer id) throws Exception {
//        NoticeDto noticeDto = noticeMapper.select(id);
//        noticeMapper.increaseViewCnt(id);
//        return noticeDto;
//    }
//    public List<NoticeDto> getPage(Map map) throws Exception {
//        return noticeMapper.selectPage(map);
//    }
//    public int modify(NoticeDto noticeDto) throws Exception {
//        return noticeMapper.update(noticeDto);
//    }
//
//    public List<NoticeDto> getsearchSelectPage(SearchCondition sc) throws Exception {
//        return noticeDto.searchSelectPage(sc);
//    }
//}
