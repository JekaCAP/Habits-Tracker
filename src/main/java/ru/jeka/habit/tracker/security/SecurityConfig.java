package ru.jeka.habit.tracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Настройка правил безопасности для URL и страниц
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/signup").permitAll()  // Делаем доступными страницы логина и регистрации
                .requestMatchers("/admin/**").hasRole("ADMIN")    // Только для администраторов
                .requestMatchers("/user/**").hasRole("USER")      // Только для обычных пользователей
                .anyRequest().authenticated()                 // Все остальные страницы требуют авторизации
                .and()
                .formLogin()
                .loginPage("/login")  // Страница логина
                .permitAll()           // Доступность страницы логина
                .defaultSuccessUrl("/user/home", true)  // Редирект после успешного логина
                .and()
                .logout()
                .permitAll()  // Доступность выхода
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Политика создания сессий
                .maximumSessions(1)  // Ограничение на количество сессий
                .expiredUrl("/login?expired=true"); // Страница по истечении сессии

        return http.build();
    }

    // Настройка шифрования паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Создание AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
