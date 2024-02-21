package com.fursah.BankSystem.config;

import com.fursah.BankSystem.service.auth.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTH_PATH = "/api/v1/auth/**";
    public static final String USER_PATH = "/api/v1/user/**";
    private static final List<String> Allowed_METHODS = Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH");
    private static final List<String> ALLOWED_HEADERS = Arrays.asList("x-request-with", "authorization", "Content-Type", "Authorization",
            "credentials", "X-XSRF-TOKEN", "X-Refresh-Token", "X-Client-Id", "x-client-id");
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .configurationSource(request -> getCorsConfiguration())
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_PATH).permitAll()
                .antMatchers(USER_PATH).hasAuthority("user")
                .anyRequest().authenticated();
        http.addFilterBefore(new JWTAuthFilter(jwtUtil, userDetailService), UsernamePasswordAuthenticationFilter.class);
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
        corsConfiguration.setAllowedMethods(Allowed_METHODS);
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

