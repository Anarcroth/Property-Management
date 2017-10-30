package com.property.manager.config;

import com.property.manager.authen.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// CSRF has to be disabled in order not to throw an error during runtime.
		http	.csrf().disable()
				.authorizeRequests()
				.antMatchers("/home", "/js/**", "/css/**", "/img/**", "/log/sign_up").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/log")
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/log/logout")).logoutSuccessUrl("/log");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {

		auth.authenticationProvider(new CustomAuthenticationProvider());
	}
}
