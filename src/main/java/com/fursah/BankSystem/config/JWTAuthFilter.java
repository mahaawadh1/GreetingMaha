package com.fursah.BankSystem.config;

import com.fursah.BankSystem.service.auth.CustomUserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class JWTAuthFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final JWTUtil jwtUtil;

    private final CustomUserDetailService userDetailsService;

    public JWTAuthFilter(JWTUtil jwtUtil, CustomUserDetailService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (!request.getServletPath().equals("/api/v1/login") && authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                String usernmae = jwtUtil.getUsernameFromToken(token);
                if (usernmae == null) {
                    throw new UsernameNotFoundException("user not found");
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(usernmae);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}




