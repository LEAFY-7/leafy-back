package bucheon.leafy.domain.user;

import bucheon.leafy.util.BaseDeleteEntity;
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
    private Long id;

    private String customerId;

    private String password;

    private String email;

    private String nickName;

    private String phone;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> address = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImage userImage;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    private User(String customerId, String password, String email, String nickName,
                 String phone, List<Address> address, UserImage userImage) {
        this.customerId = customerId;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.userImage = userImage;
        this.userRole = UserRole.NORMAL;
    }

    public void giveRole(){
        this.userRole = UserRole.ADMIN;
    }

}
