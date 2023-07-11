package bucheon.leafy.application.controller;


import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
import bucheon.leafy.domain.qna.PageHandler;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.ReadFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    private final CommentService commentService;


    @Operation(summary = "Qna 게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("/modify/{id}")
    public ResponseEntity<Object> modify(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody QnaDto qnaDto,
            @RequestParam("searchHandler") SearchHandler searchHandler
    ) {
        Long userId = user.getUserId();
        qnaDto.setUserId(userId);

        int modificationResult = qnaService.modify(qnaDto);

        if (modificationResult != 1) {
            Map<String, Object> result = new HashMap<>();
            result.put("ModifyFailedException", new ModifyFailedException());
            result.put("searchHandler.getQueryString()", searchHandler.getQueryString());
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok().body(modificationResult);
        }
    }

    @Operation(summary = "Qna 게시판 글 하나 클릭해서 읽기")
    @GetMapping("/read/{id}")
    public ResponseEntity<Map<String, Object>> read(@AuthenticationPrincipal AuthUser user,
                                                    @PathVariable("id") Long id,
                                                    SearchHandler searchHandler) {

        QnaDto qnaDto = qnaService.getRead(id);
        Long qnaId = commentService.getCommentsByQnaId(id);
        List<CommentDto> commentList    = commentService.getRead(qnaId);
        if (qnaDto != null) {

            Map<String, Object> result = new HashMap<>();
            result.put("commentList",commentList);
            result.put("qnaDto", qnaDto);

            return ResponseEntity.ok().body(result);
        } else {
            throw new ReadFailedException();
        }
    }


    @Operation(summary = "Write Qna board")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping("/write")
    public ResponseEntity<String> write(@AuthenticationPrincipal AuthUser user,
                                        @RequestBody QnaDto qnaDto) {

        qnaService.write(qnaDto);
        return ResponseEntity.status(200).body("게시판 글쓰기가 완성되었습니다.");

    }

    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> remove(
            @AuthenticationPrincipal AuthUser user,
            SearchHandler searchHandler,
            @PathVariable("id") Long id
    ) {
        Long userId = user.getUserId();
        int removeResult = qnaService.remove(id, userId);

        if(removeResult != 1){
            throw new RemoveFailedException();
        }else{
            Map<String, Object> result = new HashMap<>();
            result.put("removeResult",removeResult);
            result.put("searchHandler.getQueryString()",searchHandler.getQueryString());
            return ResponseEntity.ok().body(result);
        }

    }

    @Operation(summary = "Qna 게시판 글 삭제하기 (관리자)")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/adminRemove/{id}")
    public ResponseEntity<Object> adminRemove(
            @AuthenticationPrincipal AuthUser user,
            SearchHandler searchHandler,
            @PathVariable("id") Long id
    ) {
        int adminRemoveResult = qnaService.adminRemove(id);

        Map<String, Object> result = new HashMap<>();
        result.put("adminRemoveResult", adminRemoveResult);
        result.put("searchHandler.getQueryString()", searchHandler.getQueryString());

        return ResponseEntity.ok().body(result);
    }


    @Operation(summary = "Qna 게시판 전체 리스트 보여주기")
    @GetMapping("/list/{id}")
    public ResponseEntity<Object> list(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable("id") Long id,
            SearchHandler searchHandler
    ) {

        int totalCnt = qnaService.getSearchResultCnt(searchHandler);
        PageHandler pageHandler = new PageHandler(totalCnt, searchHandler);
        List<QnaDto> list = qnaService.getSearchResultPage(searchHandler);

        Map<String, Object> result = new HashMap<>();
        result.put("totalCnt", totalCnt);
        result.put("list", list);
        result.put("pageHandler", pageHandler);

        return ResponseEntity.ok().body(result);
    }
}
