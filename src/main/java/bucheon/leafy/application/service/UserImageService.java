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
import static bucheon.leafy.path.S3Path.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImageService {

    private final UserService userService;

    private final UserImageRepository userImageRepository;

    private final UserBackgroundImageRepository userBackgroundImageRepository;

    private final ImageComponent imageComponent;

    public String createOrUpdateUserImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);

        String image;

        if(user.getUserImage() == null) {
            image = createUserImage(user, file);
        } else {
            image = editUserImage(user, file);
        }

        return ABSOLUTE_PATH + USER_IMAGE_PATH + image;
    }

    public void createOrUpdateUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);

        if(user.getUserBackgroundImage() == null){
            createUserBackgroundImage(user, file);
        } else {
            editUserBackgroundImage(user, file);
        }
    }

    public String createUserImage(User user, MultipartFile file) {
        String renamedFile = imageComponent.createUUID();

        UserImage userImage = UserImage.of(renamedFile, user);
        userImageRepository.save(userImage);

        imageComponent.uploadImage(USER_IMAGE_PATH, file, renamedFile);
        return renamedFile;
    }

    public void createUserBackgroundImage(User user, MultipartFile file) {
        String renamedFile = imageComponent.createUUID();

        UserBackgroundImage backgroundImage = UserBackgroundImage.of(renamedFile, user);
        userBackgroundImageRepository.save(backgroundImage);

        imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file, renamedFile);
    }

    public String editUserImage(User user, MultipartFile file) {
        UserImage userImage = user.getUserImage();
        String deleteImage = userImage.getImage();

        String renamedFile = imageComponent.createUUID();
        userImage.update(renamedFile);
        userImageRepository.save(userImage);

        imageComponent.uploadImage(USER_IMAGE_PATH, file, renamedFile);
        imageComponent.deleteImage(USER_IMAGE_PATH, deleteImage);
        return renamedFile;
    }


    public void editUserBackgroundImage(User user, MultipartFile file) {
        UserBackgroundImage userBackgroundImage = user.getUserBackgroundImage();
        String deleteImage = userBackgroundImage.getImage();

        String renamedFile = imageComponent.createUUID();
        userBackgroundImage.update(renamedFile);

        userBackgroundImageRepository.save(userBackgroundImage);

        imageComponent.uploadImage(USER_BACKGROUND_IMAGE_PATH, file, renamedFile);
        imageComponent.deleteImage(USER_BACKGROUND_IMAGE_PATH, deleteImage);
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
