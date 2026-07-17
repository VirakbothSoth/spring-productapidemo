package co.istad.productapidemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Value("${keycloak.client-id}")
    private String clientId;
    @Bean
    public SecurityFilterChain config(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)

                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))

                .httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/api/v1/admin","/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/auth/register", "/api/v1/test/forgot-password/**").permitAll()
                                .requestMatchers("/scalar/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/v1/files/**","/files/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/products/**","/api/v1/tags/**").permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> converter = jwt -> {
            Map<String,Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess==null) {return Collections.emptySet();}
            var clientAccess = (Map<String,Object>) resourceAccess.get(clientId);
            if(clientAccess==null) {return Collections.emptySet();}
            Object rolesObj = clientAccess.get("roles");

            if (!(rolesObj instanceof Collection<?> roles)) { return Collections.emptySet(); }

            return roles.stream().map(Object::toString).map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                    .collect(Collectors.toSet());
        };
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return jwtAuthenticationConverter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails dev = User.withUsername("developer")
                .password(passwordEncoder().encode("developer"))
                .roles("USER").build();

        return new InMemoryUserDetailsManager(dev);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
