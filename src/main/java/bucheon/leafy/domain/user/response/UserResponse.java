package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.Gender;
import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private String email;

    private Gender gender;

    private LocalDate birthDay;

    private String name;

    private String nickName;

    private String phone;

    private String simpleIntroduction;

    private String zoneCode;

    private String address;

    private String jibunAddress;

    private String roadAddress;

    private String detailAddress;


    @Builder
    public UserResponse(String email, Gender gender, LocalDate birthDay,
                        String name, String nickName, String phone,
                        String simpleIntroduction, String zoneCode,
                        String address, String jibunAddress,
                        String roadAddress, String detailAddress) {

        this.email = email;
        this.gender = gender;
        this.birthDay = birthDay;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.simpleIntroduction = simpleIntroduction;
        this.zoneCode = zoneCode;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
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
                .zoneCode(user.getAddress().getDetailAddress())
                .address(user.getAddress().getAddress())
                .jibunAddress(user.getAddress().getJibunAddress())
                .roadAddress(user.getAddress().getRoadAddress())
                .detailAddress(user.getAddress().getDetailAddress())
                .build();
    }

}
