package com.efuture.assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.efuture.assignment.auth.JwtAuthFilter;
import com.efuture.assignment.auth.JwtAuthenticationEntrypoint;
import com.efuture.assignment.auth.JwtAuthenticationProvider;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private JwtAuthenticationEntrypoint jwtAuthEndPoint;

	@Override
	public void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		// we don't need CSRF because our token is invulnerable
		.csrf().disable()
		.cors().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEndPoint).and()

		// don't create session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
		.antMatchers(
				"/product",
				"/product/*"
				).permitAll()
		.anyRequest()
		.authenticated();

		httpSecurity
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity
		.headers()
		.frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
		.cacheControl();

//		httpSecurity
//		.requiresChannel()
//		.anyRequest()
//		.requiresSecure();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// AuthenticationTokenFilter will ignore the below paths
		web
		.ignoring()
		.antMatchers(
				HttpMethod.POST,
				"/login"
				)
			// allow anonymous resource requests
		.and()
		.ignoring()
		.antMatchers(
				HttpMethod.GET,			
				"/*",
				"/*.html",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.css",
				"/raise/",
				"/*.css",
				"/**/*.js"
				);
	}
}
