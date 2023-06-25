package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.FeedImageService;
import bucheon.leafy.domain.feed.dto.request.FeedImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/feeds/{feedId}")
@RequiredArgsConstructor
public class FeedImageController {

    private final FeedImageService service;

//    @GetMapping
//    public ResponseEntity getImages() {
//        return ResponseEntity.ok().body();
//    }

    @PostMapping("/images")
    public ResponseEntity saveImage(@PathVariable Long feedId, @RequestPart List<MultipartFile> imageList) throws IOException {
        String imagePath = "C:/upload";

        List<FeedImageRequest> requestList = new ArrayList<>();

        for (MultipartFile image : imageList) {
            UUID uuid = UUID.randomUUID();
            String imageName = uuid.toString();


            File uploadImage = new File(imagePath, imageName);

            image.transferTo(uploadImage);

            FeedImageRequest request = FeedImageRequest.builder()
                    .image_id(null)
                    .image(imageName)
                    .feed_id(feedId)
                    .build();

            requestList.add(request);
        }

        service.saveImage(requestList);

        return ResponseEntity.ok().body("이미지 저장 완료");

    }

//    @PutMapping
//    public ResponseEntity updateImage() {
//        return ResponseEntity.ok().body();
//    }

//    @DeleteMapping
//    public ResponseEntity deleteImage() {
//        return ResponseEntity.ok().body();
//    }
}
