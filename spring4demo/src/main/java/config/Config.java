package config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ComponentScan("service,servlet,controller")
public class Config {

	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://localhost:3306/spring4demodb");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

//	@Bean
//	public DriverManagerDataSource getDatasource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("");
//		dataSource.setUrl("jdbc:mariadb://localhost:3306/spring4demodb");
//		dataSource.setUsername("root");
//		dataSource.setPassword("");
//		return dataSource;
//	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(datasource());
		factory.setConfigLocation(new ClassPathResource("mybatis/mybatis_config.xml"));
		return factory;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer obj = new MapperScannerConfigurer();
		obj.setSqlSessionFactoryBeanName("sqlSessionFactory");
		obj.setBasePackage("mybatis");
		return obj;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager obj = new DataSourceTransactionManager();
		obj.setDataSource(datasource());
		return obj;
	}
	
	@Bean
	public servlet.CtxUtil ctxUtil() {
		return new servlet.CtxUtil();
	}

//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory1() {
//		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//		factory.setDataSource(datasource());
//		factory.setConfigLocation(new ClassPathResource("classpath:mybatis/mybatis_config.xml"));
//		return factory;
//	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setViewNames("jsp/*");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setPrefix("/WEB-INF/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("utf-8");

		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolverThymeLeaf() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("utf-8");
		viewResolver.setOrder(2);
		viewResolver.setViewNames(new String[] { "thymeleaf/*" });
		return viewResolver;
	}

}
