package org.example.config;

import org.example.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	SuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login.html","/logout","/reg*","/css/*","/image/*.jpg").permitAll()
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/login")
				.successHandler(successHandler)
				.usernameParameter("username")
				.passwordParameter("password");

		http.csrf().disable();
	}
}
