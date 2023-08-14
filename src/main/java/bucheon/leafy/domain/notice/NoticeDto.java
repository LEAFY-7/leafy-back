package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class NoticeDto {
    private Long id;
    private Date created_At;
    private Date modified_At;
    private Integer id_delete;
    private String contents;
    private Boolean is_hide;
    private String title;
    private Integer userId;

}
