package bucheon.leafy.util.request;

import bucheon.leafy.util.SortStatus;
import bucheon.leafy.util.SortType;
import lombok.Getter;

@Getter
public class PageRequest {

    private Integer page;


    public Integer limit;

    private Long offset;

    private String sortColumn;

    private SortStatus sortStatus;

    public PageRequest(Integer page, Integer limit, SortType sortType, SortStatus sortStatus) {
        this.page = page == null ? 1 : page;
        this.limit = limit == null ? 20 : limit;
        this.offset = Long.valueOf(this.limit * (this.page - 1));
        this.sortColumn = sortType.getText();
        this.sortStatus = sortStatus;
    }

}
