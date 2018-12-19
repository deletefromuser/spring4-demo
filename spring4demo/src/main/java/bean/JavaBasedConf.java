package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JavaBasedConfA.class)
public class JavaBasedConf {
	@Bean
	public Msg msg() {
		return new Msg();
	}
}
