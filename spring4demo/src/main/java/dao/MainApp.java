package dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("dao/student.xml");

		StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");

		System.out.println("------Records Creation--------");
		studentJDBCTemplate.create("Zara", 11);
		studentJDBCTemplate.create("Nuha", 2);
		studentJDBCTemplate.create("Ayan", 15);

		System.out.println("------Listing Multiple Records--------");
		List<Student> students = studentJDBCTemplate.listStudents();

		for (Student record : students) {
			System.out.print("ID : " + record.getId());
			System.out.print(", Name : " + record.getName());
			System.out.println(", Age : " + record.getAge());
		}

		System.out.println("----Updating Record with ID = 2 -----");
		studentJDBCTemplate.update(2, 20);

		System.out.println("----Listing Record with ID = 2 -----");
		Student student = studentJDBCTemplate.getStudentBigerThan(2);
		System.out.print("ID : " + student.getId());
		System.out.print(", Name : " + student.getName());
		System.out.println(", Age : " + student.getAge());

		TransactionTemplate tt = context.getBean("transactionTemplate", TransactionTemplate.class);
		tt.execute(new TransactionCallback<List<Student>>() {

			@Override
			public List<Student> doInTransaction(TransactionStatus arg0) {
				studentJDBCTemplate.create("zhangsan", 15);
				return null;
			}
		});
		
		Student st = studentJDBCTemplate.getStudents(2);
	}
}
