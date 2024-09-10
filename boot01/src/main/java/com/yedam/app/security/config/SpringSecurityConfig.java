package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity// 시큐리티 관련 
public class SpringSecurityConfig {
	
	@Bean// 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean // 메모리상 인증정보 등록 => 테스트 전용
//	InMemoryUserDetailsManager inMemoryUserDetailsService() {
//		// UserDetails 시큐리티 의 VO같은 존재
//		// user1 회원 추가
//		UserDetails user = User.builder()// builder 패턴 사용
//				.username("user1")
//				// 암호화된 비밀번호값을 대입한다.
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER")// ROLE_USER <-- 강제로 ROLE_ 가 붙여진다.
//				//.authorities("ROLE_USER") // ROLE_가 강제로 붙여지지 않는다는 차이가 있다.
//				.build();
//		//  admin 회원 추가
//		UserDetails admin = User.builder()// builder 패턴 사용
//				.username("admin1")
//				// 암호화된 비밀번호값을 대입한다.
//				.password(passwordEncoder().encode("1234"))
//				.roles("ADMIN","USER")// ROLE_USER <-- 강제로 ROLE_ 가 붙여진다.
//				.build();
//		// 매개값을 여러개 주어 여러 회원을 만들수 있다.
//		return new InMemoryUserDetailsManager(user,admin);
//	}
	
	// 스프링 설정 필터 체인
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 적용될 Security 설정
		//  => URI에 적용될 권한
		http
			.authorizeHttpRequests(authorize
				-> authorize
				.requestMatchers("/","/all").permitAll() // permitAll() 모든 권한 허가
				// 권한이 없다면 403 오류가 발생한다.
				//.requestMatchers("/user/**").hasAnyRole("USER","ADMIN")// 여러 권한조건을 넣고 싶을때 사용
				.requestMatchers("/user/**").hasRole("USER")// ROLE_ 은 앞에 알아서 붙여준다.
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")// 규칙명을 풀네임으로 작성해야한다.
				.anyRequest().authenticated() // 인증 된 유저에게 나머지에 대하여 허가
		);
		
		// 폼 로그인 관련 설정
		http
			.formLogin(formlogin ->  formlogin.defaultSuccessUrl("/all"));
		
		// 로그아웃 설정
		http
			.logout(logout -> logout
					.logoutSuccessUrl("/all")
					.invalidateHttpSession(true) 
		);
		// csrf 비활성화 (개발 동안만 사용)
		http
			.csrf(csrf-> csrf.disable());
		return http.build();
	}
	
}
