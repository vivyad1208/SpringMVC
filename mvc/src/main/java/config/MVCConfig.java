package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


/**
 * The primary configuration implementing MVC. <br/>
 * Implements the Web MVC using {@link EnableWebMvc} annotation. <br/>
 * Spring components are identified using {@link ComponentScan} annotation. <br/>
 *  <br/>
 * Defines and configures following {@link Bean}s: <br/>
 *   1. {@link ViewResolver}: with prefix '{@code /WEB-INF/jsp/}', and suffix '{@code .jsp}'
 * 
 * @author Vivek Yadav
 */
@EnableWebMvc				/* Alternative for the -> <mvc:annotation-driven/> */
@ComponentScan("app")	/* Alternative for the -> <context:component-scan base-package="com.pnb" /> */
public class MVCConfig extends WebMvcConfigurerAdapter {

	/**
	 * Bean for SpringMVC's 'ViewResolver'.
	 * 
	 * @return {@link ViewResolver}
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// super.configureDefaultServletHandling(configurer)
		configurer.enable();
	}

}
