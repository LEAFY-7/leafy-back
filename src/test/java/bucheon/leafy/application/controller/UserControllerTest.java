package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.UserService;
import bucheon.leafy.domain.user.Gender;
import bucheon.leafy.domain.user.request.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
    protected UserService authoritiesUserService;

    @Test
    @DisplayName("사용자가 회원가입을 요청했다")
    void testSignup() throws Exception {

        //given
        SignUpRequest request = SignUpRequest.builder()
                .password("1234")
                .email("abcd1234@gmail.com")
                .name("김찬우")
                .nickName("chanU kim")
                .simpleIntroduction("안녕!~!")
                .phone("01012345678")
                .birthDay(LocalDate.now())
                .gender(Gender.MALE)
                .zoneCode("12578")
                .address("부천")
                .jibunAddress("상동")
                .roadAddress("호수공원")
                .detailAddress("1동 1호")
                .build();

        //when //then
        mockMvc.perform(post("/api/v1/users/sign-up")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

}