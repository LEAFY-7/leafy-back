package bucheon.leafy.application.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MailComponent {

    private final JavaMailSender javaMailSender;

    private final String verificationCode = createKey();

    @Value("${spring.mail.username}")
    private String sender;

    public MimeMessage createMessage(String recipient) throws MessagingException, UnsupportedEncodingException {

        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, recipient);
        message.setSubject("Leafy 회원가입 인증 코드");

        String msg = "";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += verificationCode;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress(sender,"prac_Admin"));

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);

        javaMailSender.send(message);

        return verificationCode;
    }
}
