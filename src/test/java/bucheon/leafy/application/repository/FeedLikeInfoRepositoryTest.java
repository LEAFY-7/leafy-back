package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.FeedLikeInfoNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Transactional
class FeedLikeInfoRepositoryTest extends IntegrationTestSupport {

    @Autowired
    FeedLikeInfoRepository feedLikeInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Test
    @DisplayName("사용자의 정보와 게시글의 정보로 회원을 찾는다.")
    void testFindFeedLikeInfoByUserAndFeed(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed();
        feedRepository.save(feed);

        FeedLikeInfo feedLikeInfo = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(feedLikeInfo);

        //when
        FeedLikeInfo findFeedLikeInfo = feedLikeInfoRepository.findByUserAndFeed(user, feed)
                .orElseThrow(FeedLikeInfoNotFoundException::new);

        //then
        Assertions.assertThat(findFeedLikeInfo).isNotNull();
        Assertions.assertThat(findFeedLikeInfo.getUser()).isEqualTo(user);
        Assertions.assertThat(findFeedLikeInfo.getFeed()).isEqualTo(feed);
    }

    private Feed createFeed() {

        FeedLikeCount likeCount = FeedLikeCount.of( new AtomicLong(0) );

        return Feed.builder()
                .title("타이틀")
                .content("내용")
                .isHide(false)
                .isDelete(false)
                .feedLikeCount(likeCount)
                .build();

    }

    private User createUser(String email, String nickName) {
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
                .address(List.of(address))
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}