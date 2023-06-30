package bucheon.leafy.application.service;


import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.domain.qna.QnaDto;
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
public class QnaService {

    private final QnaMapper qnaMapper;
    private final Logger logger = LoggerFactory.getLogger(QnaService.class);

    public int getCount()  {
        return qnaMapper.count();
    }

    public int remove(Long id, Long userId)  {
        int rowCnt = qnaMapper.count();
        logger.info("count - rowCnt = " + rowCnt);
        rowCnt = qnaMapper.delete( id, userId);
        logger.info("rowCnt = " + rowCnt);

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
