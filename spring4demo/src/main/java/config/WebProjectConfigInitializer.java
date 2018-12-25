package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebProjectConfigInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
//		container.getServletContextName();
		
		// Create the spring rest servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(Config.class);

        // Register and map the spring rest servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("HelloWeb",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
//        dispatcher.setAsyncSupported(true);
        dispatcher.addMapping("/");

	}

}
