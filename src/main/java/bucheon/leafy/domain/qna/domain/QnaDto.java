package bucheon.leafy.domain.qna.domain;

import java.util.*;

public class QnaDto {
    private Integer id;
    private String user_id;
    private String title;
    private String contents;
    private Date modified_at;
    private Date dateTime;
    private Integer bno;
    private Integer view_cnt;
    private Integer comment_cnt;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return view_cnt == qnaDto.view_cnt && comment_cnt == qnaDto.comment_cnt && Objects.equals(id, qnaDto.id) && Objects.equals(user_id, qnaDto.user_id) && Objects.equals(title, qnaDto.title) && Objects.equals(contents, qnaDto.contents) && Objects.equals(bno, qnaDto.bno);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, title, contents);
    }
    public QnaDto(int i, String noContent, String asdf) {};
    public QnaDto(Integer id, String user_id, String title, String contents, Date modified_at, Date dateTime, Integer bno, int view_cnt, int comment_cnt) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.contents = contents;
        this.modified_at = modified_at;
        this.dateTime = dateTime;
        this.bno = bno;
        this.view_cnt = view_cnt;
        this.comment_cnt = comment_cnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    @Override
    public String toString() {
        return "QnaDto{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", modified_at=" + modified_at +
                ", dateTime=" + dateTime +
                ", bno=" + bno +
                ", view_cnt=" + view_cnt +
                ", comment_cnt=" + comment_cnt +
                '}';
    }
}
