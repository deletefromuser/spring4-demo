package mybatis.mapper;

import mybatis.entity.Pet;

public interface PetMapper {
    int deleteByPrimaryKey(String name);

    int insert(Pet record);

    int insertSelective(Pet record);

    Pet selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(Pet record);

    int updateByPrimaryKey(Pet record);
}