package com.ninjaone.backendinterviewproject.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/api/v1/login")) {
            filterChain.doFilter(request, response);
        } else {

            String authorizationHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {

                    String token = authorizationHeader.substring("Bearer ".length());

                    Algorithm algorithm = Algorithm.HMAC256("secret_for_token".getBytes());

                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();

                    String[] roles = decodedJWT.getClaim("authorities").asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    java.util.Arrays.stream(roles).forEach(authority -> {
                        authorities.add(new SimpleGrantedAuthority(authority));
                    });

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);

                } catch (Exception e) {

                    response.setHeader("error", e.getMessage());
                    response.setStatus(org.springframework.http.HttpStatus.FORBIDDEN.value());

                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }

    }
}
