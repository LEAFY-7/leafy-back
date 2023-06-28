package bucheon.leafy.domain.notice;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class NoticeDto {

    private Integer id;
    private Date created_At;
    private Date modified_At;
    private Integer id_delete;
    private String contents;
    private Date is_hide;
    private Date title;
    private Integer User_id;

}
