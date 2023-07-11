package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.NoticeService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.SearchHandler;
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


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "Notice 게시판 글 수정하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> modify(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody NoticeDto noticeDto,
            @PathVariable Long id
    ) {
        Long userId = user.getUserId();
        noticeDto.setUserId(userId);

        if (noticeService.modify(noticeDto) != 1) {
            throw new ModifyFailedException();
        }

        return ResponseEntity.ok().body(noticeService.modify(noticeDto));
    }

    @Operation(summary = "Notice 게시판에 글 작성하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
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
    @GetMapping
    public ResponseEntity<List<NoticeDto>> list() {
        return ResponseEntity.ok().body(noticeService.getList());
    }

    @Operation(summary = "Notice 게시판 글 삭제하기")
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
    }
}
