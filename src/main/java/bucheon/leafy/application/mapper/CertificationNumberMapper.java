package bucheon.leafy.application.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertificationNumberMapper {

    void saveCertificationNumber(String email, String number);
}