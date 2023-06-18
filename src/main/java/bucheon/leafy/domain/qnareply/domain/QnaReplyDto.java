package bucheon.leafy.domain.qnareply.domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class QnaReplyDto {
    private Integer rid;
    private Integer id;
    private Integer user_id;
    private Integer reply;
    private Date  created_at;
    private Date  modified_at;

    public QnaReplyDto() {}

    public QnaReplyDto(Integer rid, Integer id, Integer user_id, Integer reply) {

        this.rid = rid;
        this.id = id;
        this.user_id = user_id;
        this.reply = reply;
    }
}