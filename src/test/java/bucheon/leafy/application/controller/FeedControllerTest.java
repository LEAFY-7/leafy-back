package bucheon.leafy.application.controller;

import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.application.repository.AddressRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.config.AuthUserDetailService;
import bucheon.leafy.domain.feed.FeedType;
import bucheon.leafy.domain.feed.request.FeedRequest;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected AuthUserDetailService authUserDetailService;
    @Autowired
    protected AddressRepository addressRepository;
    @Autowired
    protected UserImageRepository userImageRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected FeedService feedService;
    @Autowired
    protected FeedMapper feedMapper;

    @BeforeEach
    void setUp() {
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

        User user = User.builder()
                .address(address)
                .userImage(image)
                .email("email@email.com")
                .phone("01012341234")
                .nickName("별명")
                .password("비밀번호")
                .userRole(UserRole.MEMBER)
                .build();

        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        addressRepository.deleteAllInBatch();
        userImageRepository.deleteAllInBatch();
        feedMapper.deleteAllFeeds();
        userRepository.deleteAllInBatch();
    }

    @DisplayName("피드 등록/상세 조회 테스트")
    @Test
    void testSaveFeed() throws Exception {
        AuthUser authUser = (AuthUser) authUserDetailService.loadUserByUsername("email@email.com");
        Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        FeedRequest feedRequest = FeedRequest.builder().title("새제목").content("새내용").feedType(FeedType.PUBLIC).build();

        ResultActions result = mockMvc.perform(post("/api/v1/feeds")
                        .content(objectMapper.writeValueAsString(feedRequest))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Long feedId = Long.valueOf(result.andReturn().getResponse().getContentAsString());

        mockMvc.perform(get("/api/v1/feeds/{feedId}", feedId))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
