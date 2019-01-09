package config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.val;
import springSecurity.WebSecurityConfig;

public class WebXmlConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
//		container.getServletContextName();

		// Create the spring rest servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(AppcationContextConfig.class, WebSecurityConfig.class);

		// Register and map the spring rest servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("HelloWeb",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
//        dispatcher.setAsyncSupported(true);
		dispatcher.addMapping("/");

//		val springSecurityFilterChain = container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
//		springSecurityFilterChain.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/**");
	}

}
