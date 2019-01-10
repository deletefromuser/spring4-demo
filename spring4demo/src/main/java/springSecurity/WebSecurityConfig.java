package springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/logouted", "/hello/**", "/").permitAll()
		.antMatchers("/admin/**").hasRole("USER")
		.anyRequest().authenticated().and().formLogin()// .and().httpBasic();
				.loginPage("/login").permitAll();
		http.csrf().disable();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logouted");
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		manager.createUser(User.withUsername("tom").password("1").roles("USER").build());
		return manager;
	}
}
