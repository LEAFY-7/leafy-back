package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.repository.UserBackgroundImageRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserBackgroundImage;
import bucheon.leafy.domain.user.UserImage;
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

        String renamedFile = UUID.randomUUID().toString();
        when(imageComponent.uploadImage(anyString(), eq(file))).thenReturn(renamedFile);

        userImageService.createUserImage(user.getId(), file);

        //then
        verify(userImageRepository, times(1)).save(any(UserImage.class));
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file));;
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

        String renamedFile = UUID.randomUUID().toString();
        when(imageComponent.uploadImage(anyString(), eq(file))).thenReturn(renamedFile);

        userImageService.createUserBackgroundImage(user.getId(), file);

        //then
        verify(userBackgroundImageRepository, times(1)).save(any(UserBackgroundImage.class));
        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file));;
    }

//    @Test
//    @DisplayName("회원이 이미지를 수정하면 S3 에서 이미지를 삭제한 뒤 변경할 이미지를 업로드하며 데이터베이스에 저장한다.")
//    void testEditUserImage(){
//        //given
//        User user = createUser("ekxk1234@naver.com", "정철희");
//        userRepository.save(user);
//
//        String oldFileName = UUID.randomUUID().toString();
//
//        MultipartFile file = new MockMultipartFile(
//                "fileName",
//                "originalName",
//                "contentType",
//                "fileContent".getBytes()
//        );
//
//        String renamedFile = UUID.randomUUID().toString();
//        when(imageComponent.deleteImage(anyString(), oldFileName));
//        when(imageComponent.uploadImage(anyString(), eq(file))).thenReturn(renamedFile);
//
//        userImageService.createUserImage(user.getId(), file);
//
//        //then
//        verify(userImageRepository, times(1)).save(any(UserImage.class));
//        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file));;
//    }
//
//    @Test
//    @DisplayName("회원이 배경 이미지를 수정하면 S3 에서 이미지를 삭제한 뒤 변경할 이미지를 업로드하며 데이터베이스에 저장한다.")
//    void testEditUserBackGroundImage(){
//        //given
//        User user = createUser("ekxk1234@naver.com", "정철희");
//        userRepository.save(user);
//
//        String oldFileName = UUID.randomUUID().toString();
//
//        MultipartFile file = new MockMultipartFile(
//                "fileName",
//                "originalName",
//                "contentType",
//                "fileContent".getBytes()
//        );
//
//        String renamedFile = UUID.randomUUID().toString();
//        when(imageComponent.deleteImage(anyString(), oldFileName));
//        when(imageComponent.uploadImage(anyString(), eq(file))).thenReturn(renamedFile);
//
//        userImageService.createUserBackgroundImage(user.getId(), file);
//
//        //then
//        verify(userBackgroundImageRepository, times(1)).save(any(UserBackgroundImage.class));
//        verify(imageComponent, times(1)).uploadImage(anyString(), eq(file));;
//    }

    private User createUser(String email, String nickName) {
        Address address = Address.builder()
                .zoneCode("01011")
                .address("bucheon")
                .jibunAddress("100")
                .roadAddress("ref")
                .detailAddress("hello world")
                .build();

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .address(address)
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }
}