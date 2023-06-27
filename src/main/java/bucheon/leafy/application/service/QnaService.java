package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;

    public int getCount() throws Exception {
        return qnaMapper.count();
    }

    public int remove(Integer id, Integer user_user_id) throws Exception {
        int rowCnt = qnaMapper.count();
        System.out.println("count - rowCnt = " + rowCnt);
        rowCnt = qnaMapper.delete(id, user_user_id);
        System.out.println("rowCnt = " + rowCnt);

        return qnaMapper.delete(id, user_user_id);
    }

    @Override
    public int write(QnaDto QnaDto) throws Exception {
        return qnaMapper.insertQna(QnaDto);
    }

    public List<QnaDto> getList() throws Exception {
        return qnaMapper.selectAll();
    }

    public QnaDto read(Integer id) throws Exception {
        QnaDto qnaDto = qnaMapper.select(id);
        return qnaDto;
    }

    public List<QnaDto> getPage(Map map) throws Exception {
        return qnaMapper.selectPage(map);
    }

    public int modify(QnaDto qnaDto) throws Exception {
        return qnaMapper.update(qnaDto);
    }

    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return qnaMapper.searchResultCnt(sc);
    }

    public List<QnaDto> getSearchSelectPage(SearchCondition sc) throws Exception {
        return qnaMapper.searchSelectPage(sc);
    }
}
