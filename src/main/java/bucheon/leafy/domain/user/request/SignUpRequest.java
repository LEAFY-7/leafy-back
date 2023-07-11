package bucheon.leafy.domain.user.request;


import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

        @NotBlank(message = "생일을 입력해주세요")
        private LocalDate birthDay;

        @NotBlank(message = "성별을 입력해주세요")
        private Gender gender;


        // TODO : 주소
        private String zipcode;

        private String street;

        private String lot;

        private String detail;

        private String reference;

        private Boolean addressIsHide;

        @Builder
        private SignUpRequest(String password, String email, String nickName,  String name,
                              String simpleIntroduction, String phone, String zipcode,
                              String street, String lot, String detail, String reference,
                              Boolean addressIsHide, LocalDate birthDay, Gender gender) {

                this.password = password;
                this.email = email;
                this.nickName = nickName;
                this.name = name;
                this.simpleIntroduction = simpleIntroduction;
                this.phone = phone;
                this.zipcode = zipcode;
                this.street = street;
                this.lot = lot;
                this.detail = detail;
                this.reference = reference;
                this.addressIsHide = addressIsHide;
                this.birthDay = birthDay;
                this.gender = gender;
        }

}
