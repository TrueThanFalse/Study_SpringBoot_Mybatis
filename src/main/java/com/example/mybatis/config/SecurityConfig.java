package com.example.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.mybatis.security.CustomUserSerivice;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	/*
	 * 시큐리티 버전업으로 인한 변경점 >
	 * 1. 상속 받는 것이 없음
	 * 2. 이니셜라이저 만들 필요 없음
	 */
	
	/*
	 * SpringSecurity5 이후부터 변경사항
	 * 1. 암호화 변환 정책 변경 : passwordEncoder -> createDelegatingPasswordEncoder
	 * => passwordEncoder는 단방향 암호화가 중점이었는데
	 * createDelegatingPasswordEncoder를 사용하면 양방향으로 복호화가 편하다.
	 * 
	 * 2. SecurityFilterChain 객체로 설정하기
	 * 
	 * 3. AuthenticationManager 객체로 설정하기
	 * 
	 * 전부 @Bean으로 생성하면 됨 (오버라이드 없음)
	 */
	
	// 1. createDelegatingPasswordEncoder
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	// 2. SecurityFilterChain 객체로 설정하기
	// SecurityFilterChain 객체는 build()가 내장되어 있다.
	// => stream 방식으로 모든 것을 세팅하고 build() 해주면 생성 완료 완료
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/","/index","/js/**","/dist/**","/board/list",
								"/member/login","/member/register","/comment/**",
								"/upload/**").permitAll()
						.requestMatchers("/member/list").hasAnyRole("ADMIN")
						.anyRequest().authenticated())
				.formLogin(login -> login
						.usernameParameter("email")
						.passwordParameter("pwd")
						.loginPage("/member/login")
						.defaultSuccessUrl("/board/list").permitAll())
						// Fail 설정은 Security5에선 따로하지 않는다.
				.logout(logout -> logout
						.logoutUrl("/member/logout")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.logoutSuccessUrl("/"))
				.build();				
	}
	
	// 3. AuthenticationManager 객체로 설정하기
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// UserDetailsService (옛 Security5 이전에 설정했던 방법과 동일함)
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserSerivice();
	}
	
}
