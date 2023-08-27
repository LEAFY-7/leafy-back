package bucheon.leafy.domain.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UserRequest {

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

        @NotBlank(message = "넥네임을 입력해주세요")
        @Pattern(regexp = "^(?!admin|leafy)(?!.*\\\\s{2,})(?!.*\\\\s$)(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9_가-힣\\\\s]{3,12}$")
        @Schema(description = "별칭", example = "리피김")
        private String nickName;

        @NotBlank(message = "전화번호를 입력해주세요")
        @Pattern(regexp = "([0|1|6|7|8|9]{3})([0-9]{3,4})?([0-9]{4})")
        @Schema(description = "전화번호", example = "01012345678")
        private String phone;

        @NotBlank(message = "간단 소개를 입력해주세요")
        @Schema(description = "간단 소개", example = "리피입니다.")
        private String introduction;



        @Builder
        private UserRequest(String password, String confirmPassword, String nickName,
                            String name, String introduction, String phone) {

                this.password = password;
                this.confirmPassword = confirmPassword;
                this.nickName = nickName;
                this.name = name;
                this.introduction = introduction;
                this.phone = phone;
        }


}