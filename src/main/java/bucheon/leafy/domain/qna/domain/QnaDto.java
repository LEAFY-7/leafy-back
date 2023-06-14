package bucheon.leafy.domain.qna.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode
public class QnaDto {
    private Integer id;
    private String userId;
    private String title;
    private String contents;
    private Date modifiedAt;
    private Date createdAt;
    private Integer viewCnt;
    private Integer commentCnt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return viewCnt == qnaDto.viewCnt && commentCnt == qnaDto.commentCnt && Objects.equals(id, qnaDto.id) && Objects.equals(userId, qnaDto.userId) && Objects.equals(title, qnaDto.title) && Objects.equals(contents, qnaDto.contents) && Objects.equals(id, qnaDto.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, userId);
    }
    public QnaDto() { this("","",""); }
    public QnaDto(String title, String contents, String userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) { this.userId = userId;  }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public int getViewCnt() {
        return viewCnt;
    }
    public void setViewCnt(int viewCnt) { this.viewCnt = viewCnt; }
    public int commentCnt() { return commentCnt; }
    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }


}
