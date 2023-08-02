package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.user.User;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
class FeedRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    @DisplayName("피드 배열로 회원 데이터를 포함하여 FeedResponse 형태로 반환한다.")
    void testFindAllFeedWithUserIdByFeedIn(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        Feed feed4 = createFeed("네번째 게시물");
        Feed feed5 = createFeed("다섯번째 게시물");
        Feed feed6 = createFeed("여섯번째 게시물");
        Feed feed7 = createFeed("일곱번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3, feed4, feed5, feed6, feed7) );

        User user1 = createUser("ekxk1234@naver.com", "정철희", List.of(feed1, feed2) );
        User user2 = createUser("abcd@gmail.com", "홍길동", List.of(feed3, feed4) );
        User user3 = createUser("abcde@gmail.com", "유재석", List.of(feed5, feed6, feed7) );
        userRepository.saveAll( List.of(user1, user2, user3) );

        //when
        List<FeedResponse> feedResponses = feedRepository.findAllFeedWithUserIdByFeedIn( List.of(feed1, feed3, feed4) );

        //then
        Assertions.assertThat(feedResponses)
                .hasSize(3)
                .extracting("userName", "title")
                .containsExactlyInAnyOrder(
                        new Tuple(user1.getName(), feed1.getTitle()),
                        new Tuple(user2.getName(), feed3.getTitle()),
                        new Tuple(user2.getName(), feed4.getTitle())
                );
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
        return Feed.builder()
                .title(title)
                .content("내용")
                .build();
    }

}