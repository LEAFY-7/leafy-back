package bucheon.leafy.domain.user;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.notice.Notice;
import bucheon.leafy.domain.qna.Qna;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private String email;

    private String password;

    private String name;

    private String nickName;

    private String phone;

    private Gender gender;

    private LocalDate birthDay;

    private String simpleIntroduction;

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

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Qna> qna = new ArrayList<>();

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @Builder
    private User(String password, String email, String nickName, String phone,
                 String name, String simpleIntroduction, List<Feed> feeds,
                 Gender gender, LocalDate birthDay, Address address, UserImage userImage,
                 UserRole userRole, List<Qna> qna, List<Notice> notices) {

        this.password = password;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.simpleIntroduction = simpleIntroduction;
        this.feeds = feeds;
        this.address = address;
        this.userImage = userImage;
        this.userRole = userRole;
        this.gender = gender;
        this.birthDay = birthDay;
        this.qna = qna;
        this.notices = notices;
    }

    public static User of(SignUpRequest signUpRequest) {
        Address address = Address.of(signUpRequest);

        return User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .nickName(signUpRequest.getNickName())
                .phone(signUpRequest.getPhone())
                .simpleIntroduction(signUpRequest.getSimpleIntroduction())
                .address(address)
                .userRole(UserRole.MEMBER)
                .build();

    }

    public void addUserImage(String userBackgroundImage){
        UserImage userImage = UserImage.of(userBackgroundImage, this);
        this.userImage = userImage;
    }

    public void addUserBackgroundImage(String userBackgroundImage){
        UserBackgroundImage backgroundImage = UserBackgroundImage.of(userBackgroundImage, this);
        this.userBackgroundImage = backgroundImage;
    }

    public void changePassword(String encodePassword){
        this.password = encodePassword;
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }

}