package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.Student;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
		StudentService studentService = ctx.getBean("studentService", StudentService.class);
		Student stu=studentService.getStudent(20);
		System.out.print("ID : " + stu.getId());
		System.out.print(", Name : " + stu.getName());
		System.out.println(", Age : " + stu.getAge());
	}

}
