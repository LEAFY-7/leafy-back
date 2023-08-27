package bucheon.leafy.util.request;

import bucheon.leafy.util.SortStatus;
import bucheon.leafy.util.SortType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class PageRequest {

    private Integer page;

    public Integer limit;

    @JsonIgnore
    private Long offset;

    private String sortColumn;

    private SortStatus sortStatus;


    public PageRequest(Integer page, Integer limit, SortType sortColumn, SortStatus sortStatus) {
        System.out.println("page, limit, sortColum, sortStatus");
        this.page = page == null ? 1 : page;
        this.limit = limit == null ? 20 : limit;
        this.offset = Long.valueOf(this.limit * (this.page - 1));
        this.sortColumn = sortColumn == null ? null : sortColumn.getText();
        this.sortStatus = sortStatus == null ? SortStatus.DESC : sortStatus;
    }

}