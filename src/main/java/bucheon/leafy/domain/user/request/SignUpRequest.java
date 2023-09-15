package bucheon.leafy.domain.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;
import java.util.regex.Matcher;

@Data
@NoArgsConstructor
public class SignUpRequest {

        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식을 맞춰주세요")
        @Schema(description = "이메일", example = "test@email.com")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
        @Schema(description = "비밀번호", example = "Abcd1234!")
        private String password;

        @NotBlank(message = "체크 비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{6,16}$")
        @Schema(description = "비밀번호 재입력", example = "Abcd1234!")
        private String confirmPassword;

        @NotBlank(message = "이름을 입력해주세요")
        @Schema(description = "이름", example = "김리피")
        private String name;


        @NotBlank(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "([0|1|6|7|8|9]{3})([0-9]{3,4})?([0-9]{4})")
        @Schema(description = "전화번호", example = "01012345678")
        private String phone;

        @JsonIgnore
        private String nickName = generateRandomNickname();

        @Builder
        private SignUpRequest(String password, String confirmPassword,
                              String email, String name, String phone) {

                this.password = password;
                this.confirmPassword = confirmPassword;
                this.email = email;
                this.name = name;
                this.phone = phone;
        }

        public static String generateRandomNickname() {
                String randomNickname;
                do {
                        randomNickname = UUID.randomUUID().toString()
                                .replace("-", "").substring(0, 12);
                } while (!isValidNickname(randomNickname));
                return randomNickname;
        }

        private static final java.util.regex.Pattern NICKNAME_PATTERN
                = java.util.regex.Pattern.compile("^(?!admin|leafy)(?!.*\\s{2,})(?!.*\\s$)(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9_가-힣\\s]{3,12}$");

        private static boolean isValidNickname(String nickname) {
                Matcher matcher = NICKNAME_PATTERN.matcher(nickname);
                return matcher.matches();
        }

}