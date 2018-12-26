package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.Config;
import dao.Student;
import mybatis.entity.Pet;
import mybatis.mapper.PetMapper;
import servlet.CtxUtil;

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

//				PetMapper pm = session.getMapper(PetMapper.class);
				PetMapper pm = new AnnotationConfigApplicationContext(Config.class).getBean(PetMapper.class);
				Pet pet = new Pet();
				pet.setBirth(new Date());
				pet.setDeath(new Date());
				pet.setName("dd");
				pm.insert(pet);

				Pet rp = pm.selectByPrimaryKey("dd");
				System.out.print("pet name : " + rp.getName());
				System.out.print(", pet Birth : " + rp.getBirth());
				System.out.println(", pet Death : " + rp.getDeath());
			} finally {
				session.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
