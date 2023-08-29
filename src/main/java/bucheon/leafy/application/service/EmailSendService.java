package bucheon.leafy.application.service;

import bucheon.leafy.application.component.MailComponent;
import bucheon.leafy.application.repository.CertificationNumberRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.CertificationNumber;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.AuthenticationFailedException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailSendService {

    private final MailComponent mailComponent;
    private final CertificationNumberRepository certificationNumberRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateTemporaryPassword(String email, String phone) {
        User user = userRepository.findByEmailAndPhone(email, phone)
                .orElseThrow(UserNotFoundException::new);

        String password = randomPassword(10);

        String encodedPassword = passwordEncoder.encode(password);
        user.changePassword(encodedPassword);
        userRepository.save(user);

        MimeMessage passwordMessage = mailComponent.createPasswordMessage(email, password);
        mailComponent.sendMail(passwordMessage);
    }


    private String randomPassword(int length){
        String upperAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabets = upperAlphabets.toLowerCase();
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()";

        String allCharacters = upperAlphabets + lowerAlphabets + numbers + specialCharacters;

        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        randomString.append(upperAlphabets.charAt(random.nextInt(upperAlphabets.length())));
        randomString.append(lowerAlphabets.charAt(random.nextInt(lowerAlphabets.length())));
        randomString.append(numbers.charAt(random.nextInt(numbers.length())));
        randomString.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < length; i++) {
            randomString.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        char[] charArray = randomString.toString().toCharArray();
        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }

        return new String(charArray);
    }

    public void sendCertificationNumber(String email) {
        String codeNumber = mailComponent.createCode();
        MimeMessage message = mailComponent.createMessage(email, codeNumber);
        mailComponent.sendMail(message);

        certificationNumberRepository.deleteByEmail(email);

        CertificationNumber certificationNumber = CertificationNumber.of(email, codeNumber);
        certificationNumberRepository.save(certificationNumber);
    }

    public void confirmCertificationNumber(String email, String number) {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMinutes(3);

        boolean isPresent = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, number, startTime, endTime);
        if (!isPresent) throw new AuthenticationFailedException();
    }
}
