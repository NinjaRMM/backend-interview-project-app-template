package com.ninjaone.backendinterviewproject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ninjaone.backendinterviewproject.security.filter.CustomAuthenticationFilter;
import com.ninjaone.backendinterviewproject.security.filter.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String CUSTOMER_ROLE = "CUSTOMER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String API_V1_ADMIN = "/api/v1/admin/**";
    private static final String API_V1_CUSTOMER = "/api/v1/customer/**";
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManagerBean());

        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/login/**").permitAll();
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, API_V1_ADMIN).hasAnyAuthority(ADMIN_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.GET, API_V1_ADMIN).hasAnyAuthority(ADMIN_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.PUT, API_V1_ADMIN).hasAnyAuthority(ADMIN_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, API_V1_ADMIN).hasAnyAuthority(ADMIN_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.POST, API_V1_CUSTOMER).hasAnyAuthority(CUSTOMER_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.GET, API_V1_CUSTOMER).hasAnyAuthority(CUSTOMER_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, API_V1_CUSTOMER).hasAnyAuthority(CUSTOMER_ROLE);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);

        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

 

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
