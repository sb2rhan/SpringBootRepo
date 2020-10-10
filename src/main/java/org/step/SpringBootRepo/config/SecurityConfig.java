package org.step.SpringBootRepo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/profiles", "/api/profiles/*")
                .permitAll()
                .antMatchers("/api/users", "/api/users/*")
                .permitAll()
                .antMatchers("/api/messages", "/api/messages/*")
                .permitAll()
                .antMatchers("/api/profiles/private/**")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/users/private/**")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .httpBasic()
                .and()
                .cors(httpSecurityCorsConfigurer -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();

                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));

                    corsConfiguration.setAllowCredentials(true);
                })
                .csrf().disable();
    }
}
