package com.zaynukov.dev.angeldiary.conf;

import com.zaynukov.dev.angeldiary.exception.DiaryIsNotExistsException;
import com.zaynukov.dev.angeldiary.service.diary.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());
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
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .successForwardUrl("/note-list")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .authenticationProvider(this)
                .authorizeRequests()
                .antMatchers("/note**")
                .hasRole("ACTIVE")
                .anyRequest()
                .authenticated();

    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String pass = authentication.getCredentials().toString();
        try {
            if (loginService.match(login, pass)) {
                logger.info("Корррааамммбаааааа");
                return new UsernamePasswordAuthenticationToken(
                        login,
                        pass,
                        Collections.singleton(new SimpleGrantedAuthority("ACTIVE"))
                );
            }
        } catch (DiaryIsNotExistsException e) {
            logger.error("Дневника не существует:", e);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}