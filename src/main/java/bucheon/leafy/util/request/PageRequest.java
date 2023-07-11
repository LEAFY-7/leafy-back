package bucheon.leafy.util.request;

import bucheon.leafy.util.SortStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageRequest {

    private Integer page;

    public Integer limit;

    private Long offset;

    private String sortColumn;

    private SortStatus sortStatus;

    public PageRequest(Integer page, Integer limit, Long offset, String sortColumn, SortStatus sortStatus) {

        this.page = page == null ? 1 : page;
        this.limit = limit == null ? 20 : limit;
        this.offset = offset == null ? 0 : Long.valueOf(this.limit * (this.page - 1));
        this.sortColumn = sortColumn;
        this.sortStatus = sortStatus;
    }

}