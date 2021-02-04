package com.jwt_spring.jwt;

import com.jwt_spring.helpers.SecurityConstantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAutorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With,Content-Type, Access-Control-Request-Method, Access-Control-Request- Headers,authorization");
                response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);

        }else {
            String jwtToken=request.getHeader(SecurityConstantes.HEADER_STRING);
            if(jwtToken==null || !jwtToken.startsWith(SecurityConstantes.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            Claims claims= Jwts.parser()
                    .setSigningKey(SecurityConstantes.SECRET)
                    .parseClaimsJws(jwtToken.replace(SecurityConstantes.TOKEN_PREFIX,""))
                    .getBody();

            String username=claims.getSubject();

            ArrayList<Map<String, String>> roles=(ArrayList<Map<String, String>>)
                    claims.get("roles");

            Collection<GrantedAuthority> authorities=new ArrayList<>();

            roles.forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.get("authority")));
            });

            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(username, null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        }
    }



}


