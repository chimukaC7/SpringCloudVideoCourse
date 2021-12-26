package com.appsdeveloperblog.photoapp.api.gateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    Environment environment;

    //we're going to create authorization, filter a filter that will validate provided
    //an authorization handler, JWT token.
    //If the token is valid, a request will pass through.
    //And if the token is not valid our API will not let that request to pass through.

    public AuthorizationFilter(AuthenticationManager authManager, Environment environment) {
        super(authManager);
        this.environment = environment;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        //we are reading that authorization header from a request object.
        //So we are reading the value which is included in the authorization HTTP header
        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null) {
            return null;
        }

        //replace the Bearer prefix of authorization header with empty string.
        //So I strip out the beer prefix so that they have a clean value of authorization token.
        String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "");

        String userId = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (userId == null) {
            return null;
        }

        /*
        * you can do some additional validations, for example, your request object may contain some additional
          details that you would like to validate and those additional details you might have added to your token
          * when you were creating that token.

         * Remember, token can contain more information than a single user I.D. You can put additional values
           there and then you can parse out those values out of your token and validate here
        * */
        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());

    }
}
