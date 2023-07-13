package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedImageMapper;
import bucheon.leafy.domain.feed.request.FeedImageRequest;
import bucheon.leafy.domain.feed.response.FeedImageResponse;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FeedImageService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final FeedImageMapper mapper;

    public List<FeedImageResponse> getImages(Long feedId) {

        List<FeedImageResponse> responseList =  mapper.findImageList(feedId);

        for(FeedImageResponse response:responseList) {

            String imagePath = response.getImage_path();
            String imageName = response.getImage_name();

            String imageUrl = amazonS3.getUrl(bucket, imagePath + "/" + imageName).toString();

            response.setImageUrl(imageUrl);
        }

        return responseList;
    }

    public List<FeedImageRequest> uploadImage(Long feedId, List<MultipartFile> imageFileList, List<Boolean> isThumbList) {

        List<FeedImageRequest> requestList = new ArrayList<>();

        for (int i=0; i<imageFileList.size(); i++) {

            MultipartFile multipartFile = imageFileList.get(i);
            Boolean isThumb = isThumbList.get(i);

            String imagePath = createFolder();
            String imageUUID = createUUID();
            String imageName = imagePath + "/" + imageUUID;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            try(InputStream inputStream = multipartFile.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, imageName, inputStream, metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
            }

            FeedImageRequest request = FeedImageRequest.builder()
                    .imageName(imageUUID)
                    .imagePath(imagePath)
                    .feedId(feedId)
                    .isThumb(isThumb)
                    .build();

            requestList.add(request);
        }
        mapper.saveImage(requestList);

        return requestList;
    }

    public void deleteImage(Long imageId, String imagePath, String imageName) {

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, imagePath + "/" + imageName));

        mapper.deleteImage(imageId);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    private String createFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }
}
