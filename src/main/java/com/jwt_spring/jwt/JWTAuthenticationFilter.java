package com.jwt_spring.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt_spring.entities.AppUser;
import com.jwt_spring.helpers.SecurityConstantes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        AppUser user=null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse
            response, FilterChain chain,
                                            Authentication authResult) throws IOException,
            ServletException {
        UserDetails springUser=(UserDetails)authResult.getPrincipal();
        String jwtToken= Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new
                        Date(System.currentTimeMillis()+ SecurityConstantes.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstantes.SECRET)
                .claim("roles", springUser.getAuthorities())
                .claim("email",springUser.getUsername())
                .claim("password",springUser.getPassword())
                .compact();
        response.addHeader(SecurityConstantes.HEADER_STRING,
                SecurityConstantes.TOKEN_PREFIX+jwtToken);
    }




}
