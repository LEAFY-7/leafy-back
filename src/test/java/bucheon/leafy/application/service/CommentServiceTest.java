package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.CommentMapper;
import bucheon.leafy.domain.comment.CommentDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentServiceTest {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;


    @Test
    void remove() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1, commentService.write(commentDto));

        List<CommentDto> resultList = commentMapper.selectAll();

        int insertResult = commentService.write(commentDto);
        assertEquals(1, insertResult);

        Long id = resultList.get(0).getId();
        commentService.remove(id, resultList.get(0).getUserId());
        assertEquals(1, commentService.getCount());

    }

    @Test
    void write() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1, commentService.write(commentDto));

    }


    @Test
    void getRead() {
        commentMapper.deleteAll();

        commentMapper.deleteAll();
        for (int i = 1; i <= 10; i++) {
            CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
            commentService.write(commentDto);
        }

        List<CommentDto> resultList = commentMapper.selectAll();
        Long id = resultList.get(0).getId();

        List<CommentDto> insertResult = commentService.getRead(id);
        assertEquals(1, insertResult.size());
    }

    @Test
    void modify() {
        commentMapper.deleteAll();

        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
        assertEquals(1, commentService.write(commentDto));


        Long id = commentService.getList().get(0).getId();
        commentDto.setId(id);
        commentDto.setComment("yes title");
        commentDto.setUserId(11111L);
        assertEquals(1, commentService.modify(commentDto));
    }


    @Test
    void testEmailSending() throws Exception {
        // Set up the mailSender mock
        JavaMailSender mailSender = mock(JavaMailSender.class);
        commentService.setMailSender(mailSender);

        // Perform the email sending
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            mimeMessage.setFrom("sender@example.com");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
            mimeMessage.setSubject("Test Email");
            mimeMessage.setText("This is a test email.");
        };

    }

    @Test
    void testSendEmailNotification() throws Exception {
        // 필요한 의존성들을 목 객체로 설정합니다.
        MimeMessagePreparator messagePreparator = mock(MimeMessagePreparator.class);
        JavaMailSender mailSender = mock(JavaMailSender.class);
        when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));

        // commentService에 목 객체 mailSender를 설정합니다.
        commentService.setMailSender(mailSender);

        // sendEmailNotification 메소드를 호출합니다.
        CommentDto commentDto = new CommentDto(57L,"asdfqwf", 12L  );
        commentService.sendEmailNotification(commentDto);

        // sendEmailNotificationtest 메소드를 호출합니다.
        commentService.sendEmailNotificationtest(messagePreparator);

        // 목 객체 mailSender를 통해 send 메소드가 호출되었는지 검증합니다.
        verify(mailSender).send(eq(messagePreparator));

        // write 메소드를 호출하여 commentDto를 데이터베이스에 삽입합니다.
        commentService.write(commentDto);

        // 삽입된 comment의 ID를 가져옵니다.
        Long id = commentDto.getId();

        // getEmail 메소드를 호출하여 이메일을 가져옵니다.
        String email = commentService.getEmail(id);

        // 이메일이 null이 아닌지 확인합니다.
        assertNotNull(email);
        // 필요에 따라 추가적인 검증(assertion)을 수행할 수 있습니다.
        // 예를 들어, 가져온 이메일이 기대하는 이메일과 일치하는지 확인할 수 있습니다.
        // assertEquals(expectedEmail, email);
    }

    @Test
    void testGetEmail() throws Exception {

        Long id = 57L;
        String expectedEmail = "kiover1111@naver.com";

        String actualEmail = null;
        try {
            actualEmail = commentService.getEmail(id);
        } catch (Exception e) {
            // Handle the exception if needed
            fail("An exception occurred: " + e.getMessage());
        }

        assertEquals(expectedEmail, actualEmail);
    }
}






