package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Used as Application Initializer for Spring framework by implementing {@link WebApplicationInitializer}.
 * This initializer overrides the {@link WebApplicationInitializer#onStartup(ServletContext)}.
 * 
 * @author Vivek Yadav
 */
public class MVCInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// Load Spring web application configuration
		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		webCtx.register(MVCConfig.class);
		webCtx.setServletContext(servletContext);
		webCtx.refresh();

		// Create and register the DispatcherServlet
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
		ServletRegistration.Dynamic dynamicServlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		dynamicServlet.setLoadOnStartup(1);
		dynamicServlet.addMapping("/web/*");
	}
}
