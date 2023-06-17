package bucheon.leafy.domain.qna.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaMapper qnaMapper;

    @Override
    public int remove(Integer id, String userId) throws Exception {
        return qnaMapper.deleteQna(id, userId);
    }

    @Override
    public int write(QnaDto QnaDto) throws Exception {
        return qnaMapper.insertQna(QnaDto);
    }

    @Override
    public List<QnaDto> getList() throws Exception {
        return qnaMapper.selectAllQna();
    }

    @Override
    public QnaDto read(Integer id) throws Exception {
        QnaDto qnaDto = qnaMapper.selectQna(id);
        qnaMapper.increaseViewCntQna(id);
        return qnaDto;
    }

    @Override
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
