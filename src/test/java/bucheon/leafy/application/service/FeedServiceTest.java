package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.repository.AddressRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FeedServiceTest {

    @Autowired
    FeedService feedService;

    @Autowired
    FeedMapper feedMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserImageRepository userImageRepository;

    @BeforeEach
    void setUp() {
        User user = createUser("aaa1234@naver.com", "홍길동");
        userRepository.save(user);
    }

    private User createUser(String email, String nickName) {
        return User.builder()
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }
//
//    @AfterEach
//    void tearDown(){
//        feedMapper.deleteAllFeeds();
//        addressRepository.deleteAllInBatch();
//        userRepository.deleteAllInBatch();
//        userImageRepository.deleteAllInBatch();
//    }

    @Test
    void testSaveFeed() {
        Optional<User> user = userRepository.findByEmail("aaa1234@naver.com");
        Long userId = user.get().getId();

        //given
        FeedRequest request = FeedRequest.builder()
                .user_id(userId)
                .title("새제목")
                .content("새내용")
                .is_hide(false)
                .build();

        //when
        Long feedId = feedService.saveFeed(request);

        //then
        assertThat(feedId).isNotNull();
        assertThat(feedId).isEqualTo(1L);
    }

//    @Test
//    void testUpdateFeed() {
//        //given
//        Optional<User> user = userRepository.findByEmail("aaa1234@naver.com");
//        Long userId = user.get().getId();
//
//        FeedRequest request1 = FeedRequest.builder()
//                .user_id(userId)
//                .title("새제목")
//                .content("새내용")
//                .is_hide(false)
//                .build();
//        feedService.saveFeed(request1);
//
//        //when
//        FeedRequest request2 = FeedRequest.builder()
//                .feed_id(1L)
//                .title("수정제목")
//                .build();
//
//        feedService.updateFeed(1L, request2);
//        FeedResponse response = feedService.getFeedById(1L);
//
//        //then
//        assertThat(response.getTitle()).isEqualTo("수정제목");
//    }
}
