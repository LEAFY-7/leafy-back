package bucheon.leafy.application.component;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageComponent {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    public void uploadImage(String imagePath, MultipartFile image, String imageName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());

        try(InputStream inputStream = image.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, imagePath + imageName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
        }
    }

    public List<String> uploadImages(String imagePath, List<MultipartFile> imageList) throws IOException {

        List<String> imageNameList = new ArrayList<>();

        for (MultipartFile image : imageList) {

            String imageName = createUUID();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getSize());
            metadata.setContentType(image.getContentType());

            try(InputStream inputStream = image.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket, imagePath + imageName, inputStream, metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
            }

            imageNameList.add(imageName);
        }

        return imageNameList;
    }

    public void deleteImage(String imagePath, String imageName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, imagePath + imageName));
    }

    public void deleteImages(String imagePath, List<String> imageNameList) {
        for(String imageName : imageNameList) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, imagePath + imageName));
        }
    }

    public String createUUID() {
        return UUID.randomUUID().toString();
    }

}