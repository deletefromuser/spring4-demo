package mybatis;

import dao.Student;

public interface StudentMapper {
	Student selectStudent(int id);
}
