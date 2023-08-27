package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;

import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.QnaEditResponse;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;

    public boolean remove(Long qnaId) {
        boolean deleteStatus = qnaMapper.deleteById(qnaId);
        if (!deleteStatus) {
            throw new RemoveFailedException();
        }
        return true;
    }

    public QnaSaveResponse write(Long userId, QnaSaveRequest qnaSaveRequest) {
        QnaSaveResponse qnaSaveResponse = qnaMapper.save(userId ,qnaSaveRequest);

        if (qnaSaveResponse == null) {
            throw new WriteFailedException();
        }
        return qnaSaveResponse;
    }

    public PageResponse admingetList(PageRequest pageRequest){
        List<PageResponse> list = qnaMapper.adminSelectAll(pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);
        return pageResponse;
    }
    public PageResponse getList(Long qnaId, Long userId, PageRequest pageRequest){
        List<PageResponse> list = qnaMapper.pageFindById(qnaId, userId, pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;

    }

    @Transactional
    public QnaResponse getRead(Long qnaId) {

        QnaResponse qnaResponse = qnaMapper.findById(qnaId);

        if (qnaResponse == null) {
            throw new ReadFailedException();
        }
        qnaMapper.viewCnt(qnaId);
        qnaMapper.editByIdQnaStatus(qnaId);

        return qnaMapper.findById(qnaId);
    }
    public QnaEditResponse modify(Long qnaId, QnaEditRequest qnaEditRequest) {
        return qnaMapper.editById(qnaId, qnaEditRequest );
    }

    public Long getQnaById( Long qnaId) {
        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
    }


}