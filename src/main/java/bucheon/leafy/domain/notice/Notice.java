package bucheon.leafy.domain.notice;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Getter
@Entity
@SQLDelete(sql = "update notice set is_delete = true where notice_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String contents;

    private Long viewCount;

    private Boolean isHide;


    @Builder
    private Notice(String title, String contents, Long viewCount, User user, Boolean isHide) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.isHide = isHide;
    }
}