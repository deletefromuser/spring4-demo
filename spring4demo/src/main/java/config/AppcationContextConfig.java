package config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import blog.BlogDto;
import dao.Blog;
import dao.Student;
import mybatis.entity.BlogContent;
import springSecurity.csrf.CSRFHandlerInterceptor;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity
@ComponentScan("service,servlet,controller,springSecurity,blog")
public class AppcationContextConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://localhost:3306/spring4demodb");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(ResourcePatternResolver resourcePatternResolver) throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(datasource());
		// factory.setConfigLocation(new
		// ClassPathResource("mybatis/mybatis_config.xml"));
		factory.setMapperLocations(resourcePatternResolver.getResources("classpath*:mybatis/**/*apper.xml"));
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

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setViewNames("jsp/*");
		resolver.setOrder(2);
		return resolver;
	}

	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setTemplateMode("HTML");
		templateResolver.setPrefix("/WEB-INF/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
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
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] { "thymeleaf/*" });
		return viewResolver;
	}

	@Bean
	@Scope("singleton")
	public DozerBeanMapperFactoryBean dozerMapper(ResourcePatternResolver resourcePatternResolver) throws IOException {
		DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
		factoryBean.setMappingFiles(resourcePatternResolver.getResources("classpath*:/*dozer-mapping.xml"));
		return factoryBean;
	}

	@Bean
	public Student student1() {
		Student stu = new Student();
		stu.setAge(14);
		stu.setId(3652);
		stu.setName("tom");
		return stu;
	}

	@Bean
	@RequestScope
	public BlogContent blogContent() {
		return new BlogContent();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Blog blog() {
		return new Blog();
	}

	@Bean
	@SessionScope
	public BlogDto blogDtoSeesion() {
		return new BlogDto();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}

	@Bean
	@RequestScope
	public Logger logger() {
		return LoggerFactory.getLogger("test");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		super.addFormatters(registry);
		registry.addFormatter(new Formatter<Date>() {

			@Override
			public String print(Date object, Locale locale) {
				return object.toString();
			}

			@Override
			public Date parse(String text, Locale locale) throws ParseException {
				Date date = new Date();

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					date = sdf.parse(text);
				} catch (Exception e) {

				}

				return date;
			}
		});
	}

	@Bean
	public Parser markdownParser() {
		MutableDataSet options = new MutableDataSet();
		return Parser.builder(options).build();
	}

	@Bean
	public HtmlRenderer markdownRender() {
		MutableDataSet options = new MutableDataSet();
		return HtmlRenderer.builder(options).build();
	}

}
