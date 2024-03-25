package br.com.fiap.pontoeletronicoconsultaregistro.config;

import br.com.fiap.pontoeletronicoconsultaregistro.core.application.AutenticacaoSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AutenticacaoSuccessHandler autenticacaoSuccessHandler;

    public SecurityConfiguration(AutenticacaoSuccessHandler autenticacaoSuccessHandler) {
        this.autenticacaoSuccessHandler = autenticacaoSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request.requestMatchers("/actuator/health")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2Login(oauth -> oauth.redirectionEndpoint(endPoint -> endPoint.baseUri("/login/oauth2/code/cognito"))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userAuthoritiesMapper(userAuthoritiesMapper()))
                        .successHandler(autenticacaoSuccessHandler))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            try {
                OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) new ArrayList<>(authorities).get(0);
                mappedAuthorities = ((ArrayList<?>) oidcUserAuthority.getAttributes().get("cognito:groups")).stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
            } catch (Exception exception) {
                System.out.println("Not Authorized!");
                System.out.println(exception.getMessage());
            }
            return mappedAuthorities;
        };
    }

}