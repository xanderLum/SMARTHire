package com.smarthire.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	@Qualifier("userDetailsService")
//	UserDetailsService userDetailsService;

	//getting the user and also the password for login authentication
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	// .csrf() is optional, enabled by default, if using
	// WebSecurityConfigurerAdapter constructor
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    .authorizeRequests()
			.antMatchers("/jobseeker/home/**").permitAll()
			.and()
	    .formLogin()
	    	.loginPage("/jobseeker")
	    	.loginProcessingUrl("/j_spring_security_check")
	    	.failureUrl("/admin?error")
	    	.defaultSuccessUrl("/adminHome", true)
		    .usernameParameter("user")
		    .passwordParameter("pass")		
			.and()
	    .logout()
	    	.logoutSuccessUrl("/admin?logout")
			.and()
	    .csrf().disable(); 	
	    /*http
	    .authorizeRequests()
			.antMatchers("/adminHome/**").access("hasRole('ADMIN')")
			.and()
	    .formLogin()
	    	.loginPage("/admin")
	    	.loginProcessingUrl("/j_spring_security_check")
	    	.failureUrl("/admin?error")
	    	.defaultSuccessUrl("/adminHome", true)
		    .usernameParameter("user")
		    .passwordParameter("pass")		
			.and()
	    .logout()
	    	.logoutSuccessUrl("/admin?logout")
			.and()
	    .csrf()
	    	.and()
	    .sessionManagement()
	        .maximumSessions(1)
	        .expiredUrl("/admin?expired"); 	*/	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}