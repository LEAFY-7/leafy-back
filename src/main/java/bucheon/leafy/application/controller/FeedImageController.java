package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "피드 이미지 업로드")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/images")
@RequiredArgsConstructor
public class FeedImageController {

    private final FeedService service;

    @Operation(summary = "피드 이미지 리스트")
    @GetMapping
    public ResponseEntity<List<FeedImageResponse>> getImages(@PathVariable Long feedId) {

        return ResponseEntity.ok().body(service.getFeedImages(feedId));
    }

    @Operation(summary = "피드 이미지 업로드")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable Long feedId, @RequestPart List<MultipartFile> imageList) {
        service.saveImage(feedId, imageList);
        return ResponseEntity.ok().body("업로드 완료");
    }

    @Operation(summary = "피드 이미지 삭제")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable Long feedId, @PathVariable Long imageId) {

        return ResponseEntity.ok().body(service.deleteFeedImage(feedId, imageId));
    }
}