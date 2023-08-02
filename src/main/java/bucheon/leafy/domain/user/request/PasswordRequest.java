package bucheon.leafy.domain.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PasswordRequest {

    @NotBlank(message = "비밀번호는 필수입니다")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
    String password;

    @NotBlank(message = "체크 비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
    private String confirmPassword;

}
