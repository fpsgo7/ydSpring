package com.yedam.app.spring.anotation;

import org.springframework.stereotype.Component;

@Component // 빈등록
public class Chef {
	public void cooking() {
		System.out.println("Spring anotation 방식");
	};
}
