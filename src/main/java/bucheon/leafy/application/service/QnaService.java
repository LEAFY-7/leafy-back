package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;

import bucheon.leafy.application.repository.QnaRepository;
import bucheon.leafy.config.AuthUser;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    private final QnaRepository qnaRepository;

    public void remove(Long qnaId, AuthUser user) {
         Long userId = user.getUserId();
        QnaResponse result = qnaMapper.findQnaById(qnaId);
         if(result == null){ throw new NoticeNotFoundException(); }


         if(result.getUserId() != user.getUserId()){ throw new QnaNotFoundException(); }

        if(qnaMapper.deleteById(qnaId) != 1){
            throw new RemoveFailedException();
        }

    }

    public QnaSaveResponse write( QnaSaveRequest qnaSaveRequest, AuthUser user) {
        Long userId = user.getUserId();

        if (qnaMapper.save(userId, qnaSaveRequest) != 1) { throw new WriteFailedException(); }

        QnaSaveResponse qnaSaveResponse = qnaMapper.savefind(userId);

        return qnaSaveResponse;
    }
    public PageResponse getList(AuthUser user,PageRequest pageRequest){
        Long userId = user.getUserId();

        List<PageResponse> list ;
        long total ;

         if (user == null || user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            list = qnaMapper.adminSelectAll(pageRequest);
            total = qnaMapper.adminCount();

        }else{

             list = qnaMapper.pageFindById(userId ,pageRequest);
             total = qnaMapper.count(userId);

         }

        return PageResponse.of(pageRequest, list, total);
    }
    
    public QnaResponse getRead(Long qnaId) {

        QnaResponse qnaResponse = qnaMapper.findById(qnaId);

        if (qnaResponse == null) {
            throw new ReadFailedException();
        }
        qnaMapper.viewCnt(qnaId);

        return qnaMapper.findById(qnaId);
    }
    public QnaEditResponse modify(Long qnaId, QnaEditRequest qnaEditRequest, AuthUser user) {
        Long userId = user.getUserId();

        if (qnaMapper.editById(qnaId, qnaEditRequest, userId) != 1) {
            throw new WriteFailedException();
        }
        QnaEditResponse qnaEditResponse = qnaMapper.eidtfind(userId);
        return qnaEditResponse;
    }

//    public QnaResponse getQnaById( Long qnaId) {
//        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
//    }

    public List<MyPageQnaResponse> getQnasByUserId(Long userId) {
        List<Qna> qnas = qnaRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
        return qnas.stream()
                .map(MyPageQnaResponse::of)
                .collect(Collectors.toList());
    }


}