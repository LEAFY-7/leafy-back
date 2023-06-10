package bucheon.leafy.domain.user.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignInRequest {

        @Email(message = "이메일 형식을 맞춰주세요")
        @NotBlank(message = "이메일은 필수입니다")
        String email;

        @NotBlank(message = "비밀번호는 필수입니다")
        String password;

}
