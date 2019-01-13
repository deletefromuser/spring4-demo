package springSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/logouted", "/hello/**", "/").permitAll().antMatchers("/admin/**")
				.hasRole("ADMIN").anyRequest().access("hasRole('ADMIN') or hasRole('USER')").and().formLogin()// .and().httpBasic();
				.loginPage("/login").permitAll().failureHandler(new AuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						System.out.println("login user is " + request.getParameter("username"));
						System.out.println("login password is " + request.getParameter("password"));
						response.sendRedirect("login?error");
					}
				});
		http.csrf().disable();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// manager.createUser(User.withUsername("user").password("password").roles("USER").build());
	// manager.createUser(User.withUsername("tom").password("1").roles("USER").build());
	// return manager;
	// }

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	// @Override
	// public JdbcDaoImpl userDetailsService() {
	// return new BCryptPasswordEncoder();
	// }

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
