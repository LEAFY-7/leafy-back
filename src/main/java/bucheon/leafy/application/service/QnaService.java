package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;

import bucheon.leafy.application.repository.QnaRepository;
import bucheon.leafy.domain.qna.Qna;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.MyPageQnaResponse;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    private final QnaRepository qnaRepository;

    public boolean remove(Long qnaId) {
        boolean deleteStatus = qnaMapper.deleteById(qnaId);
        if (!deleteStatus) {
            throw new RemoveFailedException();
        }
        return true;
    }

    public QnaSaveResponse write(Long userId, QnaSaveRequest qnaSaveRequest) {

        if (qnaMapper.save(userId, qnaSaveRequest) != 1) {
            throw new WriteFailedException();
        }
        QnaSaveResponse qnaSaveResponse = qnaMapper.savefind(userId, qnaSaveRequest);

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


        if (qnaMapper.editById(qnaId, qnaEditRequest) != 1) {
            throw new WriteFailedException();
        }
        QnaEditResponse qnaEditResponse = qnaMapper.eidtfind(qnaEditRequest);
        return qnaEditResponse;
    }

    public Long getQnaById( Long qnaId) {
        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
    }

    public List<MyPageQnaResponse> getQnasByUserId(Long userId) {
        List<Qna> qnas = qnaRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
        return qnas.stream()
                .map(MyPageQnaResponse::of)
                .collect(Collectors.toList());
    }


}