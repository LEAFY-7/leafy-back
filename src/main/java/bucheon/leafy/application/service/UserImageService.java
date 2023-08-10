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

    public void createUserImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);

        if(user.getUserImage() != null){
            throw new ExistException(USER_IMAGE);
        }

        String renamedFile = imageComponent.uploadImage(USER_IMAGE_PATH, file);

        UserImage userImage = UserImage.of(renamedFile, user);
        userImageRepository.save(userImage);
    }

    public void createUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
        String renamedFile = imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file);

        if(user.getUserBackgroundImage() != null){
            throw new ExistException(USER_BACKGROUND_IMAGE);
        }

        UserBackgroundImage backgroundImage = UserBackgroundImage.of(renamedFile, user);
        userBackgroundImageRepository.save(backgroundImage);
    }

//    public void editUserImage(Long userId, MultipartFile file) {
//        deleteUserImage(userId);
//        createUserImage(userId, file);
//    }
//
//    public void editUserBackgroundImage(Long userId, MultipartFile file) {
//        deleteUserBackgroundImage(userId);
//        createUserBackgroundImage(userId, file);
//    }


    public void editUserImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
        UserImage userImage = user.getUserImage();

        imageComponent.deleteImage(USER_IMAGE_PATH, userImage.getImage());

        String renamedFile = imageComponent.uploadImage(USER_IMAGE_PATH, file);
        userImage.update(renamedFile);

        userImageRepository.save(userImage);
    }


    public void editUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
        UserBackgroundImage userBackgroundImage = user.getUserBackgroundImage();

        imageComponent.deleteImage(USER_BACKGROUND_IMAGE_PATH, userBackgroundImage.getImage());

        String renamedFile = imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file);
        userBackgroundImage.update(renamedFile);

        userBackgroundImageRepository.save(userBackgroundImage);
    }

    public void deleteUserImage(Long userId) {
        User user = userService.getUserById(userId);
        UserImage userImage = user.getUserImage();

        imageComponent.deleteImage(USER_IMAGE_PATH, userImage.getImage());
        user.deleteImage();
        userImageRepository.delete(userImage);
    }

    public void deleteUserBackgroundImage(Long userId) {
        User user = userService.getUserById(userId);
        UserBackgroundImage userBackgroundImage = user.getUserBackgroundImage();

        imageComponent.deleteImage(USER_BACKGROUND_IMAGE_PATH, userBackgroundImage.getImage());
        user.deleteBackgroundImage();
        userBackgroundImageRepository.delete(userBackgroundImage);
    }

}
