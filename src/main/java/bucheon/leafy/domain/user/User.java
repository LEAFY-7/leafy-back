package bucheon.leafy.domain.user;

import bucheon.leafy.domain.feed.Feed;
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImage userImage;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    private User(String password, String email, String nickName, String phone, List<Feed> feeds,
                 List<Address> address, UserImage userImage, UserRole userRole) {

        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.phone = phone;
        this.feeds = feeds;
        this.address = address;
        this.userImage = userImage;
        this.userRole = userRole;
    }

    public static User of(SignUpRequest signUpRequest) {
        Address address = Address.of(signUpRequest);
        UserImage userImage = UserImage.of(signUpRequest);

        return User.builder()
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .nickName(signUpRequest.getNickName())
                .phone(signUpRequest.getPhone())
                .address(List.of(address))
                .userImage(userImage)
                .userRole(UserRole.MEMBER)
                .build();

    }

    public void changePassword(String encodePassword){
        this.password = encodePassword;
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }

}
