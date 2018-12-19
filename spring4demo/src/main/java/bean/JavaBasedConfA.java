package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBasedConfA {
	@Bean
	public MsgA msgA() {
		return new MsgA();
	}
}
