package com.aleksandrbogomolov.vote_restaurant.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("com.aleksandrbogomolov.vote_restaurant.configuration")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final CustomSuccessHandler customSuccessHandler;

    @Autowired
    public SpringSecurityConfiguration(@Qualifier("userService") UserDetailsService userDetailsService, CustomSuccessHandler customSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/profile/register", "/login").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/", "/**").authenticated()
                .and().formLogin().loginPage("/login").successHandler(customSuccessHandler).failureUrl("/login?error=true")
                .and().logout().logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/access_denied")
                .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
