package io.eddie.jwt.config;

import io.eddie.jwt.config.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2SuccessHandler oauth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http

                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

                .formLogin(form -> form.disable())
                .oauth2Login(
                        oauth2 -> oauth2.successHandler(oauth2SuccessHandler)
                )

                .sessionManagement( config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(CorsUtils::isPreFlightRequest)
                            .permitAll()
                        .requestMatchers("/admin/**")
                            .hasAuthority("ADMIN")
                        .requestMatchers("/user/**")
                            .hasAuthority("USER")
                        .anyRequest()
                            .authenticated()
                )

                .build();

    }

}
