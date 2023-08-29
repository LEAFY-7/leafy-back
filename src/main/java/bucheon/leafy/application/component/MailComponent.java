package bucheon.leafy.application.component;

import bucheon.leafy.exception.EmailSendException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
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

    @Value("${spring.mail.username}")
    private String sender;

    public String createCode() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public MimeMessage createMessage(String recipient, String code) {
        try {
            MimeMessage  message = javaMailSender.createMimeMessage();

            message.addRecipients(MimeMessage.RecipientType.TO, recipient);
            message.setSubject("Leafy 회원가입 인증 코드");

            String msg = "";
            msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
            msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
            msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
            msg += code;
            msg += "</td></tr></tbody></table></div>";

            message.setText(msg, "utf-8", "html");
            message.setFrom(new InternetAddress(sender,"prac_Admin"));

            return message;
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new EmailSendException();
        }
    }

    public MimeMessage createPasswordMessage(String recipient, String password) {
        try {
            MimeMessage  message = javaMailSender.createMimeMessage();

            message.addRecipients(MimeMessage.RecipientType.TO, recipient);
            message.setSubject("Leafy 임시 비밀번호 발급 메일");

            String msg = "";
            msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">임시 비밀번호 발급 완료</h1>";
            msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 임시 비밀번호로 로그인을 진행해주세요.</p>";
            msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">임시 비밀번호 발급을 요청하지 않았을 경우, 고객센터로 문의 부탁드립니다.</p>";
            msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
            msg += password;
            msg += "</td></tr></tbody></table></div>";

            message.setText(msg, "utf-8", "html");
            message.setFrom(new InternetAddress(sender,"prac_Admin"));

            return message;

        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new EmailSendException();
        }
    }

    public void sendMail(MimeMessage message) {
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new EmailSendException();
        }
    }
}
