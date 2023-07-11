package bucheon.leafy.application.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmailServiceTest {

    private GreenMail greenMail;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender mailSender;

    @BeforeEach
    void setup() {
        ServerSetup serverSetup = new ServerSetup(25, null, "smtp");
        greenMail = new GreenMail(serverSetup);
        greenMail.start();

        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
        javaMailSender.setPort(serverSetup.getPort());
        javaMailSender.setHost(serverSetup.getBindAddress());
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testSendEmailNotification() throws Exception {
        String recipientEmail = "recipient@example.com";
        String subject = "Test Email";
        String content = "This is a test email.";

        emailService.sendEmailNotification(recipientEmail, subject, content);

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1, receivedMessages.length);

        MimeMessage receivedMessage = receivedMessages[0];
        assertEquals(recipientEmail, receivedMessage.getAllRecipients()[0].toString());
        assertEquals(subject, receivedMessage.getSubject());
        assertEquals(content, receivedMessage.getContent().toString().trim()); // Updated assertion
    }
}
