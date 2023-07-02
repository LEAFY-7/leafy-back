package bucheon.leafy.domain.qna;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;


@Data
public class PageHandler {
    private SearchHandler searchHandler; // page, pageSize, option, keyword 여기로 받는다
    static  final int NAV_SIZE = 10; // page navigation size
    private int totalCnt; // 게시물의 총 갯수
    private int totalPage; // 전체 페이지의 갯수
    private int beginPage; // 화면에 보여줄 첫 페이지
    private int endPage; // 화면에 보여줄 마지막 페이지
    private boolean showNext = false; // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showPrev = false; // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false

    public PageHandler(int totalCnt, Integer page) {
        this(totalCnt, new SearchHandler(page, 10));
    }

    public PageHandler(int totalCnt, Integer page, Integer pageSize) { this(totalCnt, new SearchHandler(page, pageSize)); }

    public PageHandler(int totalCnt, SearchHandler searchHandler) {
        this.totalCnt = totalCnt;
        this.searchHandler = searchHandler;

        doPaging(totalCnt, searchHandler); //호출해서 페이징 처리
    }


    private void doPaging(int totalCnt, SearchHandler searchHandler) {
        this.totalPage = totalCnt / searchHandler.getPageSize() + (totalCnt % searchHandler.getPageSize()==0? 0:1);
        this.searchHandler.setPage(Math.min(searchHandler.getPage(), totalPage));  // page가 totalPage보다 크지 않게
        this.beginPage = (this.searchHandler.getPage() -1) / NAV_SIZE * NAV_SIZE + 1; // 11 -> 11, 10 -> 1, 15->11. 따로 떼어내서 테스트
        this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
        this.showPrev = beginPage!=1;
        this.showNext = endPage!=totalPage;
    }

    public String getQueryString() {    //페이지 지정해주지 않으면 이걸 쓰고
        return getQueryString(this.searchHandler.getPage());
    }
    public String getQueryString(Integer page) {

        return UriComponentsBuilder.newInstance()
                .queryParam("page",     page)
                .queryParam("pageSize", searchHandler.getPageSize())
                .queryParam("option",   searchHandler.getOption())
//                .queryParam("keyword",  searchHandler.getKeyword())
                .build().toString();
    }

    void print() {
        System.out.println("page="+ searchHandler.getPage());
        System.out.print(showPrev? "PREV " : "");

        for(int i=beginPage;i<=endPage;i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext? " NEXT" : "");
    }
}
