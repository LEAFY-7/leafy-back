package bucheon.leafy.application.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@SpringBootTest
//@AutoConfigureMockMvc
//@WithMockUser
public class FeedImageControllerTest {

//    @Autowired
//    protected MockMvc mockMvc;
//
//    @Autowired
//    FeedService feedService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    FeedMapper feedMapper;
//
//    @Autowired
//    FeedImageMapper imageMapper;
//
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private UserImageRepository userImageRepository;
//
//    @AfterEach
//    void tearDown(){
//        imageMapper.deleteAllImages();
//        feedMapper.deleteAllFeeds();
//        userImageRepository.deleteAllInBatch();
//        addressRepository.deleteAllInBatch();
//        userRepository.deleteAllInBatch();
//    }
//
//    @Test
//    void testSaveImage() throws Exception {
//        User user1 = createUser("aaa1234@naver.com", "홍길동");
//        userRepository.save(user1);
//
//        Optional<User> user2 = userRepository.findByEmail("aaa1234@naver.com");
//        Long userId = user2.get().getId();
//
//        //given
//        FeedRequest request = FeedRequest.builder()
//                .user_id(userId)
//                .title("새제목")
//                .content("새내용")
//                .is_hide(false)
//                .build();
//
//        //when
//        Long feedId = feedService.saveFeed(request);
//
//        MockMultipartFile image1 = new MockMultipartFile("imageList", "image1.jpg", "image/jpeg", "image1".getBytes());
//        MockMultipartFile image2 = new MockMultipartFile("imageList", "image2.jpg", "image/jpeg", "image2".getBytes());
//
//        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/feeds/{feedId}/images", feedId)
//                        .file(image1)
//                        .file(image2)
//                        .with(user("username").roles("MEMBER")))
//                .andExpect(status().isOk());
//    }
//
//    private User createUser(String email, String nickName) {
//
//        return User.builder()
//                .email(email)
//                .phone("01012341234")
//                .nickName(nickName)
//                .password("비밀번호")
//                .build();
//    }
}
