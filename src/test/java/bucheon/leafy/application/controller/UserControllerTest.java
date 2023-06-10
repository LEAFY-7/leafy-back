package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.AuthoritiesUserService;
import bucheon.leafy.domain.user.dto.request.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected AuthoritiesUserService authoritiesUserService;

    @Test
    @DisplayName("사용자가 회원가입을 요청했다")
    void testSignup() throws Exception {

        //given
        SignUpRequest request = SignUpRequest.builder()
                .password("1234")
                .email("abcd1234@gmail.com")
                .nickName("김찬우")
                .phone("01012345678")
                .zipcode("12578")
                .street("부천")
                .lot("상동")
                .detail("호수공원")
                .reference("1동 1호")
                .userImage("a.png")
                .build();

        //when //then
        mockMvc.perform(post("/user/sign-up")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }

}