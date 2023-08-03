package bucheon.leafy.application.service;

<<<<<<< HEAD
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyServiceTest {

    @Test
=======
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.config.AuthUserDetailService;
import bucheon.leafy.domain.user.request.SignInRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Test
    void getCount() {
    }

    @Test
>>>>>>> ab55635b2b92322470b1d02be0adbadc5260dbe1
    void remove() {
    }

    @Test
    void write() {
    }

    @Test
<<<<<<< HEAD
    void modify() {
    }

    @Test
    void getList() {
=======
    void getList() {
    }

    @Test
    void modify() {
>>>>>>> ab55635b2b92322470b1d02be0adbadc5260dbe1
    }
}