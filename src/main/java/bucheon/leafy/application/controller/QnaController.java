package bucheon.leafy.application.controller;

<<<<<<< HEAD
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
=======

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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde


@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

<<<<<<< HEAD
    @Operation(summary = "Qna 게시물 수정")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PutMapping("{id}")
    public ResponseEntity<Object> modify(@AuthenticationPrincipal @Parameter(hidden = true)AuthUser user, @RequestBody QnaDto qnaDto, @PathVariable("id") Long id ) {
        return ResponseEntity.ok().body(qnaService.modify(qnaDto, id));
    }

    @Operation(summary = "Qna 게시판 글 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping("")
    public ResponseEntity<Long> write( @AuthenticationPrincipal  @Parameter(hidden = true)AuthUser user,@RequestBody QnaDto qnaDto) {
        return ResponseEntity.ok().body(qnaService.write(qnaDto));
    }

    @Operation(summary = "Qna 게시판 클릭 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@AuthenticationPrincipal  @Parameter(hidden = true)AuthUser user, @PathVariable Long id ) {
        return ResponseEntity.ok().body(qnaService.getRead(id));
    }

    //Mypage이니까 자신만 삭제가능
    @Operation(summary = "Qna 게시판 글 삭제하기")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal  @Parameter(hidden = true)AuthUser user, @PathVariable("id") Long id ) {
            return ResponseEntity.ok().body(qnaService.remove(id));
    }

    @Operation(summary = "Mypage에 자신이 올린 Qna 보여주기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponse<QnaDto>> list(@AuthenticationPrincipal  @Parameter(hidden = true)AuthUser user, PageRequest pageRequest) {
        Long userId = user.getUserId();
        if (user.getAuthorities().contains("ROLE_ADMIN")){
            return ResponseEntity.ok().body(qnaService.admingetList(pageRequest));
        }
        return ResponseEntity.ok().body(qnaService.getList(userId, pageRequest));
    }

    @Operation(summary = "Get Me")
    @GetMapping
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = qnaService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
=======
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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }
}
