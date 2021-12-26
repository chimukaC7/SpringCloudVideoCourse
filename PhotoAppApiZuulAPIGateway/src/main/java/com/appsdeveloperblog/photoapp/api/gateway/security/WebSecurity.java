package com.appsdeveloperblog.photoapp.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.csrf().disable();
    	http.headers().frameOptions().disable();
    	http.authorizeRequests()
    	.antMatchers(environment.getProperty("api.users.actuator.url.path")).permitAll()
    	.antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
    	.antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
		//allow access to registration and login URLs
    	.antMatchers(HttpMethod.POST, environment.getProperty("api.registration.url.path")).permitAll()
    	.antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
    	.anyRequest().authenticated()//any other HTTP request should be allowed, but user will need to be authenticated
    	.and()
		// create authorization filter and configure that filter to validate the provided JWT token.
		// And if it is valid, we will let the request through.
    	.addFilter(new AuthorizationFilter(authenticationManager(), environment));

		//So this one will make our API stateless.
		//this session will uniquely identify the client application while this client application is communicating
		//with the server.
		//So if you have multiple different client applications communicating with your API, then you will have
		//multiple different HTTP sessions created.
		//So this sessions and the cookies that will be created can cache some information about the request.
		//and this can make our authorization header, which contains the  JWT token in that header, also cache.
		//And then even if we do not provide the authorization header in the form any the following HTTP request, the request
		//will still be authorized and we do not want that to happen
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
