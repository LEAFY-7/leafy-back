package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
class FeedServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedService feedService;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    @DisplayName("회원 아이디로 게시물이 몇개인지 조회한다.")
    void testGetCountByUserId(){
        //given
        Feed feed1 = createFeed("첫번째 게시물");
        Feed feed2 = createFeed("두번째 게시물");
        Feed feed3 = createFeed("세번째 게시물");
        Feed feed4 = createFeed("네번째 게시물");
        Feed feed5 = createFeed("다섯번째 게시물");
        Feed feed6 = createFeed("여섯번째 게시물");
        Feed feed7 = createFeed("일곱번째 게시물");
        feedRepository.saveAll( List.of(feed1, feed2, feed3, feed4, feed5, feed6, feed7) );

        User user = createUser("ekxk1234@naver.com", "정철희", List.of(feed1, feed2, feed3, feed4, feed5, feed6, feed7) );
        userRepository.save( user );

        //when
        Long count = feedService.countByUserId(user.getId());

        //then
        Assertions.assertThat(count).isEqualTo(7);
    }

    private User createUser(String email, String nickName, List<Feed> feeds) {
        return User.builder()
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .feeds(feeds)
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .isHide(false)
                .userRole(UserRole.MEMBER)
                .build();
    }

    private Feed createFeed(String title) {
        return Feed.builder()
                .title(title)
                .content("내용")
                .build();
    }
}