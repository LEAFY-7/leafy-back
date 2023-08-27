package bucheon.leafy.application.service;

import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.repository.UserBackgroundImageRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserBackgroundImage;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.ExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static bucheon.leafy.exception.enums.ExceptionKey.USER_BACKGROUND_IMAGE;
import static bucheon.leafy.exception.enums.ExceptionKey.USER_IMAGE;
import static bucheon.leafy.path.S3Path.USER_BACKGROUND_IMAGE_PATH;
import static bucheon.leafy.path.S3Path.USER_IMAGE_PATH;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImageService {

    private final UserService userService;

    private final UserImageRepository userImageRepository;

    private final UserBackgroundImageRepository userBackgroundImageRepository;

    private final ImageComponent imageComponent;

    public void createOrUpdateUserImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);

        if(user.getUserImage() == null){
            createUserImage(user, file);
        } else {
            editUserImage(user, file);
        }
    }

    public void createOrUpdateUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);

        if(user.getUserBackgroundImage() == null){
            createUserBackgroundImage(user, file);
        } else {
            editUserBackgroundImage(user, file);
        }
    }

    public void createUserImage(User user, MultipartFile file) {
        String renamedFile = imageComponent.createUUID();

        UserImage userImage = UserImage.of(renamedFile, user);
        userImageRepository.save(userImage);

        imageComponent.uploadImage(USER_IMAGE_PATH, file, renamedFile);
    }

    public void createUserBackgroundImage(User user, MultipartFile file) {
        String renamedFile = imageComponent.createUUID();

        UserBackgroundImage backgroundImage = UserBackgroundImage.of(renamedFile, user);
        userBackgroundImageRepository.save(backgroundImage);

        imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file, renamedFile);
    }

    public void editUserImage(User user, MultipartFile file) {
        UserImage userImage = user.getUserImage();

        String renamedFile = imageComponent.createUUID();
        userImage.update(renamedFile);
        userImageRepository.save(userImage);

        imageComponent.deleteImage(USER_IMAGE_PATH, userImage.getImage());
        imageComponent.uploadImage(USER_IMAGE_PATH, file, renamedFile);
    }


    public void editUserBackgroundImage(User user, MultipartFile file) {
        UserBackgroundImage userBackgroundImage = user.getUserBackgroundImage();

        String renamedFile = imageComponent.createUUID();
        userBackgroundImage.update(renamedFile);

        userBackgroundImageRepository.save(userBackgroundImage);

        imageComponent.deleteImage(USER_BACKGROUND_IMAGE_PATH, userBackgroundImage.getImage());
        imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file, renamedFile);
    }

    public void deleteUserImage(Long userId) {
        User user = userService.getUserById(userId);
        UserImage userImage = user.getUserImage();

        user.deleteImage();
        userImageRepository.delete(userImage);

        imageComponent.deleteImage(USER_IMAGE_PATH, userImage.getImage());
    }

    public void deleteUserBackgroundImage(Long userId) {
        User user = userService.getUserById(userId);
        UserBackgroundImage userBackgroundImage = user.getUserBackgroundImage();

        user.deleteBackgroundImage();
        userBackgroundImageRepository.delete(userBackgroundImage);

        imageComponent.deleteImage(USER_BACKGROUND_IMAGE_PATH, userBackgroundImage.getImage());
    }

}
