package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.SearchHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    public int getCount()  {
        return noticeMapper.count();
    }

    public int remove(Long id, Long userId)  {
        int rowCnt = noticeMapper.count();
        logger.info("count - rowCnt = " + rowCnt);
        rowCnt = noticeMapper.delete( id, userId);
        logger.info("rowCnt = " + rowCnt);

        return noticeMapper.delete( id, userId);
    }

    public int write(NoticeDto noticeDto)  {
        return noticeMapper.insert(noticeDto);
    }

    public List<NoticeDto> getList()  {
        return noticeMapper.selectAll();
    }

    public List<NoticeDto> getRead(Long id) {
        return noticeMapper.select(id);
    }
    public List<NoticeDto> getPage(Map<String, Integer> map) {
        return noticeMapper.selectPage(map);
    }
    public int modify(NoticeDto noticeDto)  {
        return noticeMapper.update(noticeDto);
    }

}
