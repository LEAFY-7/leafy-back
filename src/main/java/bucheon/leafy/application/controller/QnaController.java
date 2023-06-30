package bucheon.leafy.application.controller;


import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.qna.PageHandler;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.application.service.QnaService;
import bucheon.leafy.domain.qna.QnaDto;

import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.enums.ReadFailedException;
import bucheon.leafy.exception.enums.RemoveFailedException;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/v1/Qna")
@RequiredArgsConstructor
public class QnaController {

    private QnaService qnaService;
    @Operation(summary = "Qna게시판 수정하기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/modify/{id}")
    public ResponseEntity<Object> modify(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody QnaDto qnaDto,
            SearchHandler searchHandler,
            @PathVariable("id") Long id
    ) {
        Long userId = user.getUserId();
        qnaDto.setUserId(userId);

        if (qnaService.modify(qnaDto) != 1) {
            throw new ModifyFailedException();
        } else {
            return ResponseEntity.ok().body(qnaService.modify(qnaDto));
        }
    }

    @Operation(summary = "Qna게시판 글 하나 클릭해서 읽기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @GetMapping("/read/{id}")
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
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/remove/{id}")
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
    @GetMapping("/list")
    public ResponseEntity<List<QnaDto>> list(
            SearchHandler searchHandler
    ) {

        int totalCnt = qnaService.getSearchResultCnt(searchHandler);
        PageHandler pageHandler = new PageHandler(totalCnt, searchHandler);

        List<QnaDto> qnaList = new ArrayList<>();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(totalCnt))
                .header("X-Page-Handler", pageHandler.toString())
                .body(qnaList);
    }

    @Operation(summary = "Notice게시판 쓰기")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
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


    @Operation(summary = "Qna게시판 로그인 체크")
    @PreAuthorize("hasAnyRole('MEMBER', 'ADMIN')")
    @PostMapping("/logincheck")
    public ResponseEntity<String> logincheck(@AuthenticationPrincipal AuthUser user,@PathVariable("id") Long userId) {
        if (user != null) {
            return ResponseEntity.ok("User is logged in");
        } else {
            return ResponseEntity.ok("User is not logged in");
        }
    }
}


