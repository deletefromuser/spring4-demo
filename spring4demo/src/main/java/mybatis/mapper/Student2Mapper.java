package mybatis.mapper;

import mybatis.entity.Student2;

public interface Student2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student2 record);

    int insertSelective(Student2 record);

    Student2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student2 record);

    int updateByPrimaryKey(Student2 record);
}