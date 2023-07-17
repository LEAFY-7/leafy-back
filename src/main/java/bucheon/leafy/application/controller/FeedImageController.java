package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<String>> getImages(@PathVariable Long feedId) {

        return ResponseEntity.ok().body(service.getFeedImages(feedId));
    }

    @PostMapping
    public ResponseEntity<List<String>> uploadImage(@PathVariable Long feedId, @RequestPart List<MultipartFile> imageList) {

        return ResponseEntity.ok().body(service.saveFeedImage(feedId, imageList));
    }


    @DeleteMapping
    public ResponseEntity<String> deleteImage(@PathVariable Long feedId, @RequestParam String imageName) {

        return ResponseEntity.ok().body(service.deleteFeedImage(feedId, imageName));
    }
}