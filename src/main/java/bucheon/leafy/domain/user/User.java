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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

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

    private String introduction;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    // 유저는 정보를 뽑아올 때 거의 대부분 이미지를 뽑아오고 추가적으로 연관관계가 설정이 되어있지 않기 때문에 FetchType.EAGER 로 수정 고려
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserImage userImage;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserBackgroundImage userBackgroundImage;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @Builder
    private User(String password, String email, String nickName, String phone,
                 String name, String introduction, List<Feed> feeds, UserImage userImage,
                 UserBackgroundImage userBackgroundImage, UserRole userRole) {

        this.password = password;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.introduction = introduction;
        this.feeds = feeds;
        this.userImage = userImage;
        this.userBackgroundImage = userBackgroundImage;
        this.userRole = userRole;
    }

    public static User of(SignUpRequest signUpRequest) {
        return User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .nickName(
                        generateRandomNickname()
                )
                .phone(signUpRequest.getPhone())
                .userRole(UserRole.MEMBER)
                .feeds(new ArrayList<>())
                .build();
    }

    public void update(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.nickName = userRequest.getNickName();
        this.phone = userRequest.getPhone();
        this.introduction = userRequest.getIntroduction();
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

    private static String generateRandomNickname() {
        String randomNickname;
        do {
            randomNickname = UUID.randomUUID().toString()
                    .replace("-", "").substring(0, 12);
        } while (!isValidNickname(randomNickname));
        return randomNickname;
    }

    private static final java.util.regex.Pattern NICKNAME_PATTERN
            = java.util.regex.Pattern.compile("^(?!admin|leafy)(?!.*\\s{2,})(?!.*\\s$)(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9_가-힣\\s]{3,12}$");

    private static boolean isValidNickname(String nickname) {
        Matcher matcher = NICKNAME_PATTERN.matcher(nickname);
        return matcher.matches();
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }
}
