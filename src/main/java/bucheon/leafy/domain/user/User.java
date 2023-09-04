package bucheon.leafy.domain.user;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.request.UserRequest;
import bucheon.leafy.oauth.OauthRequest;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String introduction;

    private String providerId;

    @Column(name = "is_hide", nullable = false)
    private Boolean isHide;

    @Column(name = "is_all_notifications", nullable = false)
    private Boolean isAllNotifications;

    @Column(name = "is_comment_notifications", nullable = false)
    private Boolean isCommentNotifications;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    // 유저는 정보를 뽑아올 때 거의 대부분 이미지를 뽑아오고 추가적으로 연관관계가 설정이 되어있지 않기 때문에 FetchType.EAGER 로 수정 고려
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserImage userImage;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserBackgroundImage userBackgroundImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Builder
    private User(String password, String email, String nickName, String phone, Boolean isHide,
                 String name, String introduction, List<Feed> feeds, UserImage userImage,
                 UserBackgroundImage userBackgroundImage, UserRole userRole, LoginType loginType,
                 Boolean isAllNotifications, Boolean isCommentNotifications, String providerId) {

        this.password = password;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.introduction = introduction;
        this.providerId = providerId;
        this.feeds = feeds;
        this.isHide = isHide;
        this.userImage = userImage;
        this.userBackgroundImage = userBackgroundImage;
        this.userRole = userRole;
        this.loginType = loginType;
        this.isAllNotifications = isAllNotifications;
        this.isCommentNotifications = isCommentNotifications;
    }

    public static User of(SignUpRequest signUpRequest) {
        return User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .nickName(signUpRequest.getNickName())
                .phone(signUpRequest.getPhone())
                .userRole(UserRole.MEMBER)
                .isHide(false)
                .loginType(LoginType.NORMAL)
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .feeds(new ArrayList<>())
                .build();
    }

    public static User of(OauthRequest oauthRequest) {
        User user = User.builder()
                .name(oauthRequest.getName())
                .nickName(oauthRequest.getNickName())
                .password(oauthRequest.getEncodedPassword())
                .providerId(oauthRequest.getProviderId())
                .userRole(UserRole.MEMBER)
                .isHide(false)
                .loginType(oauthRequest.getLoginType())
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .feeds(new ArrayList<>())
                .build();

        UserImage userImage = UserImage.of(oauthRequest.getImage(), user);
        user.addImage(userImage);
        return user;
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

    public void addImage(UserImage userImage){
        this.userImage = userImage;
    }

    public void deleteImage(){
        this.userImage = null;
    }

    public void deleteBackgroundImage(){
        this.userBackgroundImage = null;
    }


    public void updateIsHide(){
        this.isHide = !this.isHide;
    }

    public void giveRole(UserRole userRole){
        this.userRole = userRole;
    }

    public void updateIsAllNotifications(){
        this.isAllNotifications = !this.isAllNotifications;
    }

    public void updateIsCommentNotifications(){
        this.isCommentNotifications = !this.isCommentNotifications;
    }

}
