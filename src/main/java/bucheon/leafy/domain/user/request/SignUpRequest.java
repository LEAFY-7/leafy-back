package bucheon.leafy.domain.user.request;

import bucheon.leafy.domain.user.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

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
        private String simpleIntroduction;

        @NotNull(message = "생일을 입력해주세요")
        @Schema(description = "생년월일", example = "1999-02-11")
        private LocalDate birthDay;

        @NotNull(message = "성별을 입력해주세요")
        @Schema(description = "성별", example = "FEMALE")
        private Gender gender;


        // TODO : 주소
        @Schema(description = "우편번호", example = "경기도")
        private String zoneCode;

        @Schema(description = "주소", example = "부천시")
        private String address;

        @Schema(description = "지번주소", example = "소향로 11")
        private String jibunAddress;

        @Schema(description = "도로명주소", example = "소향로 11")
        private String roadAddress;

        @Schema(description = "상세주소", example = "1동 1호")
        private String detailAddress;

        @Schema(description = "주소 공개 여부", example = "true")
        private Boolean addressIsHide;

        @Builder
        private SignUpRequest(String password, String confirmPassword, String email, String nickName,
                              String name, String simpleIntroduction, String phone, String zoneCode,
                              String address, String jibunAddress, String roadAddress, String detailAddress,
                              Boolean addressIsHide, LocalDate birthDay, Gender gender) {

                this.password = password;
                this.confirmPassword = confirmPassword;
                this.email = email;
                this.nickName = nickName;
                this.name = name;
                this.simpleIntroduction = simpleIntroduction;
                this.phone = phone;
                this.zoneCode = zoneCode;
                this.address = address;
                this.jibunAddress = jibunAddress;
                this.roadAddress = roadAddress;
                this.detailAddress = detailAddress;
                this.addressIsHide = addressIsHide;
                this.birthDay = birthDay;
                this.gender = gender;
        }


}