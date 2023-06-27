package bucheon.leafy.domain.qna.domain;

import lombok.*;

import java.time.LocalTime;
import java.util.*;

@Data
public class QnaDto {
    private Integer id;
    private Date createdAt;
    private Date modifiedAt;
    private Integer is_delete;
    private String contents;
    private String qna_status;
    private String title;
    private Integer user_user_id;

    public QnaDto() {  }
    public QnaDto(Integer id,String title, String contents, Integer user_user_id) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user_user_id = user_user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getQna_status() {
        return qna_status;
    }

    public void setQna_status(String qna_status) {
        this.qna_status = qna_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUser_user_id() {
        return user_user_id;
    }

    public void setUser_user_id(Integer user_user_id) {
        this.user_user_id = user_user_id;
    }



    public void setBno(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QnaDto)) return false;
        QnaDto qnaDto = (QnaDto) o;
        return Objects.equals(id, qnaDto.id) && Objects.equals(is_delete, qnaDto.is_delete) && Objects.equals(contents, qnaDto.contents) && Objects.equals(qna_status, qnaDto.qna_status) && Objects.equals(title, qnaDto.title) && Objects.equals(user_user_id, qnaDto.user_user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, modifiedAt, is_delete, contents, qna_status, title, user_user_id);
    }
}



}
