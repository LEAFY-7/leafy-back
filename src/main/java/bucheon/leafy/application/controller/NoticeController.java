package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.NoticeDto;
<<<<<<< HEAD
import bucheon.leafy.domain.user.response.GetMeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
=======
import bucheon.leafy.domain.qna.SearchHandler;
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
import java.util.List;
=======

import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "Notice 게시판 글 수정하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> modify(
<<<<<<< HEAD
            @RequestBody NoticeDto noticeDto) {
=======
            @AuthenticationPrincipal AuthUser user,
            @RequestBody NoticeDto noticeDto,
            @PathVariable Long id
    ) {
        Long userId = user.getUserId();
        noticeDto.setUserId(userId);

        if (noticeService.modify(noticeDto) != 1) {
            throw new ModifyFailedException();
        }

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        return ResponseEntity.ok().body(noticeService.modify(noticeDto));
    }

    @Operation(summary = "Notice 게시판에 글 작성하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<Long> write(
            @RequestBody NoticeDto noticeDto
    ) {
        return ResponseEntity.ok().body(noticeService.write(noticeDto));
    }

    @Operation(summary = "Notice 게시판 클릭 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read( @PathVariable Long id) {
        return ResponseEntity.ok().body(noticeService.getRead(id));
    }

    @Operation(summary = "Notice 게시판의 전체 글 목록 보기")
=======
    public ResponseEntity<List<NoticeDto>> write(
            @RequestBody NoticeDto noticeDto,
            @AuthenticationPrincipal AuthUser user
    ) {
        Long userId = user.getUserId();
        noticeDto.setUserId(userId);

        int insertResult = noticeService.write(noticeDto);

        if (insertResult != 1) {
           throw new ReadFailedException();
        }
        return ResponseEntity.ok().body(List.of(noticeDto));
    }

    @Operation(summary = "Notice 게시판에서 특정 글 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<Object> read(
            @AuthenticationPrincipal AuthUser user,
            @PathVariable Long id
    ) {
        NoticeDto noticeDto = noticeService.getRead(id); // 게시글 가져오기

        return ResponseEntity.ok().body(noticeDto);
    }


    @Operation(summary = "Notice 게시판의 전체 글 목록 보기")
    @PreAuthorize("hasAnyRole('ADMIN')")
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    @GetMapping
    public ResponseEntity<List<NoticeDto>> list() {
        return ResponseEntity.ok().body(noticeService.getList());
    }

    @Operation(summary = "Notice 게시판 글 삭제하기")
<<<<<<< HEAD
    @PreAuthorize("hasAnyRole('MEMBER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
      //모든관리자가 삭제할수있을려면 userId로 하면 x
        return ResponseEntity.ok().body(noticeService.remove(id));
    }
    @Operation(summary = "Get Me")
    @GetMapping("/me") // 엔드포인트를 /v1/me로 수정합니다.
    public ResponseEntity<GetMeResponse> authorize(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser) {
        Long userId = authUser.getUserId();
        GetMeResponse getMe = noticeService.getMe(userId);
        return ResponseEntity.ok().body(getMe);
=======
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(
            @AuthenticationPrincipal AuthUser user,
            SearchHandler searchHandler,
            @PathVariable("id") Long id
    ) {
        Long userId = user.getUserId();
        int removeResult = noticeService.remove(id, userId);

        if (removeResult != 1) {
            throw new RemoveFailedException();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("removeResult", removeResult);
        result.put("searchHandler.getQueryString()", searchHandler.getQueryString());
        return ResponseEntity.ok().body(result);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }
}
