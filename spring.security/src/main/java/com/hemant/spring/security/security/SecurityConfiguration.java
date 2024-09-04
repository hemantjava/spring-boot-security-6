package com.hemant.spring.security.security;


import com.hemant.spring.security.service.UserInfoDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home/user").permitAll()
                        .requestMatchers("/home/users").permitAll()
                        /*
                        .requestMatchers("home/admin").hasAnyRole("ADMIN")
                        .requestMatchers("home/normal").hasAnyRole("NORMAL","ADMIN")
                        .requestMatchers("/home/public").permitAll() //direct access*/
                        .anyRequest().authenticated()
                );
        http.formLogin(Customizer.withDefaults());//all access after login

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
     /*   UserDetails normal = User.builder()
                .username("normal")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("NORMAL")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        UserDetails publics = User.builder()
                .username("publics")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("PUBLIC")
                .build();
        return new InMemoryUserDetailsManager(normal, admin, publics);*/
        return new UserInfoDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();
        authentication.setUserDetailsService(userDetailsService());
        authentication.setPasswordEncoder(bCryptPasswordEncoder());
        return authentication;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
