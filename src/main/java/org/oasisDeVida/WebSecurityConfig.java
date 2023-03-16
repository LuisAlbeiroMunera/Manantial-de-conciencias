package org.oasisDeVida;

import org.oasisDeVida.model.service.UserDetailsMgrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsMgrImpl userDetailsService;

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**" };

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	     .antMatchers(resources).permitAll()  
	         .anyRequest().authenticated()
	         .and()
	     .formLogin()
	         .loginPage("/login")
	         .permitAll()
	         .defaultSuccessUrl("/inicio")
	         .failureUrl("/login?error=true")
	         .usernameParameter("username")
	         .passwordParameter("password")
	         .and()
	     .logout()
	         .permitAll()
	         .logoutSuccessUrl("/login?logout")
	         .deleteCookies("JSESSIONID");
	}
	
	
 

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}