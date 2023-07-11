package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.exception.enums.ReadFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;

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