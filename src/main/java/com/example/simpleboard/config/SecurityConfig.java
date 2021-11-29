package com.example.simpleboard.config;

import com.example.simpleboard.enums.Role;
import com.example.simpleboard.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // to use h2-console
                .csrf().disable()
                // URL setting
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/test/articles",
                        "/test/init",
                        "/css/**",
                        "/images/**",
                        "/js/**"
                ).permitAll() // 누구나 접근 허용
                // 아래의 패턴은
                .antMatchers("/test/api/**")
                .hasRole(Role.USER.name()) // USER일 경우만 사용 가능
                .anyRequest().authenticated() //나머지 URL 요청은 무조건 인증 필요
                .and()
                .logout()
                .logoutSuccessUrl("/test/articles") // 로그아웃 후, 리다이렉트 될 URL
                .and()
                .oauth2Login() // OAuth2 login setting
                .defaultSuccessUrl("/test/articles")
                .userInfoEndpoint() // 로그인 성공 시, 사용자 정보 관련 설정
                .userService(customOAuth2UserService); // 로그인 성공 후 사용자를 다룰 수 있는 서비스 등록

    }
}
