package bucheon.leafy.domain.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequest {

        @NotBlank(message = "아이디는 필수입니다")
        String customerId;

        @NotBlank(message = "비밀번호는 필수입니다")
        String password;

}
