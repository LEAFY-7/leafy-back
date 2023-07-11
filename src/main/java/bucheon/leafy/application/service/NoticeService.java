package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.exception.enums.ReadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final ReentrantLock lock = new ReentrantLock();

    public int getCount()  {
        return noticeMapper.count();
    }

    public int remove(Long id, Long userId)  {return noticeMapper.delete( id, userId); }

    public int write(NoticeDto noticeDto)  {
        return noticeMapper.insert(noticeDto);
    }

    public List<NoticeDto> getList()  {
        return noticeMapper.selectAll();
    }

    public NoticeDto getRead(Long id) {

        lock.lock();
        try {
            NoticeDto noticeDto = noticeMapper.select(id);
            noticeMapper.increaseViewCnt(id);

            if (noticeDto == null) {
                throw new ReadFailedException();
            }

            return noticeDto;
        } finally {
            lock.unlock();
        }
    }

    public int modify(NoticeDto noticeDto)  {
        return noticeMapper.update(noticeDto);
    }

    public List<NoticeDto> getPage(Map map) throws Exception { return noticeMapper.selectPage(map);
    }

}
