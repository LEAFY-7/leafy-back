package bucheon.leafy.domain.user;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.request.UserRequest;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate birthDay;

    private String introduction;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    // 유저는 정보를 뽑아올 때 거의 대부분 이미지를 뽑아오고 추가적으로 연관관계가 설정이 되어있지 않기 때문에 FetchType.EAGER 로 수정 고려
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserImage userImage;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserBackgroundImage userBackgroundImage;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @Builder
    private User(String password, String email, String nickName, String phone,
                 String name, String introduction, List<Feed> feeds,
                 Gender gender, LocalDate birthDay, Address address, UserImage userImage,
                 UserBackgroundImage userBackgroundImage, UserRole userRole) {

        this.password = password;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.introduction = introduction;
        this.feeds = feeds;
        this.address = address;
        this.userImage = userImage;
        this.userBackgroundImage = userBackgroundImage;
        this.userRole = userRole;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public static User of(SignUpRequest signUpRequest) {
        Address address = Address.of(signUpRequest);

        User user = User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .nickName(signUpRequest.getNickName())
                .phone(signUpRequest.getPhone())
                .birthDay(signUpRequest.getBirthDay())
                .address(address)
                .userRole(UserRole.MEMBER)
                .feeds(new ArrayList<>())
                .build();

        address.addUser(user);
        return user;
    }

    public void update(UserRequest userRequest) {
        this.address.update(userRequest);

        this.name = userRequest.getName();
        this.nickName = userRequest.getNickName();
        this.phone = userRequest.getPhone();
        this.introduction = userRequest.getIntroduction();
        this.gender = userRequest.getGender();
        this.birthDay = userRequest.getBirthDay();
    }

    public void changePassword(String encodePassword){
        this.password = encodePassword;
    }

    public void deleteImage(){
        this.userImage = null;
    }

    public void deleteBackgroundImage(){
        this.userBackgroundImage = null;
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }

}
