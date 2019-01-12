package mybatis.mapper;

import mybatis.entity.Authorities;

public interface AuthoritiesMapper {
    int insert(Authorities record);

    int insertSelective(Authorities record);
}