package bucheon.leafy.domain.notice;

import bucheon.leafy.domain.user.User;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private List<NoticeComment> noticeComments = new ArrayList<>();

    @Builder
    private Notice(String title, String contents, Long viewCount, User user,
                   List<NoticeComment> noticeComments, Boolean isHide) {

        this.user = user;
        this.title = title;
        this.contents = contents;
        this.viewCount = viewCount;
        this.noticeComments = noticeComments;
        this.isHide = isHide;
    }
}