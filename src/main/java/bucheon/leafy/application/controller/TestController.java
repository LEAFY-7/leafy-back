package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "테스트")
@RestController
@RequestMapping("/api/v1/lets-go")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    // admin 으로 만
    @Operation(summary = "가즈아!!")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void letsGo(@RequestPart List<MultipartFile> files) {
        testService.createDummyFeed(files);
    }

}
