package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.domain.notice.NoticeDto;
<<<<<<< HEAD
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.exception.enums.ReadFailedException;
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import java.util.concurrent.locks.ReentrantLock;
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
<<<<<<< HEAD
    private final ReentrantLock lock = new ReentrantLock();
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

    public int getCount()  {
        return noticeMapper.count();
    }

<<<<<<< HEAD
    public int remove(Long id, Long userId)  {return noticeMapper.delete( id, userId); }
=======
    public int remove(Long id, Long userId)  {
        int rowCnt = noticeMapper.count();
        rowCnt = noticeMapper.delete( id, userId);
        return noticeMapper.delete( id, userId);
    }
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

    public int write(NoticeDto noticeDto)  {
        return noticeMapper.insert(noticeDto);
    }

    public List<NoticeDto> getList()  {
        return noticeMapper.selectAll();
    }

<<<<<<< HEAD
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

=======
    public List<NoticeDto> getRead(Long id) {
        return noticeMapper.select(id);
    }
    public List<NoticeDto> getPage(Map<String, Integer> map) {
        return noticeMapper.selectPage(map);
    }
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    public int modify(NoticeDto noticeDto)  {
        return noticeMapper.update(noticeDto);
    }

<<<<<<< HEAD
    public List<NoticeDto> getPage(Map map) throws Exception { return noticeMapper.selectPage(map);
    }

=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
}
