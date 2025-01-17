package com.salesconnect.backend.config;

import com.salesconnect.backend.config.jwt.JwtTokenFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtTokenFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable()) // Active si tu as besoin du CORS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getLocalizedMessage()))
                )
                .authorizeHttpRequests(authorize -> authorize
                        // 🔓 Endpoints publics
                        .requestMatchers("/api/auth/**").permitAll()  // Authentification
                        .requestMatchers("/public/**").permitAll()    // Pages publiques

                        // 🔒 Accès réservé à l'Admin Global
                        .requestMatchers("/api/admin/**","/api/companies/all","/api/companies/get/{id}").hasAuthority("ADMIN_GLOBAL")
                        .requestMatchers("/api/users/all").hasAuthority("ADMIN_GLOBAL")


                        // 🔒 Accès réservé à l'Admin d’Entreprise.requestMatchers("/api/companies/my-company").hasAuthority("ADMIN_COMPANY")
                        .requestMatchers("/api/companies/create","/api/companies/company-infos","/api/companies/update/{id}","/api/companies/delete/{id}").hasAuthority("ADMIN_COMPANY")
                        .requestMatchers("/api/users/getByCompany","/api/users/create","/api/users/get/{id}","/api/users/update/{id}","/api/users/delete/{id}").hasAuthority("ADMIN_COMPANY")
                        .requestMatchers("/api/contacts/**").hasAuthority("ADMIN_COMPANY")
                        .requestMatchers(HttpMethod.GET,"/api/opportunities/**").hasAnyAuthority("ADMIN_COMPANY","USER")


                        // 🔒 Accès réservé aux Utilisateurs
                        .requestMatchers("/api/users/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/opportunities/**").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/opportunities/**").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/opportunities/**").hasAuthority("USER")


                        // 🔐 Tout autre accès nécessite l'authentification
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
