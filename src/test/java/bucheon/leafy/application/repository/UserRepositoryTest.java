package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends IntegrationTestSupport {

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
        User user = createUser("email@email.com", "별명");

        //when
        User result = userRepository.save(user);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("@EntityGraph 로 UserImage 까지 fetch join 조회")
    void testFindAllWithUserImageByIdIn(){
        //given
        User user1 = createUser("email@email.com", "별명1");
        User user2 = createUser("abcd@gmail.com", "별명2");
        User user3 = createUser("qwer@naver.com", "별명3");

        List<User> users = userRepository.saveAll(List.of(user1, user2, user3));
        List<Long> ids = users.stream()
                .map(user -> user.getId())
                .collect(Collectors.toList());

        //when
        List<User> result = userRepository.findAllWithUserImageByIdIn(ids);

        //then
        assertThat(result).isNotNull();
        assertThat(result)
                .hasSize(3)
                .extracting("email", "nickName")
                .containsExactlyInAnyOrder(
                        new Tuple("email@email.com", "별명1"),
                        new Tuple("abcd@gmail.com", "별명2"),
                        new Tuple("qwer@naver.com", "별명3")
                );

    }


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