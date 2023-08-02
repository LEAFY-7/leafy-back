package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.FeedReportRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feedreport.FeedReport;
import bucheon.leafy.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
class FeedReportServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedReportRepository feedReportRepository;

    @Autowired
    private FeedReportService feedReportService;


    @AfterEach
    void tearDown(){
        feedReportRepository.deleteAllInBatch();
        feedRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }


    @TestFactory
    @DisplayName("내가 신고한 게시물 리스트를 가져온다.")
    Collection<DynamicTest> testGetReportedFeeds(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 20, sort);

        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        Feed feed4 = createFeed("네번째 게시물");
        Feed feed5 = createFeed("다섯번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3, feed4, feed5) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1, feed2));
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed3, feed4));
        User user3 = createUser("abcde@gmail.com", "유재석", List.of(feed5));
        userRepository.saveAll( List.of(user1, user2, user3) );

        FeedReport feedReport1 = FeedReport.of(user1, feed3);
        FeedReport feedReport2 = FeedReport.of(user1, feed4);
        feedReportRepository.saveAll( List.of(feedReport1, feedReport2) );

        return List.of(
                DynamicTest.dynamicTest("사용자의 신고 내역을 기반으로 사용자들의 정보를 조회한다.", () -> {
                    //when
                    List<FeedResponse> feedResponses = feedReportService.getReportedFeeds(user1.getId(), pageable);

                    //then
                    Assertions.assertThat(feedResponses).hasSize(2)
                            .extracting("title")
                            .containsExactlyInAnyOrder(
                                    "세번째 게시물", "네번째 게시물"
                            );
                }),

                DynamicTest.dynamicTest("신고 내역이 없는 사용자는 빈 컬렉션이 반환된다.", () -> {
                    //when
                    List<FeedResponse> feedResponses = feedReportService.getReportedFeeds(user3.getId(), pageable);

                    //then
                    Assertions.assertThat(feedResponses).hasSize(0);
                })
        );
    }

    @Test
    @DisplayName("회원을 신고하면 신고 테이블에 정보가 입력된다.")
    void testReportFeeds(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1));
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed2, feed3));
        userRepository.saveAll( List.of(user1, user2) );

        //when
        feedReportService.reportFeed(user1.getId(), feed2.getId());
        feedReportService.reportFeed(user1.getId(), feed3.getId());
        List<FeedReport> feedReports = feedReportRepository.findAll();

        //then
        Assertions.assertThat(feedReports).hasSize(2);
    }

    @Test
    @DisplayName("신고를 취소하면 신고 테이블에 정보가 삭제된다.")
    void testReportCancelFeeds(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1));
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed2, feed3));
        userRepository.saveAll( List.of(user1, user2) );

        FeedReport feedReport1 = FeedReport.of(user1, feed2);
        FeedReport feedReport2 = FeedReport.of(user1, feed3);
        feedReportRepository.saveAll( List.of(feedReport1, feedReport2) );

        //when
        feedReportService.reportCancelFeed(user1.getId(), feed2.getId());
        feedReportService.reportCancelFeed(user1.getId(), feed3.getId());
        List<FeedReport> feedReports = feedReportRepository.findAll();

        //then
        Assertions.assertThat(feedReports).hasSize(0);
    }

    private User createUser(String email, String nickName, List<Feed> feeds) {
        return User.builder()
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .feeds(feeds)
                .build();
    }

    private Feed createFeed(String title) {
        Feed feed = Feed.builder()
                .title(title)
                .content("내용")
                .build();

        return feed;
    }
}