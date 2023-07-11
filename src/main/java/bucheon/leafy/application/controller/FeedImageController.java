package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedImageService;
<<<<<<< HEAD
import bucheon.leafy.domain.feed.dto.request.FeedImageRequest;
import bucheon.leafy.domain.feed.dto.response.FeedImageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
>>>>>>> d09b628b834277ae4f8b6d50286d36f4bfa02928
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "피드 이미지 업로드")
@RestController
@RequestMapping("/api/v1/feeds/{feedId}/images")
@RequiredArgsConstructor
public class FeedImageController {

    private final FeedImageService service;

    @Operation(summary = "피드 이미지 가져오기")
    @GetMapping
    public ResponseEntity<List<FeedImageResponse>> getImages(@PathVariable Long feedId) {

        return ResponseEntity.ok().body(service.getImages(feedId));
    }

    @Operation(summary = "피드 이미지 업로드")
    @PostMapping
    public ResponseEntity<List<FeedImageRequest>> uploadImage(@PathVariable Long feedId, @RequestPart List<MultipartFile> imageFileList,
                                              @RequestParam List<Boolean> isThumbList) throws IOException {

        return ResponseEntity.ok().body(service.uploadImage(feedId, imageFileList, isThumbList));
    }


    @Operation(summary = "피드 이미지 삭제")
    @DeleteMapping("/{imageId}")
    public ResponseEntity deleteImage(@PathVariable Long feedId, @PathVariable Long imageId, @RequestParam String imagePath, @RequestParam String imageName) {

        service.deleteImage(imageId, imagePath, imageName);

        return ResponseEntity.ok().body("이미지 삭제 완료");
    }
}
