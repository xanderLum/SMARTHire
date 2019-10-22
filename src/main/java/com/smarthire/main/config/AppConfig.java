package com.smarthire.main.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.smarthire.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean()
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.smarthire.*" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource restDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/SMARTHire");
		dataSource.setUsername("root");
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties hibernateProperties() {
		final Properties hibernateProperties = new Properties();
		/*
		 * hibernateProperties.setProperty("hibernate.dialect",
		 * "org.hibernate.dialect.Oracle10gDialect");
		 */
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "false");
		return hibernateProperties;
	}
	
	final Properties javaMailProperties(){
		final Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("id", "org.springframework.mail.javamail.JavaMailSenderImpl");
		javaMailProperties.setProperty("mail.transport.protocol", "smtp");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.debug", "true");
		return javaMailProperties;
	}
	
	@Bean
	public JavaMailSenderImpl smtpProperties(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("ai.smarthire@gmail.com");
		javaMailSender.setPassword("thesis1205");
		javaMailSender.setJavaMailProperties(javaMailProperties());
		return javaMailSender;
		
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		// add new views
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/*");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/*");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/*");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/*");
		registry.addResourceHandler("/font-awesome/**").addResourceLocations("/font-awesome/*");
		registry.addResourceHandler("/less/**").addResourceLocations("/less/*");

		// add new css,scripts etc
		registry.addResourceHandler("/newscripts/css/**").addResourceLocations("/newscripts/css/*");
		registry.addResourceHandler("/newscripts/fonts/**").addResourceLocations("/newscripts/fonts/*");
		registry.addResourceHandler("/newscripts/images/**").addResourceLocations("/newscripts/images/*");
		registry.addResourceHandler("/newscripts/scripts/**").addResourceLocations("/newscripts/scripts/*");
		registry.addResourceHandler("/newscripts/noty-2.3.8/**").addResourceLocations("/newscripts/noty-2.3.8/*");
	}

}