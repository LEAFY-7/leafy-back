package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserService;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.jwt.TokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserService userService;

    @Test
    @DisplayName("사용자가 회원가입을 요청했다")
    void testSignup() throws Exception {

        //given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .password("Ss12345!@")
                .confirmPassword("Ss12345!@")
                .email("abcd1234@gmail.com")
                .name("김찬우")
                .phone("01012345678")
                .build();

        TokenResponse tokenResponse = TokenResponse.builder().build();

        SignInRequest signInRequest = SignInRequest.of(signUpRequest);

        when(userService.signIn(signInRequest)).thenReturn(tokenResponse);

        //when //then
        mockMvc.perform(post("/api/v1/users/sign-up")
                        .content(objectMapper.writeValueAsString(signUpRequest))
                        .contentType(APPLICATION_JSON)
                ).andDo(print())
                .andExpect( status().isCreated() );
    }

}