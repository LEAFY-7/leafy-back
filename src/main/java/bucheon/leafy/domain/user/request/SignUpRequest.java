package bucheon.leafy.domain.user.request;

import bucheon.leafy.domain.user.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SignUpRequest {

        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식을 맞춰주세요")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @NotBlank(message = "이름을 입력해주세요")
        private String name;

        @NotBlank(message = "넥네임을 입력해주세요")
        private String nickName;

        @NotBlank(message = "전화번호를 입력해주세요")
        private String phone;

        @NotBlank(message = "간단 소개를 입력해주세요")
        private String simpleIntroduction;

        @NotNull(message = "생일을 입력해주세요")
        private LocalDate birthDay;

        @NotNull(message = "성별을 입력해주세요")
        private Gender gender;


        // TODO : 주소
        private String zoneCode;

        private String address;

        private String jibunAddress;

        private String roadAddress;

        private String detailAddress;

        private Boolean addressIsHide;

        @Builder
        private SignUpRequest(String password, String email, String nickName,  String name,
                              String simpleIntroduction, String phone, String zoneCode,
                              String address, String jibunAddress, String roadAddress, String detailAddress,
                              Boolean addressIsHide, LocalDate birthDay, Gender gender) {

                this.password = password;
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