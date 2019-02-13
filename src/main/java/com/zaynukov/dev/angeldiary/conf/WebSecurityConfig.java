package com.zaynukov.dev.angeldiary.conf;

import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Set;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LoginService loginService;

    @Inject
    public WebSecurityConfig(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this).eraseCredentials(false);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/create-diary", "/css/**", "/img/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .successForwardUrl("/main")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .authenticationProvider(this)
                .authorizeRequests()
                .antMatchers("/main", "/delete-note", "/view", "/create-note", "/edit-note")
                .hasRole("ACTIVE")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();

        http.httpBasic();

    }

    private final Set<SimpleGrantedAuthority> roles = Collections.singleton(new SimpleGrantedAuthority("ACTIVE"));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String pass = authentication.getCredentials().toString();
        try {
            if (loginService.match(login, pass)) {
                return new UsernamePasswordAuthenticationToken(
                        login,
                        pass,
                        roles
                );
            }
        } catch (DiaryIsNotExistsException e) {
            logger.error("Дневника не существует", e);
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UsernamePasswordAuthenticationToken.class);
    }
}