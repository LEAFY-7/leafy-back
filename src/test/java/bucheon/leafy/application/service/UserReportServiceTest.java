package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.AddressRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserReportRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.domain.userreport.UserReport;
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
class UserReportServiceTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserReportRepository userReportRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    @Autowired
    private UserReportService userReportService;


    @AfterEach
    void tearDown(){
        userImageRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
        userReportRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }


    @TestFactory
    @DisplayName("내가 신고한 사용자들의 리스트를 가져온다.")
    Collection<DynamicTest> testGetReportedUsers(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 20, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("abcde@gmail.com", "유재석");
        User user4 = createUser("abcdf@gmail.com", "강호동");
        User user5 = createUser("abcdg@gmail.com", "테스트");
        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        UserReport userReport1 = UserReport.of(user1, user2);
        UserReport userReport2 = UserReport.of(user1, user3);
        UserReport userReport3 = UserReport.of(user1, user4);
        userReportRepository.saveAll(List.of(userReport1, userReport2, userReport3));

        return List.of(

                DynamicTest.dynamicTest("사용자의 신고 내역을 기반으로 사용자들의 정보를 조회한다.", () -> {
                    //when
                    List<UserResponse> reportedUsers = userReportService.getReportedUsers(user1.getId(), pageable);

                    //then
                    Assertions.assertThat(reportedUsers).hasSize(3)
                            .extracting("email", "nickName" )
                            .containsExactlyInAnyOrder(
                                    new Tuple("abcd@gmail.com", "홍길동"),
                                    new Tuple("abcde@gmail.com", "유재석"),
                                    new Tuple("abcdf@gmail.com", "강호동")
                            );
                }),

                DynamicTest.dynamicTest("신고 내역이 없는 사용자는 빈 컬렉션이 반환된다.", () -> {
                    //when
                    List<UserResponse> reportedUsers = userReportService.getReportedUsers(user5.getId(), pageable);

                    //then
                    Assertions.assertThat(reportedUsers).hasSize(0);

                })

        );

    }

    @Test
    @DisplayName("회원을 신고하면 신고 테이블에 정보가 입력된다.")
    void testReportUser(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "박길동");
        userRepository.saveAll( List.of(user1, user2, user3) );

        //when
        userReportService.reportUser(user1.getId(), user2.getId());
        userReportService.reportUser(user1.getId(), user3.getId());
        List<UserReport> userReports = userReportRepository.findAll();

        //then
        Assertions.assertThat(userReports).hasSize(2);
    }

    @Test
    @DisplayName("신고를 취소하면 신고 테이블에 정보가 삭제된다.")
    void testReportCancelUser(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll( List.of(user1, user2) );

        UserReport userReport = UserReport.of(user1, user2);
        userReportRepository.save(userReport);
        //when
        userReportService.reportCancelUser(user1.getId(), user2.getId());
        List<UserReport> userReports = userReportRepository.findAll();

        //then
        Assertions.assertThat(userReports).hasSize(0);
    }

    private User createUser(String email, String nickName) {

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .userImage(image)
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

}