package com.arcsapt.sms.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.arcsapt.sms")
@PropertySource(value = { "classpath:jdbc.properties",
		"classpath:hibernate.properties" })
public class MvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private Environment environment;

	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();

	}

	/*
	 * @Bean public SimpleControllerHandlerAdapter
	 * simpleControllerHandlerAdapter() { return new
	 * SimpleControllerHandlerAdapter(); }
	 * 
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry
	 * registry) { registry.addResourceHandler("/resources/**")
	 * .addResourceLocations("/resources/").setCachePeriod(31556926); }
	 * 
	 * @Override public void configureDefaultServletHandling(
	 * DefaultServletHandlerConfigurer configurer) { configurer.enable(); }
	 * 
	 * @Bean public DefaultServletHttpRequestHandler
	 * defaultServletHttpRequestHandler() { DefaultServletHttpRequestHandler
	 * defaultServletHttpRequestHandler = new
	 * DefaultServletHttpRequestHandler(); return
	 * defaultServletHttpRequestHandler; }
	 * 
	 * @Bean public LocalValidatorFactoryBean validator() {
	 * LocalValidatorFactoryBean validatorFactoryBean = new
	 * LocalValidatorFactoryBean();
	 * validatorFactoryBean.setValidationMessageSource(messageSource()); return
	 * validatorFactoryBean; }
	 */

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:message");
		return messageSource;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	// TODO database
	@Bean
	public ComboPooledDataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(environment
					.getProperty("jdbc.driverClassName"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		dataSource.setUser(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));

		// these are C3P0 properties
		dataSource.setAcquireIncrement(5);
		dataSource.setMaxIdleTime(3600);
		dataSource.setMaxIdleTimeExcessConnections(300);
		dataSource.setMinPoolSize(20);
		dataSource.setMaxPoolSize(100);
		dataSource.setNumHelperThreads(6);
		dataSource.setUnreturnedConnectionTimeout(3600);
		// this keep pool alive
		dataSource.setPreferredTestQuery("SELECT 1");
		dataSource.setTestConnectionOnCheckout(true);

		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] { environment
				.getProperty("hibernate.package") });
		factory.setHibernateProperties(hibProperties());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto",
				environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect",
				environment.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.globally_quoted_identifiers",
				environment
						.getProperty("hibernate.globally_quoted_identifiers"));
		properties.setProperty("hibernate.show_sql",
				environment.getProperty("hibernate.showsql"));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory());
		return transactionManager;
	}

	// TODO database

	// TODO SPRING security start

	@Bean
	public FilterChainProxy springSecurityFilterChain() {
		FilterChainProxy filterChainProxy = new FilterChainProxy();
		return filterChainProxy;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// TODO SPRING security end

	/*
	 * @Bean public WebContentInterceptor contentInterceptor(){
	 * WebContentInterceptor contentInterceptor = new WebContentInterceptor();
	 * contentInterceptor.setCacheSeconds(0);
	 * contentInterceptor.setUseExpiresHeader(true);
	 * contentInterceptor.setUseCacheControlHeader(true);
	 * contentInterceptor.setUseCacheControlNoStore(true); return
	 * contentInterceptor; }
	 */

/*	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setOrder(1);
		contentNegotiatingViewResolver.setMediaTypes("");
		return contentNegotiatingViewResolver;
	}
*/
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true)
				.parameterName("mediaType").ignoreAcceptHeader(true)
				.useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

}
