package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.user.CertificationNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class CertificationNumberRepositoryTest extends IntegrationTestSupport {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CertificationNumberRepository certificationNumberRepository;

    @TestFactory
    @DisplayName("이메일로 발급된 인증번호를 발급한지 3분 이내로 확인한다.")
    Collection<DynamicTest> testExistsByEmailAndNumberAndCreatedAtBetween() {
        //given
        String email = "abcd@naver.com";
        String number = "123456";
        String failNumber = "111111";

        CertificationNumber certificationNumber = CertificationNumber.of(email, number);
        certificationNumberRepository.save(certificationNumber);

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(3);
        LocalDateTime expireTime = LocalDateTime.now().minusMinutes(1);

        return List.of(
                DynamicTest.dynamicTest("이메일과 인증번호를 정상 입력하고 예상시간 내로 인증을 하면 성공한다.", () -> {
                    //when
                    boolean expectTrue = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, number, startTime, endTime);

                    // then
                    assertThat(expectTrue).isTrue();
                }),

                DynamicTest.dynamicTest("이메일을 정상 입력하고 잘못된 인증번호를 예상시간 내로 인증을 하면 실패한다.", () -> {
                    //when
                    boolean expectFail = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, failNumber, startTime, endTime);

                    //then
                    assertThat(expectFail).isFalse();
                }),

                DynamicTest.dynamicTest("이메일과 인증번호를 정상 입력하고 예상시간 밖에 인증을 하면 실패한다.", () -> {
                    //when
                    boolean expectFail2 = certificationNumberRepository.existsByEmailAndNumberAndCreatedAtBetween(email, number, startTime, expireTime);

                    //then
                    assertThat(expectFail2).isFalse();
                })
        );

    }

}