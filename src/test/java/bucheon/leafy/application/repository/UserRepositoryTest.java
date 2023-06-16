package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserImageRepository userImageRepository;

    @AfterEach
    void tearDown(){
        addressRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        userImageRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("유저 정보 저장하기")
    void testSaveUser(){
        //given
        Address address = Address.builder()
                .zipcode("01011")
                .street("bucheon")
                .lot("100")
                .reference("ref")
                .detail("hello world")
                .build();

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        User user = User.builder()
                .address(List.of(address))
                .userImage(image)
                .email("email@email.com")
                .phone("01012341234")
                .nickName("별명")
                .password("비밀번호")
                .build();

        //when
        User result = userRepository.save(user);

        //then
        assertThat(result).isNotNull();
    }

}