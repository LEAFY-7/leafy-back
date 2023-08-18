package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Tag(name = "테스트")
@RestController
@RequestMapping("/api/v1/lets-go")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @Operation(summary = "가즈아!!")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void letsGo(@RequestPart List<MultipartFile> files) {
        try {
            testService.createDummyFeed(files);
        } catch (IOException e) {
            log.info("IOException 발생 : {}", e.getMessage());
        }
    }

}
