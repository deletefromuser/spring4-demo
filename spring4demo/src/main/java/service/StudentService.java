package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.Student;
import mybatis.StudentMapper;

@Service
public class StudentService {
	
	@Resource
	StudentMapper studentMapper;
	
	public Student getStudent(int id) {
		return studentMapper.selectStudent(id);
	}

}
