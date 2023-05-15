package com.formula38.appuser.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,
                                                      SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // Authenticates if Username and Password are correct using AuthenticationManager
        try {

            // Object Mapper reads value from input stream and places values in UsernameAndPasswordAuthReq class
            UsernameAndPasswordAuthenticatonRequest authenticatonRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticatonRequest.class);

            // Check if username exist then check if password is correct
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticatonRequest.getUsername(), // username = principle
                    authenticatonRequest.getPassword()  // password = credentials
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // This method creates the JWT Token only after a Successful Authentication Attempt

        // Generate Token
        String token = Jwts.builder()
                .setSubject(authResult.getName()) // grabs authentication result of client users
                .claim("authorities", authResult.getAuthorities()) // users granted permissions
                    .setIssuedAt(new Date()) // date token issued
                    .setExpiration(java.sql.Date.valueOf(
                            LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()))) // Token Expiration
                .signWith(secretKey) // Key encryption algorithm (HS256: HMACSHA256)
                .compact(); // Turns JWT to JWS (web token -> web signature)

        // Send token to client; The "Bearer" token is the JWT token
        // This is the header used in HTTP request client
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }

}
