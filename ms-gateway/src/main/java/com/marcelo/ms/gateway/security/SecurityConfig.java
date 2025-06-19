package com.marcelo.ms.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

//@Configuration
//public class SecurityConfig {
//	
//	@Bean
//	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
//		return http.authorizeExchange(authz -> {
//			authz.pathMatchers("/authorized","logout").permitAll()
//			.pathMatchers(HttpMethod.GET, "/api/items", "/api/products", "/api/users").permitAll()
//			.pathMatchers(HttpMethod.GET, "/api/items/{id}", "/api/products/{id}","/api/users/{id}")
//			.hasAnyRole("ADMIN","USER") // url protegidas
////			.hasAnyAuthority("SCOPE_write","SCOPE_read")
//			.pathMatchers("/api/items/**", "/api/products/**","/api/users/**")
//			.hasRole("ADMIN") // url protegidas
////			.hasAnyAuthority("SCOPE_write")
//			.anyExchange().authenticated();
//		}).cors(csrf -> csrf.disable())
////				.securityContextRepository(NoOpServerSecurityContextRepository.getInstance()) // para que no genere el code en cada request
//				.oauth2Login(withDefaults())
//				.oauth2Client(withDefaults())
//				.oauth2ResourceServer( oauth2 -> oauth2.jwt(
////						jwt ->
////						withDefaults()
//				  jwt -> jwt.jwtAuthenticationConverter(new Converter<Jwt, Mono<AbstractAuthenticationToken>>() {
//
//                      @Override
//                      public Mono<AbstractAuthenticationToken> convert(Jwt source) {
//                          Collection<String> roles = source.getClaimAsStringList("roles");
//                          Collection<GrantedAuthority> authorities = roles.stream()
//                          .map(SimpleGrantedAuthority::new)
//                                  .collect(Collectors.toList());
//
//                          return Mono.just(new JwtAuthenticationToken(source, authorities));
//                      }
//
//                      
//                      
//                  })
//						))
//				.build();
//	}
//	
//}

//La clase SecurityConfig para Spring MVC (Servlet)
//
//
//
@Configuration
public class SecurityConfig {
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> {
            authz
                    .requestMatchers("/authorized", "/logout").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/products", "/api/items", "/api/users").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/products/{id}", "/api/items/{id}", "/api/users/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/products/**", "/api/items/**", "/api/users/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
        })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .oauth2Login(login -> login.loginPage("/oauth2/authorization/client-app"))
                .oauth2Client(withDefaults())
                .oauth2ResourceServer(
//                		withDefaults() // lo siguiente de abajo es para que funcione los roles personalizados y no este limitado a los scopes por defecto
                		oauth2 -> oauth2.jwt(
                                jwt -> jwt.jwtAuthenticationConverter(new Converter<Jwt, AbstractAuthenticationToken>() {

                                    @Override
                                    public AbstractAuthenticationToken convert(Jwt source) {
                                        Collection<String> roles = source.getClaimAsStringList("roles");
                                        Collection<GrantedAuthority> authorities = roles.stream()
                                        .map(SimpleGrantedAuthority::new)
                                                .collect(Collectors.toList());

                                        return new JwtAuthenticationToken(source, authorities);
                                    }
                                }))
                		)
                .build();
    }
 
}
