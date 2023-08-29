package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.user.CertificationNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class CertificationNumberRepositoryTest extends IntegrationTestSupport {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CertificationNumberRepository certificationNumberRepository;

    @Test
    @DisplayName("이메일로 발급된 인증번호를 발급한지 3분 이내로 확인한다.")
    void testExistsByEmailAndNumberAndCreatedAtBetween() {
        //given
        String email = "abcd@naver.com";
        String number = "123456";
        String failNumber = "111111";

        CertificationNumber certificationNumber = CertificationNumber.of(email, number);
        certificationNumberRepository.save(certificationNumber);

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusMinutes(3);

        //when
        boolean expectTrue = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, number, startTime, endTime);
        boolean expectFail = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, failNumber, startTime, endTime);

        //then
        assertThat(expectTrue).isTrue();
        assertThat(expectFail).isFalse();
    }

}