package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Transactional
class FeedRepositoryTest extends IntegrationTestSupport {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Rollback(value = false)
    @Test
    @DisplayName("회원의 1년 동안의 월별 활동 내역을 카운트하여 가져온다.")
    void testGroupByMonthlyCountByUserId(){
        //given
        Feed feed1 = createFeed("1월 게시물");
        Feed feed2 = createFeed("2월 게시물");
        Feed feed3 = createFeed("7월 게시물");
        Feed feed4 = createFeed("7월 게시물");

        List<Feed> feeds = List.of(feed1, feed2, feed3, feed4);

        feedRepository.saveAll(feeds);

        feed1.updateCreatedAt( LocalDateTime.of(2023, 1, 1, 12 , 0) );
        feed2.updateCreatedAt( LocalDateTime.of(2023, 2, 1, 12 , 0) );
        feed3.updateCreatedAt( LocalDateTime.of(2023, 7, 1, 12 , 0) );
        feed4.updateCreatedAt( LocalDateTime.of(2023, 7, 1, 12 , 0) );

        feedRepository.saveAll(feeds);

        User user = createUser("ekxk1234@naver.com", "정철희", feeds);
        User saveUser = userRepository.save(user);

        //when
        List<FeedMonthlyInformation> feedMonthlyResponse = feedRepository.groupByMonthlyCountByUserId(saveUser.getId());

        List<FeedMonthlyResponse> feedMonthlyResponses = feedMonthlyResponse.stream()
                .map(FeedMonthlyResponse::of)
                .collect(Collectors.toList());

        //then
        Assertions.assertThat(feedMonthlyResponses)
                .extracting("year", "month", "count")
                .containsExactlyInAnyOrder(
                        new Tuple(2023, 1, 1L),
                        new Tuple(2023, 2, 1L),
                        new Tuple(2023, 7, 2L)
                );

    }


    private Feed createFeed(String title) {
        FeedLikeCount likeCount = FeedLikeCount.of( new AtomicLong(0) );

        return Feed.builder()
                .title(title)
                .content("내용")
                .feedLikeCount(likeCount)
                .build();
    }

    private User createUser(String email, String nickName, List<Feed> feeds) {
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

        return User.builder()
                .address(address)
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .feeds(feeds)
                .build();
    }
}