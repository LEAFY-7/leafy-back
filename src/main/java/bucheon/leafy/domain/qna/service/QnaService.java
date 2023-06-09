package bucheon.leafy.domain.qna.service;

import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface QnaService {
    int getCount() throws Exception;

    int remove(Integer bno, String user_id) throws Exception;

    int user_id(QnaDto qnaDto) throws Exception;

    List<QnaDto> getList() throws Exception;

    QnaDto read(Integer bno) throws Exception;

    List<QnaDto> getPage(Map map) throws Exception;

    int modify(QnaDto qnaDto) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    List<QnaDto> getSearchResultPage(SearchCondition sc) throws Exception;
}
