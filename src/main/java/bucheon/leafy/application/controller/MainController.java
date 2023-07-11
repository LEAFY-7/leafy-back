package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메인 페이지")
@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainController {

    private final FeedCommentService feedCommentService;

    @Operation(summary = "메인 페이지 불러오기")
    @GetMapping
    public ResponseEntity getMain() {
        return null;
    }

}
