package com.project38.appuser.security;


import com.project38.appuser.auth.ApplicationUserService;
import com.project38.appuser.jwt.JwtConfig;
import com.project38.appuser.jwt.JwtTokenVerifier;
import com.project38.appuser.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final HttpSecurity http;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserService applicationUserService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig, HttpSecurity http) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.http = http;
    }

    @Bean
    public SecurityFilterChain filterChain() throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.DELETE)
                .hasRole("ADMIN")
                    .requestMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                    .requestMatchers("/user/**")
                .hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/login/**")
                .anonymous()
                    .anyRequest()
                    .authenticated()
                .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                        .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                    .httpBasic()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(applicationUserService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager()
    throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(applicationUserService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
