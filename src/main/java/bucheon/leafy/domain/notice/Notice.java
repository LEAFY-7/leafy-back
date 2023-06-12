package bucheon.leafy.domain.notice;

import bucheon.leafy.domain.user.User;
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
public class Notice extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @Enumerated(EnumType.STRING)
    private NoticeStatus noticeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private List<NoticeComment> noticeComments = new ArrayList<>();

    @Builder
    private Notice(String title, String contents, NoticeStatus noticeStatus,
                   User user, List<NoticeComment> noticeComments) {

        this.title = title;
        this.contents = contents;
        this.noticeStatus = noticeStatus;
        this.user = user;
        this.noticeComments = noticeComments;
    }
}