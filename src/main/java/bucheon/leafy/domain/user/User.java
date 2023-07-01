package bucheon.leafy.domain.user;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.notice.Notice;
import bucheon.leafy.domain.qna.Qna;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.util.BaseDeleteEntity;
import lombok.*;

import javax.persistence.*;
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

    private String password;

    private String email;

    private String nickName;

    private String phone;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feeds = new ArrayList<>();

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> address = new ArrayList<>();

    // 유저는 정보를 뽑아올 때 거의 대부분 이미지를 뽑아오고 추가적으로 연관관계가 설정이 되어있지 않기 때문에 FetchType.EAGER 로 수정 고려
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserImage userImage;

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
                 List<Feed> feeds, List<Address> address, UserImage userImage,
                 UserRole userRole,  List<Qna> qna, List<Notice> notices) {

        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.phone = phone;
        this.feeds = feeds;
        this.address = address;
        this.userImage = userImage;
        this.userRole = userRole;
        this.qna = qna;
        this.notices = notices;
    }

    public static User of(SignUpRequest signUpRequest) {
        Address address = Address.of(signUpRequest);

        return User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .nickName(signUpRequest.getNickName())
                .phone(signUpRequest.getPhone())
                .address(List.of(address))
                .userRole(UserRole.MEMBER)
                .build();

    }

    public void addUserImage(SignUpRequest signUpRequest){
        UserImage userImage = UserImage.of(signUpRequest, this);
        this.userImage = userImage;
    }

    public void changePassword(String encodePassword){
        this.password = encodePassword;
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }

}
