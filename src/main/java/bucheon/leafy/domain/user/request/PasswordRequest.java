package bucheon.leafy.domain.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PasswordRequest {

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
    @Schema(description = "비밀번호", example = "Abcd1234!")
    private String password;

    @NotBlank(message = "체크 비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
    @Schema(description = "비밀번호 재입력", example = "Abcd1234!")
    private String confirmPassword;

}
