package bucheon.leafy.util.request;

import bucheon.leafy.util.SortStatus;
import bucheon.leafy.util.SortType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PageRequest {

    @Schema(description = "옵션, null 가능")
    private Integer page;

    @Schema(description = "옵션, null 가능")
    public Integer limit;

    @JsonIgnore
    private Long offset;

    @Schema(description = "옵션, null 가능, 데이터 보낼시 CREATE_DATE, FAVORITE, MODIFY_DATE 가능")
    private String sortColumn;

    @Schema(description = "옵션, null 가능, 데이터 보낼시 ASC, DESC 가능")
    private SortStatus sortStatus;


    public PageRequest(Integer page, Integer limit, SortType sortColumn, SortStatus sortStatus) {
        this.page = page == null ? 1 : page;
        this.limit = limit == null ? 20 : limit;
        this.offset = Long.valueOf(this.limit * (this.page - 1));
        this.sortColumn = sortColumn == null ? null : sortColumn.getText();
        this.sortStatus = sortStatus == null ? SortStatus.DESC : sortStatus;
    }

}