package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedImageService;
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/feeds/{feedId}/images")
@RequiredArgsConstructor
public class FeedImageController {

    private final FeedImageService service;

    @GetMapping
    public ResponseEntity<List<FeedImageResponse>> getImages(@PathVariable Long feedId) {

        return ResponseEntity.ok().body(service.getImages(feedId));
    }

    @PostMapping
    public ResponseEntity<List<FeedImageRequest>> uploadImage(@PathVariable Long feedId, @RequestPart List<MultipartFile> imageFileList,
                                                              @RequestParam List<Boolean> isThumbList) throws IOException {

        return ResponseEntity.ok().body(service.uploadImage(feedId, imageFileList, isThumbList));
    }


    @DeleteMapping("/{imageId}")
    public ResponseEntity deleteImage(@PathVariable Long feedId, @PathVariable Long imageId, @RequestParam String imagePath, @RequestParam String imageName) {

        service.deleteImage(imageId, imagePath, imageName);

        return ResponseEntity.ok().body("이미지 삭제 완료");
    }
}
