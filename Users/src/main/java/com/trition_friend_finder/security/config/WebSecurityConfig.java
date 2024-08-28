package com.trition_friend_finder.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.trition_friend_finder.services.UserService;
import org.springframework.security.config.Customizer;

import lombok.AllArgsConstructor;

import java.util.List;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Configures the security filter chain for the application, defining the authorization rules for incoming HTTP requests.
     * 
     * Security filter chain is a component of the Spring Security framework, responsible for intercepting and processing incoming requests to ensure they meet the defined security constraints.
     * 
     * This method configures the filter chain to permit all requests to the registration endpoint (/api/v*\/registration\/**) without authentication, while requiring authentication for all other requests.
     * 
     * Usage:
     * This method is typically used in conjunction with other security configurations to establish a comprehensive security posture for the application.
     * 
     * @param http the HttpSecurity object to configure, providing a fluent API for defining security constraints
     * @return the configured SecurityFilterChain, which can be further customized or extended as needed
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.configurationSource((corsConfigurationSource())))
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(auth-> auth.requestMatchers("/api/v1/registration","/error").permitAll()
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .build();
    }

    /**
     * Configure CORS settings for the application, 
     * defining allowed headers, origins, methods, and exposed headers. Needed
     * to enable our localhost:3000 to make requests to a different origin than the one the web page 
     * was loaded from (localhost:8080 backend).
     * 
     * Settings that are configured:
     * - Allowed headers: specifies the headers that are allowed to be sent in requests 
     *   from the client to the server.
     * - Allowed origins: specifies the domains that are allowed to make requests to the 
     *   server. In this case, only requests from http://localhost:3000 are allowed.
     * - Allowed methods: specifies the HTTP methods that are allowed to be used in 
     *   requests from the client to the server.
     * - Allow credentials: specifies whether the server allows the client to send 
     *   credentials (such as cookies or authorization headers) with requests.
     * - Exposed headers: specifies the headers that the server allows the client to 
     *   access in responses.
     * 
     * The configured CORS settings are then applied to all endpoints in the application.
     * 
     * @return the configured CorsConfigurationSource, which can be used to enable CORS 
     *         support in the application
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));  // Allow only your frontend origin

        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);  // Apply CORS settings to all endpoints
        return source;
    }

    /**
     * Returns an instance of the AuthenticationManager bean, 
     * which is used to authenticate users in the application.
     *
     * @param  authenticationConfiguration  the configuration object used to create the AuthenticationManager instance
     * @return          the AuthenticationManager instance
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Returns an instance of the DaoAuthenticationProvider bean, 
     * which is used to authenticate users in the application.
     *
     * @return          the DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
