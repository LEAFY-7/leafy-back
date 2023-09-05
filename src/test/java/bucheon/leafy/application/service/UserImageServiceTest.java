package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.repository.UserBackgroundImageRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Transactional
class UserImageServiceTest extends IntegrationTestSupport {

    @Autowired
    UserImageService userImageService;

    @Autowired
    UserRepository userRepository;

    @MockBean
    UserImageRepository userImageRepository;

    @MockBean
    UserBackgroundImageRepository userBackgroundImageRepository;

    @MockBean
    ImageComponent imageComponent;

    @Test
    @DisplayName("회원이 이미지를 저장하면 이미지의 이름을 unique 한 값으로 받고 저장한다.")
    void testCreateUserImage(){
        //given
        User user = createUser("ekxk1234@naver.com", "정철희");
        userRepository.save(user);

        MultipartFile file = new MockMultipartFile(
                "fileName",
                "originalName",
                "contentType",
                "fileContent".getBytes()
        );

        String uuid = UUID.randomUUID().toString();

        // when
        when(imageComponent.createUUID()).thenReturn(uuid);
        userImageService.createUserImage(user, file);

        //then
        verify(userImageRepository, times(1)).save(any(UserImage.class));
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file), anyString());;
    }

    @Test
    @DisplayName("회원이 배경 이미지를 저장하면 이미지의 이름을 unique 한 값으로 받고 저장한다.")
    void testCreateUserBackGroundImage(){
        //given
        User user = createUser("ekxk1234@naver.com", "정철희");
        userRepository.save(user);

        MultipartFile file = new MockMultipartFile(
                "fileName",
                "originalName",
                "contentType",
                "fileContent".getBytes()
        );

        String uuid = UUID.randomUUID().toString();

        // when
        when(imageComponent.createUUID()).thenReturn(uuid);
        userImageService.createUserBackgroundImage(user, file);

        //then
        verify(userBackgroundImageRepository, times(1)).save(any(UserBackgroundImage.class));
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file), anyString());;
    }

    @Test
    @DisplayName("회원이 이미지를 수정하면 S3 에서 이미지를 삭제한 뒤 변경할 이미지를 업로드하며 데이터베이스에 저장한다.")
    void testEditUserImage(){
        //given
        User user = createUserWithImage("ekxk1234@naver.com", "정철희");
        userRepository.save(user);

        MultipartFile file = new MockMultipartFile(
                "fileName",
                "originalName",
                "contentType",
                "fileContent".getBytes()
        );

        String uuid = UUID.randomUUID().toString();

        //when
        when(imageComponent.createUUID()).thenReturn(uuid);
        userImageService.editUserImage(user, file);

        //then
        verify(userImageRepository, times(1)).save(any(UserImage.class));
        verify(imageComponent, times(1)).deleteImage(anyString(), anyString());;
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file), anyString());;

    }

    @Test
    @DisplayName("회원이 배경 이미지를 수정하면 S3 에서 이미지를 삭제한 뒤 변경할 이미지를 업로드하며 데이터베이스에 저장한다.")
    void testEditUserBackGroundImage(){
        //given
        User user = createUserWithImage("ekxk1234@naver.com", "정철희");
        userRepository.save(user);

        MultipartFile file = new MockMultipartFile(
                "fileName",
                "originalName",
                "contentType",
                "fileContent".getBytes()
        );

        String uuid = UUID.randomUUID().toString();

        // when
        when(imageComponent.createUUID()).thenReturn(uuid);
        userImageService.editUserBackgroundImage(user, file);

        //then
        verify(userBackgroundImageRepository, times(1)).save(any(UserBackgroundImage.class));
        verify(imageComponent, times(1)).deleteImage(anyString(), anyString());
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file), anyString());
    }

    private User createUser(String email, String nickName) {
        return User.builder()
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .isHide(false)
                .userRole(UserRole.MEMBER)
                .build();
    }

    private User createUserWithImage(String email, String nickName) {

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        UserBackgroundImage backgroundImage = UserBackgroundImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .userImage(image)
                .userBackgroundImage(backgroundImage)
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .isHide(false)
                .build();
    }

}