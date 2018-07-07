package config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * The primary configuration implementing MVC. <br/>
 * Implements the Web MVC using {@link EnableWebMvc} annotation. <br/>
 * Spring components are identified using {@link ComponentScan} annotation.
 * <br/>
 * <br/>
 * Defines and configures following {@link Bean}s: <br/>
 * 1. {@link ViewResolver}: with prefix '{@code /WEB-INF/jsp/}', and suffix
 * '{@code .jsp}' <br/>
 * <br/>
 * Configures following components: <br/>
 * 1. Default-Servlet to serve the resources in
 * {@link #configureDefaultServletHandling} using
 * {@link DefaultServletHandlerConfigurer#enable()}. <br/>
 * 2. Add Interceptors in {@link #addInterceptors} using
 * {@link InterceptorRegistration#addPathPatterns(String...)}. <br/>
 * 
 * @author Vivek Yadav
 */
@Configuration
@EnableWebMvc /* Alternative for the -> <mvc:annotation-driven/> */
@ComponentScan("app") /* Alternative for the -> <context:component-scan base-package="com.pnb" /> */
@EnableTransactionManagement
@EnableAspectJAutoProxy
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
		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HttpInterceptor interceptor = new HttpInterceptor();
		InterceptorRegistration register = registry.addInterceptor(interceptor);
		register.addPathPatterns("/homepage");
	}

	/*
	 * @Bean public MessageSource messageSource() { ResourceBundleMessageSource
	 * messageSource = new ResourceBundleMessageSource();
	 * messageSource.setBasename("message.source.basename");
	 * messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(
	 * environment.getRequiredProperty(
	 * PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));
	 * 
	 * return messageSource; }
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mvc");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	/*@Bean
	public EntityManagerFactory entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("app.mvc.repository");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}*/

	@Bean
	public JpaTransactionManager transactionManager() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("app.mvc.dto");
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

		return entityManagerFactoryBean;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.globally_quoted_identifiers", "true");
		// "hibernate.ejb.naming_strategy"
		properties.put("hibernate.show_sql", "true");
		return properties;
	}
}



/*
hibernate.hbm2ddl.auto
Automatically validates or exports schema DDL to the database when the SessionFactory is created.
With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.

validate: validate the schema, makes no changes to the database.
update: update the schema.
create: creates the schema, destroying previous data.
create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
*/
