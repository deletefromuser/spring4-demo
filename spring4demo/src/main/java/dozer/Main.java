package dozer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.github.dozermapper.core.Mapper;

import config.AppcationContextConfig;
import dao.Student;
import mybatis.entity.Student2;

public class Main {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppcationContextConfig.class);
		Mapper dozerMapper = (Mapper) ctx.getBean("dozerMapper");
		Worker worker = dozerMapper.map((Student) ctx.getBean("student1"), Worker.class);
		System.out.println(dozerMapper);
		System.out.println(worker);
		System.out.println((Mapper) ctx.getBean("dozerMapper"));
		WorkerB workerB = new WorkerB();
		dozerMapper.map((Student) ctx.getBean("student1"), workerB);
		System.out.println(workerB);
		Student2 stu2 = dozerMapper.map((Student) ctx.getBean("student1"), Student2.class);
		System.out.println(stu2);
	}
}
