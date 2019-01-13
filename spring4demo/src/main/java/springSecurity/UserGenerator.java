package springSecurity;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import config.AppcationContextConfig;
import lombok.val;
import mybatis.entity.Authorities;
import mybatis.entity.Users;
import mybatis.mapper.AuthoritiesMapper;
import mybatis.mapper.UsersMapper;

public class UserGenerator {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppcationContextConfig.class);

		Users tom = new Users("tom", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1"), true);
		Users jack = new Users("jack", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2"), true);
		// Users tom = new Users("tom", new
		// Md5PasswordEncoder().encodePassword("1", null), true);
		// Users jack = new Users("jack", new
		// Md5PasswordEncoder().encodePassword("2", null), true);

		UsersMapper mapper = ctx.getBean(UsersMapper.class);
		mapper.insert(tom);
		mapper.insert(jack);
		
		val tomRole = new Authorities("tom", "ROLE_ADMIN");
		val jackRole = new Authorities("jack", "ROLE_USER");
		AuthoritiesMapper authoritiesMapper = ctx.getBean(AuthoritiesMapper.class);
		authoritiesMapper.insert(tomRole);
		authoritiesMapper.insert(jackRole);
		ctx.close();

		val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println(encoder.encode("123456789"));
		System.out.println(encoder.encode("1"));
		System.out
				.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().matches("1", encoder.encode("1")));

		System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().matches("2",
				"{bcrypt}$2a$10$iFVVI0y8nLiiBXDDg4Z4Quhq7SKmGFMC1oMPzqSEmQCpX4dDSZK9."));

		System.out.println(new Md5PasswordEncoder().encodePassword("1", null));
		System.out.println(new Md5PasswordEncoder().encodePassword("1", null));
	}
}
