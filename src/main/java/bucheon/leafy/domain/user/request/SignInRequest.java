package bucheon.leafy.domain.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class SignInRequest {

        @Email(message = "이메일 형식을 맞춰주세요")
        @NotBlank(message = "이메일은 필수입니다")
        @Schema(description = "이메일", example = "test@email.com")
        String email;

        @NotBlank(message = "비밀번호는 필수입니다")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
        @Schema(description = "비밀번호", example = "Abcd1234!")
        private String password;

        @Builder
        private SignInRequest(String email, String password) {
                this.email = email;
                this.password = password;
        }

        public static SignInRequest of(SignUpRequest signUpRequest) {
                return SignInRequest.builder()
                        .email(signUpRequest.getEmail())
                        .password(signUpRequest.getPassword())
                        .build();
        }

}
