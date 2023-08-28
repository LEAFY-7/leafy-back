package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.util.request.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeMapperTest {

    @Autowired
    NoticeMapper noticeMapper;

    @Test
    void findById() {
        noticeMapper.findById(1L);
    }

    @Test
    void viewCnt() {
        noticeMapper.viewCnt(1L);
    }

    @Test
    void deleteById() {
        noticeMapper.deleteById(21L);
    }

    @Test
    void modify(){
//        NoticeEditRequest noticeEditRequest = new NoticeEditRequest("asdf","title");
//        noticeMapper.editById(1L, noticeEditRequest );
//        System.out.println(noticeMapper.editById(1L, noticeEditRequest ));
    }
    @Test
    void pageFindById() {


    }
}
