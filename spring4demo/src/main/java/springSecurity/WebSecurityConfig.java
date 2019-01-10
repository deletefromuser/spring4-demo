package springSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
		http.authorizeRequests().antMatchers("/logouted", "/hello/**", "/").permitAll().antMatchers("/admin/**")
				.hasRole("ADMIN").anyRequest().access("hasRole('ADMIN') or hasRole('USER')").and().formLogin()// .and().httpBasic();
				.loginPage("/login").permitAll();
		http.csrf().disable();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/logouted");
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//		manager.createUser(User.withUsername("tom").password("1").roles("USER").build());
//		return manager;
//	}

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);

//		 create table users(
//	username varchar_ignorecase(50) not null primary key,
//	password varchar_ignorecase(50) not null,
//	enabled boolean not null
//);
//
//create table authorities (
//	username varchar_ignorecase(50) not null,
//	authority varchar_ignorecase(50) not null,
//	constraint fk_authorities_users foreign key(username) references users(username)
//);
//create unique index ix_auth_username on authorities (username,authority);
//
//
//
//--optional
//
//create table groups (
//	id bigint generated by default as identity(start with 0) primary key,
//	group_name varchar_ignorecase(50) not null
//);
//
//create table group_authorities (
//	group_id bigint not null,
//	authority varchar(50) not null,
//	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
//);
//
//create table group_members (
//	id bigint generated by default as identity(start with 0) primary key,
//	username varchar(50) not null,
//	group_id bigint not null,
//	constraint fk_group_members_group foreign key(group_id) references groups(id)
//);
	}

}
