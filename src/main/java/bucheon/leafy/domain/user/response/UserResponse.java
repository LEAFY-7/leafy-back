package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private String email;

    private String gender;

    private LocalDate birthDay;

    private String name;

    private String nickName;

    private String phone;

    private String simpleIntroduction;

    private String addressZipcode;

    private String addressStreet;

    private String addressLot;

    private String addressDetail;

    private String addressReference;

    @Builder
    public UserResponse(String email, Gender gender, LocalDate birthDay,
                        String name, String nickName, String phone,
                        String simpleIntroduction, String zipcode,
                        String street, String lot, String detail, String reference) {

        this.email = email;
        this.gender = gender.getValue();
        this.birthDay = birthDay;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.simpleIntroduction = simpleIntroduction;
        this.addressZipcode = zipcode;
        this.addressStreet = street;
        this.addressLot = lot;
        this.addressDetail = detail;
        this.addressReference = reference;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phone(user.getPhone())
                .birthDay(user.getBirthDay())
                .gender(user.getGender())
                .simpleIntroduction(user.getSimpleIntroduction())
                .zipcode(user.getAddress().getZipcode())
                .street(user.getAddress().getStreet())
                .lot(user.getAddress().getLot())
                .detail(user.getAddress().getDetail())
                .reference(user.getAddress().getReference())
                .build();
    }

}
