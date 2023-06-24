package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;
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
        return qnaMapper.countQna();
    }
    public int remove(Integer id, String userId) throws Exception {
        return qnaMapper.deleteQna(id, userId);
    }

    @Override
    public int write(QnaDto QnaDto) throws Exception {
        return qnaMapper.insertQna(QnaDto);
    }

    public int userId(QnaDto qnaDto) throws Exception {
        return qnaMapper.insertQna(qnaDto);
    }
    public List<QnaDto> getList() throws Exception {
        return qnaMapper.selectAllQna();
    }
    public QnaDto read(Integer id) throws Exception {
        QnaDto qnaDto = qnaMapper.selectQna(id);
        qnaMapper.increaseViewCntQna(id);
        return qnaDto;
    }
    public List<QnaDto> getPage(Map map) throws Exception {
        return qnaMapper.selectPageQna(map);
    }
    public int modify(QnaDto qnaDto) throws Exception {
        return qnaMapper.updateQna(qnaDto);
    }

    @Override
    public int getsearchResultCntQna(SearchCondition sc) throws Exception {
        return qnaMapper.searchResultCntQna(sc);
    }

    @Override
    public List<QnaDto> getsearchSelectPageQna(SearchCondition sc) throws Exception {
        return qnaMapper.searchSelectPageQna(sc);
    }
}
