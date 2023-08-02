package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.FeedBlockRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feedblock.FeedBlock;
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
class FeedBlockServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedBlockRepository feedBlockRepository;

    @Autowired
    private FeedBlockService feedBlockService;


    @AfterEach
    void tearDown(){
        feedBlockRepository.deleteAllInBatch();
        feedRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }


    @TestFactory
    @DisplayName("내가 차단한 게시물 리스트를 가져온다.")
    Collection<DynamicTest> testGetBlockedFeeds(){
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

        FeedBlock feedBlock1 = FeedBlock.of(user1, feed3);
        FeedBlock feedBlock2 = FeedBlock.of(user1, feed4);
        feedBlockRepository.saveAll( List.of(feedBlock1, feedBlock2) );

        return List.of(
                DynamicTest.dynamicTest("사용자의 차단 내역을 기반으로 사용자들의 정보를 조회한다.", () -> {
                    //when
                    List<FeedResponse> feedResponses = feedBlockService.getBlockedFeeds(user1.getId(), pageable);

                    //then
                    Assertions.assertThat(feedResponses).hasSize(2)
                            .extracting("title")
                            .containsExactlyInAnyOrder(
                                    "세번째 게시물", "네번째 게시물"
                            );
                }),

                DynamicTest.dynamicTest("차단 내역이 없는 사용자는 빈 컬렉션이 반환된다.", () -> {
                    //when
                    List<FeedResponse> feedResponses = feedBlockService.getBlockedFeeds(user3.getId(), pageable);

                    //then
                    Assertions.assertThat(feedResponses).hasSize(0);
                })
        );

    }

    @Test
    @DisplayName("피드를 차단하면 차단 테이블에 정보가 입력된다.")
    void testBlockFeed(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1));
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed2, feed3));
        userRepository.saveAll( List.of(user1, user2) );

        //when
        feedBlockService.blockFeed(user1.getId(), feed2.getId());
        feedBlockService.blockFeed(user1.getId(), feed3.getId());
        List<FeedBlock> feedBlocks = feedBlockRepository.findAll();

        //then
        Assertions.assertThat(feedBlocks).hasSize(2);
    }

    @Test
    @DisplayName("차단을 취소하면 차단 테이블에 정보가 삭제된다.")
    void testNoneBlockFeed(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1));
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed2, feed3));
        userRepository.saveAll( List.of(user1, user2) );

        FeedBlock feedBlock1 = FeedBlock.of(user1, feed2);
        FeedBlock feedBlock2 = FeedBlock.of(user1, feed3);
        feedBlockRepository.saveAll( List.of(feedBlock1, feedBlock2) );

        //when
        feedBlockService.noneBlockFeed(user1.getId(), feed2.getId());
        feedBlockService.noneBlockFeed(user1.getId(), feed3.getId());
        List<FeedBlock> feedBlocks = feedBlockRepository.findAll();

        //then
        Assertions.assertThat(feedBlocks).hasSize(0);
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