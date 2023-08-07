package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.user.response.CertificationNumberResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertificationNumberMapper {

    void saveCertificationNumber(String email, String number);
    CertificationNumberResponse findCertificationNumber();
}
