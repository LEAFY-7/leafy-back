package bucheon.leafy.application.service;

<<<<<<< HEAD
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.exception.enums.ReadFailedException;
=======

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchHandler;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD


import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
=======
import java.util.List;
import java.util.Map;

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;

<<<<<<< HEAD
    public int getCount() {
        return qnaMapper.count();
    }
    public int remove(Long id, Long userId) {
        return qnaMapper.delete(id, userId);
    }
    public int adminRemove(Long id) {
        return qnaMapper.adminDelete(id);
    }

    public int write(QnaDto qnaDto) {
        return qnaMapper.insert(qnaDto);
    }

    public List<QnaDto> getList() {
        return qnaMapper.selectAll();
    }

    @Transactional
    public QnaDto getRead(Long id) {
        QnaDto qnaDto = qnaMapper.select(id);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }

        qnaMapper.increaseViewCnt(id);

        return qnaMapper.select(id);
    }
    public int modify(QnaDto qnaDto) {
        return qnaMapper.update(qnaDto);
    }
    public int qnaStatusModify(Long id) {
        return qnaMapper.qnaStatusModify(id);
    }
    public int getSearchResultCnt(SearchHandler searchHandler) {
        return qnaMapper.searchResultCnt(searchHandler);
    }
    public List<QnaDto> getSearchResultPage(SearchHandler searchHandler) {
        return qnaMapper.searchSelectPage(searchHandler);
    }
}
=======
    public int getCount()  {
        return qnaMapper.count();
    }

    public int remove(Long id, Long userId)  {
        int rowCnt = qnaMapper.count();
        rowCnt = qnaMapper.delete( id, userId);

        return qnaMapper.delete( id, userId);
    }

    public int write(QnaDto qnaDto)  {
        return qnaMapper.insert(qnaDto);
    }

    public List<QnaDto> getList()  {
        return qnaMapper.selectAll();
    }

    public List<QnaDto> getRead(Long id) {
        return qnaMapper.select(id);
    }
    public List<QnaDto> getPage(Map<String, Integer> map) {
        return qnaMapper.selectPage(map);
    }
    public int modify(QnaDto qnaDto)  {
        return qnaMapper.update(qnaDto);
    }

    public int getSearchResultCnt(SearchHandler searchHandler)   {
        return qnaMapper.searchResultCnt(searchHandler);
    }

    public List<QnaDto> getSearchSelectPage(SearchHandler searchHandler)   {
        return qnaMapper.searchSelectPage(searchHandler);
    }
    public List<QnaDto> getSearchResultPage(SearchHandler searchHandler)   {
        return qnaMapper.searchSelectPage(searchHandler);
    }
}
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
