package bucheon.leafy.domain.user.request;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SignUpRequest {

        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일 형식을 맞춰주세요")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @NotBlank(message = "넥네임을 입력해주세요")
        private String nickName;

        @NotBlank(message = "전화번호를 입력해주세요")
        private String phone;

        // TODO : 주소
        private String zipcode;

        private String street;

        private String lot;

        private String detail;

        private String reference;

        // TODO : 이미지
        private String userImage;

        @Builder
        private SignUpRequest(String password, String email, String nickName,
                             String phone, String zipcode, String street, String lot,
                             String detail, String reference, String userImage) {

                this.password = password;
                this.email = email;
                this.nickName = nickName;
                this.phone = phone;
                this.zipcode = zipcode;
                this.street = street;
                this.lot = lot;
                this.detail = detail;
                this.reference = reference;
                this.userImage = userImage;

        }

}