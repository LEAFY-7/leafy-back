package bucheon.leafy.util.request;

import bucheon.leafy.util.SortStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageRequest {

    private int page;

    public int limit = 20;

    private int offset;

    private String sortColumn;

    private SortStatus sortStatus;

    private boolean isFirst;

    private boolean isLast;

    public PageRequest(int page, int limit, int offset, String sortColumn,
                       SortStatus sortStatus, boolean isFirst, boolean isLast) {

//        this.offset = page * limit;
        this.page = page;
        this.limit = limit;
        this.offset = offset;
        this.sortColumn = sortColumn;
        this.sortStatus = sortStatus;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }
}
