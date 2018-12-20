package service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml" })
public class StudentServiceTest {

	@Resource
	StudentService studentService;

	@Resource
	String address3;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
		String ad3 = ctx.getBean("address3", String.class);
		studentService = ctx.getBean(StudentService.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStudent() {
		Student stu = studentService.getStudent(20);
		System.out.print("ID : " + stu.getId());
		System.out.print(", Name : " + stu.getName());
		System.out.println(", Age : " + stu.getAge());
		assertNotNull(stu);
	}

}
