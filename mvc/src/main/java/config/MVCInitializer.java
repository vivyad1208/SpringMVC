package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Used as Application Initializer for Spring framework by implementing {@link WebApplicationInitializer}.
 * This initializer overrides the {@link #onStartup(ServletContext)} of {@link WebApplicationInitializer}.
 * 
 * @author Vivek Yadav
 */
public class MVCInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		webCtx.register(MVCConfig.class);
		webCtx.setServletContext(servletContext);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);
		ServletRegistration.Dynamic dynamicServlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		dynamicServlet.setLoadOnStartup(1);
		dynamicServlet.addMapping("/web/*");
	}
}
