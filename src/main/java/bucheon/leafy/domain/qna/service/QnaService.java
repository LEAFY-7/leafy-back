package bucheon.leafy.domain.qna.service;

import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface QnaService {
    int getCount() throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    int write(QnaDto boardDto) throws Exception;
    List<QnaDto> getList() throws Exception;
    QnaDto read(Integer bno) throws Exception;
    List<QnaDto> getPage(Map map) throws Exception;
    int modify(QnaDto boardDto) throws Exception;
    int getsearchResultCntQna(SearchCondition sc) throws Exception;
    List<QnaDto> getsearchSelectPageQna(SearchCondition sc) throws Exception;
}
