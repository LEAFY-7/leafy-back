package bucheon.leafy.domain.board;

import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private Boolean isHide;

    @OneToOne(mappedBy = "board", cascade = CascadeType.ALL)
    private BoardLike boardLike;

    @JoinColumn(name = "board_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardComment> boardComments = new ArrayList<>();

    @JoinColumn(name = "board_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardTag> boardTags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BoardType boardType;


    @Builder
    private Board(String title, String contents, Boolean isHide, BoardLike boardLike,
                  List<BoardComment> boardComments, List<BoardTag> boardTags, BoardType boardType) {
        this.title = title;
        this.contents = contents;
        this.isHide = isHide;
        this.boardLike = boardLike;
        this.boardComments = boardComments;
        this.boardTags = boardTags;
        this.boardType = boardType;
    }

}
