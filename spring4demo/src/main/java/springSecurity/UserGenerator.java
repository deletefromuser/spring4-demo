package springSecurity;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import config.AppcationContextConfig;
import mybatis.entity.Users;
import mybatis.mapper.UsersMapper;

public class UserGenerator {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppcationContextConfig.class);

		Users tom = new Users("tom", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1"), true);
		Users jack = new Users("jack", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2"), true);

		UsersMapper mapper = ctx.getBean(UsersMapper.class);
		mapper.insert(tom);
		mapper.insert(jack);
		ctx.close();
	}
}
