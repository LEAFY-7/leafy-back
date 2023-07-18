package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserBackgroundImageRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserBackgroundImage;
import bucheon.leafy.domain.user.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImageService {

    private final UserService userService;

    private final UserImageRepository userImageRepository;

    private final UserBackgroundImageRepository userBackgroundImageRepository;

    public void createUserImage(Long userId, String file) {
        User user = userService.getUserById(userId);
        UserImage userImage = UserImage.of(file, user);
        userImageRepository.save(userImage);
    }

    public void createUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
        UserBackgroundImage backgroundImage = UserBackgroundImage.of(file.toString(), user);
        userBackgroundImageRepository.save(backgroundImage);
    }

    public void editUserImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
    }

    public void editUserBackgroundImage(Long userId, MultipartFile file) {
        User user = userService.getUserById(userId);
    }

}
