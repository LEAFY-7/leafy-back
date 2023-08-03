<<<<<<< HEAD
package bucheon.leafy.application.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

    @Test
    void getRead() {
    }

    @Test
    void remove() {
    }

    @Test
    void write() {
    }

    @Test
    void modify() {
    }
}
=======
package bucheon.leafy.application.service;//package bucheon.leafy.application.service;
//
//import bucheon.leafy.application.mapper.CommentMapper;
//import bucheon.leafy.domain.comment.CommentDto;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//
//import javax.mail.internet.MimeMessage;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CommentServiceTest {
//
//    @Autowired
//    private CommentMapper commentMapper;
//    @Autowired
//    private CommentService commentService;
//
//
//    @Test
//    void remove() {
//        commentMapper.deleteAll();
//
//        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
//        assertEquals(1, commentService.write(commentDto));
//
//        List<CommentDto> resultList = commentMapper.selectAll();
//
//        int insertResult = commentService.write(commentDto);
//        assertEquals(1, insertResult);
//
//        Long id = resultList.get(0).getId();
//        commentService.remove(id, resultList.get(0).getUserId());
//        assertEquals(1, commentService.getCount());
//
//    }
//
//    @Test
//    void write() {
//        commentMapper.deleteAll();
//
//        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
//        assertEquals(1, commentService.write(commentDto));
//
//    }
//
//
//    @Test
//    void getRead() {
//        commentMapper.deleteAll();
//
//        for (int i = 1; i <= 10; i++) {
//            CommentDto commentDto = new CommentDto("asdfqwf", 12L, 14L);
//            commentService.write(commentDto);
//        }
//
//        List<CommentDto> resultList = commentMapper.selectAll();
//        Long id = resultList.get(0).getId();
//
//        CommentDto insertResult = commentService.getRead(id);
//        assertNotNull(insertResult);
//
//        CommentDto expectedObject = new CommentDto("expected contents", 12L, 14L);
//        assertEquals(expectedObject, insertResult);
//    }
//
//    @Test
//    void modify() {
//        commentMapper.deleteAll();
//
//        CommentDto commentDto = new CommentDto("asdfqwf", 12L,  14L);
//        assertEquals(1, commentService.write(commentDto));
//
//
//        Long id = commentService.getList().get(0).getId();
//        commentDto.setId(id);
//        commentDto.setComment("yes title");
//        commentDto.setUserId(11111L);
//        assertEquals(1, commentService.modify(commentDto));
//    }
//
//
//    @Test
//    void testEmailSending() throws Exception {
//        // mailSender 목(mock) 설정
//        JavaMailSender mailSender = mock(JavaMailSender.class);
//        commentService.setMailSender(mailSender);
//
//        // 이메일 전송 수행
//        MimeMessage message = mock(MimeMessage.class);
//        when(mailSender.createMimeMessage()).thenReturn(message);
//
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            mimeMessage.setFrom("sender@example.com");
//            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
//            mimeMessage.setSubject("테스트 이메일");
//            mimeMessage.setText("이것은 테스트 이메일입니다.");
//        };
//
//        // messagePreparator를 사용하여 sendEmail 메서드 호출
//        commentService.sendEmail(messagePreparator);
//
//        // createMimeMessage 메서드가 호출되었는지 확인
//        verify(mailSender).createMimeMessage();
//
//        // 준비된 메시지와 함께 send 메서드가 호출되었는지 확인
//        verify(mailSender).send(message);
//    }
//
//    @Test
//    void testGetEmail() throws Exception {
//
//        Long id = 1L;
//        String expectedEmail = "kiover1111@naver.com";
//
//        String actualEmail = null;
//        try {
//            actualEmail = commentService.getEmail(id);
//        } catch (Exception e) {
//            // Handle the exception if needed
//            fail("An exception occurred: " + e.getMessage());
//        }
//
//        assertEquals(expectedEmail, actualEmail);
//    }
//}
//
//
//
//
//
//
>>>>>>> ab55635b2b92322470b1d02be0adbadc5260dbe1
