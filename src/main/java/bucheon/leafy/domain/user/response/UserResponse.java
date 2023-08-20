package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.Gender;
import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static bucheon.leafy.domain.user.UserRole.ADMIN;
import static bucheon.leafy.path.S3Path.*;

@Data
public class UserResponse {

    private String email;

    private Gender gender;

    private LocalDate birthDay;

    private String name;

    private String nickName;

    private String phone;

    private String profileImage;

    private String backgroundImage;

    private String introduction;

    private String zoneCode;

    private String address;

    private String jibunAddress;

    private String roadAddress;

    private String detailAddress;
    private Boolean isAdmin;


    @Builder
    public UserResponse(String email, Gender gender, LocalDate birthDay,
                        String name, String nickName, String phone,
                        String profileImage, String backgroundImage,
                        String introduction, String zoneCode,
                        String address, String jibunAddress, String roadAddress,
                        String detailAddress, Boolean isAdmin) {

        this.email = email;
        this.gender = gender;
        this.birthDay = birthDay;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.introduction = introduction;
        this.zoneCode = zoneCode;
        this.address = address;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.isAdmin = isAdmin;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phone(user.getPhone())
                .birthDay(user.getBirthDay())
                .gender(user.getGender())
                .profileImage(
                        user.getUserImage() != null ? ABSOLUTE_PATH + USER_IMAGE_PATH + user.getUserImage().getImage() : ""
                )
                .backgroundImage(
                        user.getUserBackgroundImage() != null ? ABSOLUTE_PATH + USER_BACKGROUND_IMAGE_PATH + user.getUserBackgroundImage().getImage() : ""
                )
                .introduction(user.getIntroduction())
                .zoneCode(user.getAddress().getDetailAddress())
                .address(user.getAddress().getAddress())
                .jibunAddress(user.getAddress().getJibunAddress())
                .roadAddress(user.getAddress().getRoadAddress())
                .detailAddress(user.getAddress().getDetailAddress())
                .isAdmin(
                        user.getUserRole().equals(ADMIN) ? true : false
                )
                .build();
    }

}
