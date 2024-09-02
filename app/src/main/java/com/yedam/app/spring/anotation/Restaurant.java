package com.yedam.app.spring.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Autowired를 작성한것을 기준으로 주입방법이 결정된다.
// 만약 세터, 생성자 둘다에게 Autowired를 제공하면
// 둘다 실행되니 주의!
@Component // 빈등록
public class Restaurant {

	private Chef chef;
	
	// 생성자 인젝션
	//@Autowired // 스프링 쪽에 사용하도록한다.
	public Restaurant(Chef chef) {
		this.chef = chef;
		System.out.println("생성자 주입");
	}
	
	// 세터 인젝션에서는 기본 생성자가 필수이다.
	// (여기서는 생성자가 이미 잇어 기본생성자가 만들어지지 않아 따로 작성하였다.)
	public Restaurant() {
		
	}
	
	// 세터 인젝션
	@Autowired
	public void setChef(Chef chef) {
		this.chef = chef;
		System.out.println("세터 주입");
	}
	
	public void run() {
		chef.cooking();
	}
}
