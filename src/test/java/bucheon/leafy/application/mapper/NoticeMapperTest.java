package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {
    NoticeMapper noticeMapper;

    @Test
    void findById() {
    }

    @Test
    void viewCnt() {
        noticeMapper.viewCnt(1L);
    }

    @Test
    void deleteById() {
    }

    @Test
    void save() {
        NoticeDto noticeDto = new NoticeDto("rrr", "rrr", 22222L);
        int savedNoticeId = noticeMapper.save(noticeDto);
        System.out.println(savedNoticeId);
    }

    @Test
    void editById() {
    }

    @Test
    void pageFindById() {
    }

    @Test
    void findTableIdByUserId() {
    }

}
