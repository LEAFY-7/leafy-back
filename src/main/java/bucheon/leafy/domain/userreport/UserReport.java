package bucheon.leafy.domain.userreport;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.userblock.UserBlock;
import bucheon.leafy.util.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "report_user_id"})
        }
)
public class UserReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_report_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 신고 대상
    @ManyToOne
    @JoinColumn(name = "report_user_id")
    private User reportUser;

    @Builder
    private UserReport(User user,User reportUser) {
        this.user = user;
        this.reportUser = reportUser;
    }

    public static UserReport of(User user, User blockUser) {
        return UserReport.builder()
                .user(user)
                .reportUser(blockUser)
                .build();
    }
}
