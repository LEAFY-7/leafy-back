package bucheon.leafy.application.mapper;

<<<<<<< HEAD
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyMapperTest {

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void selectAll() {
=======
import bucheon.leafy.application.repository.AddressRepository;
import bucheon.leafy.application.repository.UserImageRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.application.service.FeedService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.config.AuthUserDetailService;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReplyMapperTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected AuthUserDetailService authUserDetailService;
    @Autowired
    protected UserRepository userRepository;

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
    @Test
    void getCount() {
        AuthUser authUser = (AuthUser) authUserDetailService.loadUserByUsername("email@email.com");
        Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("답"+authUser.getAuthorities());
>>>>>>> ab55635b2b92322470b1d02be0adbadc5260dbe1
    }
}