package com.jet.demo.faas.client.simple;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.requiresChannel().anyRequest().requiresSecure();
		http.authorizeRequests().antMatchers("/oauth2/**", "/idp/**", "/login", "/uilogin/**", "/images/**", "/css/**",
				"/js/**", "/.well-known/**").permitAll().anyRequest().authenticated().and().oauth2Login();
	}
}
