package com.ing.store.config;

import static com.ing.store.utils.Constants.ADMIN;
import static com.ing.store.utils.Constants.PRODUCTS_CONTROLLER_PATH;
import static com.ing.store.utils.Constants.ROLE;
import static org.springframework.security.config.Customizer.withDefaults;

import com.ing.store.config.UserAuthenticationProvider;
import com.ing.store.config.jwt.JwtAuthFilter;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  private final UserAuthenticationProvider userAuthenticationProvider;
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
    configuration.setAllowedMethods(Arrays.asList(
        HttpMethod.GET.name(),
        HttpMethod.POST.name(),
        HttpMethod.PUT.name(),
        HttpMethod.DELETE.name()));
    configuration.setAllowedHeaders(Arrays.asList(
        HttpHeaders.AUTHORIZATION,
        HttpHeaders.CONTENT_TYPE));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(corsConfiguration -> corsConfiguration.configurationSource(corsConfigurationSource()))
        .exceptionHandling(customizer -> customizer.authenticationEntryPoint(
            new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
        .authorizeHttpRequests(req -> req
            //.requestMatchers(HttpMethod.GET, PRODUCTS_CONTROLLER_PATH).permitAll()
            //.requestMatchers(HttpMethod.POST, PRODUCTS_CONTROLLER_PATH).hasAuthority(ROLE + ADMIN)
            //.requestMatchers(HttpMethod.DELETE, PRODUCTS_CONTROLLER_PATH).hasAuthority(ROLE + ADMIN)
            //.requestMatchers(HttpMethod.PUT, PRODUCTS_CONTROLLER_PATH).hasAnyRole()
            //.anyRequest().authenticated()
            .anyRequest().permitAll()
        )
        .csrf(AbstractHttpConfigurer::disable)

        .httpBasic(withDefaults());

    return http.build();
  }

}
