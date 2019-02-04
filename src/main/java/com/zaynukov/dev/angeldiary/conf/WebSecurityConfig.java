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
import java.util.Set;

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
        auth.authenticationProvider(this).eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                                                       // Авторизовать соединения
                .antMatchers("/", "/login", "/create-diary", "/css/**", "/img/**", "/js/**")                // Для указанных страниц
                .permitAll()                                                               // Доступ всем
                .anyRequest()                                                              // Любые соединения
                .authenticated()                                                           // Авторизовать
                .and()                                                                     // и
                .formLogin()                                                               // Форма входа
                .loginPage("/login")                                                       // На этом контексте
                .usernameParameter("login")                                                // Имя параметра имени пользователя
                .passwordParameter("password")                                             // Имя параметра пароля
                .successForwardUrl("/main")                                                // При успешной авторизации переслать
                .permitAll()                                                               // Доступ всем
                .and()                                                                     // и
                .logout()                                                                  // страница выхода
                .deleteCookies("JSESSIONID")                                               // Удалить все указанные куки
                .permitAll()                                                               // Доступ всем
                .and()                                                                     // и
                .authenticationProvider(this)                                              // Сервис аутентификации (проверки входа)
                .authorizeRequests()                                                       // Авторизовать соединения ...
                .antMatchers("/main", "/create-note", "/edit-note")                          // Для страниц, начинающихся с этой строки
                .hasRole("ACTIVE")                                                         // Для указанных ролей
                .anyRequest()                                                              // все соединения
                .authenticated()                                                           // Аутентифицировать
                .and()                                                                     // и
                .csrf()                                                                    // выключить csrf (смотри в Википедию)
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
                logger.info("Корррааамммбаааааа");
                return new UsernamePasswordAuthenticationToken(
                        login,
                        pass,
                        roles
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