package bucheon.leafy.application.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int updatePassword(String email, String password);

}
