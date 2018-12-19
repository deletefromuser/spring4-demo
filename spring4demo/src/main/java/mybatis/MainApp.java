package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.Student;

public class MainApp {
	public static void main(String[] args) {
		try {
			String resource = "mybatis/mybatis_config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			SqlSession session = sqlSessionFactory.openSession();
			try {
				StudentMapper mapper = session.getMapper(StudentMapper.class);
				Student stu = mapper.selectStudent(2);
				System.out.print("ID : " + stu.getId());
				System.out.print(", Name : " + stu.getName());
				System.out.println(", Age : " + stu.getAge());

				stu = (Student) session.selectOne("mybatis.StudentMapper.selectStudent", 25);
				System.out.print("ID : " + stu.getId());
				System.out.print(", Name : " + stu.getName());
				System.out.println(", Age : " + stu.getAge());
			} finally {
				session.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
