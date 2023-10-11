package com.netmind.oauth2resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity
public class ResourceServerConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                // .requestMatchers(antMatcher("/docs/**")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/products/**")).hasAnyAuthority("SCOPE_products.read") //admin puede hacer de todo
                                // .requestMatchers(antMatcher(HttpMethod.GET, "/products/**")).hasAnyAuthority(ERole.ADMIN.name(), ERole.USER.name()) //Para acceder a productos debe ser USER
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oAuth2 -> oAuth2
                        .jwt(it -> it.decoder(JwtDecoders.fromIssuerLocation(issuer))));
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
        // jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("role");
       // jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("sub"); //role comes in sub examine jwt token
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}