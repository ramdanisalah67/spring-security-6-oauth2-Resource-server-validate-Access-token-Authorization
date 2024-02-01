package com.example.springsecuritydemo.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig   {

     //@Autowired
    // private JwtConverter jwtConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(httpRequests ->{

                    httpRequests.anyRequest().authenticated();})
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        //configurer->configurer.jwtAuthenticationConverter(jwtConverter)
                                Customizer.withDefaults()))

                .build();
    }


    @Bean
    public DefaultMethodSecurityExpressionHandler msecurity(){
        DefaultMethodSecurityExpressionHandler df = new DefaultMethodSecurityExpressionHandler();
        df.setDefaultRolePrefix("");
        return df ;
    }

    @Bean
    public JwtAuthenticationConverter myConverter(){
    JwtAuthenticationConverter jwc = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwg = new JwtGrantedAuthoritiesConverter();
        jwg.setAuthorityPrefix("");
        jwg.setAuthoritiesClaimName("roles");//by default is "scope" or "scp"
            jwc.setJwtGrantedAuthoritiesConverter(jwg);
            return jwc ;
    }
}

