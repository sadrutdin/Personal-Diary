package com.zaynukov.dev.angeldiary.conf;

import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationProvider {

    private final LoginService loginService;

    @Inject
    public WebSecurityConfig(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/sign-in", "/sign-up", "/css/**", "/img/**", "/js/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String pass = authentication.getCredentials().toString();
        try {
            if (loginService.match(login, pass)) {
                return new UsernamePasswordAuthenticationToken(login, pass);
            }
        } catch (DiaryIsNotExistsException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}