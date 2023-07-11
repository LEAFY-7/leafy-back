package bucheon.leafy.application.controller;


<<<<<<< HEAD
import bucheon.leafy.application.service.CommentService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.comment.CommentDto;
=======
import bucheon.leafy.config.AuthUser;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import bucheon.leafy.domain.qna.PageHandler;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
<<<<<<< HEAD
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.ReadFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
=======

import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.ReadFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======


import java.util.ArrayList;
import java.util.List;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57



@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {
<<<<<<< HEAD
    private final QnaService qnaService;

    private final CommentService commentService;


    @Operation(summary = "Qna 게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("/modify/{id}")
    public ResponseEntity<Object> modify(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody QnaDto qnaDto,
            @RequestParam("searchHandler") SearchHandler searchHandler
=======

    private QnaService qnaService;
    @Operation(summary = "Qna게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> modify(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody QnaDto qnaDto,
            SearchHandler searchHandler,
            @PathVariable("id") Long id
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    ) {
        Long userId = user.getUserId();
        qnaDto.setUserId(userId);

<<<<<<< HEAD
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
=======
        if (qnaService.modify(qnaDto) != 1) {
            throw new ModifyFailedException();
        } else {
            return ResponseEntity.ok().body(qnaService.modify(qnaDto));
        }
    }

    @Operation(summary = "Qna게시판 글 하나 클릭해서 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable("id") Long id
    ) {
        Long userId = user.getUserId();
        QnaDto qnaDto = new QnaDto();
        qnaDto.setUserId(userId);

        if (qnaService.write(qnaDto) != 1) {
            throw new ReadFailedException();
        } else {
            return ResponseEntity.ok().body(qnaService.write(qnaDto));
        }
    }


    @Operation(summary = "Qna게시판 글 삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(
            @AuthenticationPrincipal AuthUser user,
            SearchHandler searchHandler,
            @PathVariable("id") Long userId
    ) {
        Long id = user.getUserId();

        if (qnaService.remove(id, userId) != 1) {
            throw new RemoveFailedException();
        } else {
            return ResponseEntity.ok().body("삭제가 성공했습니다");
        }
    }

    @Operation(summary = "Qna게시판 전체 리스트 보여주기")
    @GetMapping
    public ResponseEntity<List<QnaDto>> list(
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
            SearchHandler searchHandler
    ) {

        int totalCnt = qnaService.getSearchResultCnt(searchHandler);
        PageHandler pageHandler = new PageHandler(totalCnt, searchHandler);
<<<<<<< HEAD
        List<QnaDto> list = qnaService.getSearchResultPage(searchHandler);

        Map<String, Object> result = new HashMap<>();
        result.put("totalCnt", totalCnt);
        result.put("list", list);
        result.put("pageHandler", pageHandler);

        return ResponseEntity.ok().body(result);
    }
}
=======

        List<QnaDto> qnaList = new ArrayList<>();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(totalCnt))
                .header("X-Page-Handler", pageHandler.toString())//body
                .body(qnaList);
    }

    @Operation(summary = "Qna게시판 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping("/write")
    public ResponseEntity<List<QnaDto>> write(QnaDto qnaDto,
                                              @RequestParam(required = false) Long userId,
                                              SearchHandler searchHandler) {

        qnaDto.setUserId(userId);

        int insertResult = qnaService.write(qnaDto);
        List<QnaDto> result = new ArrayList<>();
        if (insertResult != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

        result.add(qnaDto);
        return ResponseEntity.ok().body(result);
    }

}


>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
