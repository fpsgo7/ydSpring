package com.yedam.app.spring.xml;

import org.springframework.stereotype.Component;

public class Restaurant {
	private Chef chef;
	
	// 생성자 인젝션
	public Restaurant(Chef chef) {
		this.chef = chef;
		System.out.println("생성자 주입");
	}
	
	// 세터 인젝션에서는 기본 생성자가 필수이다.
	// (여기서는 생성자가 이미 잇어 기본생성자가 만들어지지 않아 따로 작성하였다.)
	public Restaurant() {
		
	}
	
	// 세터 인젝션
	public void setChef(Chef chef) {
		this.chef = chef;
		System.out.println("세터 주입");
	}
	
	public void run() {
		chef.cooking();
	}
}
