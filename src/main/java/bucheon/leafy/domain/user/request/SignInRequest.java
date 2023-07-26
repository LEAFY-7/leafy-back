package bucheon.leafy.domain.user.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SignInRequest {

        @Email(message = "이메일 형식을 맞춰주세요")
        @NotBlank(message = "이메일은 필수입니다")
        String email;

        @NotBlank(message = "비밀번호는 필수입니다")
        String password;

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
