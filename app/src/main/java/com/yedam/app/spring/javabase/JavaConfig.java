package com.yedam.app.spring.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 여기서 빈등록과 의존성 주입을 전부 할 것이다.
 */
@Configuration // 스프링에서 설정을 등록하기 위한 파일
public class JavaConfig {
	
	@Bean // 해당 메서드에서 반환한 대상을 빈으로 등록할 것이다.
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		// 생성자 주입 방식
		// return new Restaurant(chef);
		// sett 주입방식
		Restaurant res = new Restaurant();
		res.setChef(chef);
		return res;
	}
}
