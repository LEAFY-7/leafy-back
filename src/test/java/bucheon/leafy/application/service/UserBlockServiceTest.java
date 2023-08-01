package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.*;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.domain.userblock.UserBlock;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
class UserBlockServiceTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserBlockRepository userBlockRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    @Autowired
    private UserBlockService userBlockService;


    @AfterEach
    void tearDown() {
        userImageRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
        userBlockRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }


    @TestFactory
    @DisplayName("내가 차단한 사용자들의 리스트를 가져온다.")
    Collection<DynamicTest> testGetBlockedUsers() {
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 20, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("abcde@gmail.com", "유재석");
        User user4 = createUser("abcdf@gmail.com", "강호동");
        User user5 = createUser("abcdg@gmail.com", "테스트");
        userRepository.saveAll(List.of(user1, user2, user3, user4, user5));

        UserBlock userBlock1 = UserBlock.of(user1, user2);
        UserBlock userBlock2 = UserBlock.of(user1, user3);
        UserBlock userBlock3 = UserBlock.of(user1, user4);
        userBlockRepository.saveAll(List.of(userBlock1, userBlock2, userBlock3));

        return List.of(

                DynamicTest.dynamicTest("사용자의 차단 내역을 기반으로 사용자들의 정보를 조회한다.", () -> {
                    //when
                    List<UserResponse> reportedUsers = userBlockService.getBlockedUsers(user1.getId(), pageable);

                    //then
                    Assertions.assertThat(reportedUsers).hasSize(3)
                            .extracting("email", "nickName")
                            .containsExactlyInAnyOrder(
                                    new Tuple("abcd@gmail.com", "홍길동"),
                                    new Tuple("abcde@gmail.com", "유재석"),
                                    new Tuple("abcdf@gmail.com", "강호동")
                            );
                }),

                DynamicTest.dynamicTest("차단 내역이 없는 사용자는 빈 컬렉션이 반환된다.", () -> {
                    //when
                    List<UserResponse> reportedUsers = userBlockService.getBlockedUsers(user5.getId(), pageable);

                    //then
                    Assertions.assertThat(reportedUsers).hasSize(0);

                })

        );

    }

    @Test
    @DisplayName("회원을 차단하면 차단 테이블에 정보가 입력된다.")
    void testBlockUser() {
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll(List.of(user1, user2, user3));

        //when
        userBlockService.blockUser(user1.getId(), user2.getId());
        userBlockService.blockUser(user1.getId(), user3.getId());
        List<UserBlock> UserBlocks = userBlockRepository.findAll();

        //then
        Assertions.assertThat(UserBlocks).hasSize(2);
    }

    @Test
    @DisplayName("차단을 취소하면 차단 테이블에 정보가 삭제된다.")
    void testNoneBlockUser() {
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll(List.of(user1, user2));

        UserBlock userBlock = UserBlock.of(user1, user2);
        userBlockRepository.save(userBlock);
        //when
        userBlockService.noneBlockUser(user1.getId(), user2.getId());
        List<UserBlock> UserBlocks = userBlockRepository.findAll();

        //then
        Assertions.assertThat(UserBlocks).hasSize(0);
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