package com.yedam.app.spring.javabase;

public class Restaurant {

	private Chef chef;
	
	public Restaurant(Chef chef) {
		this.chef = chef;
		System.out.println("생성자 주입");
	}
	
	public Restaurant() {
		
	}
	
	public void setChef(Chef chef) {
		this.chef = chef;
		System.out.println("세터 주입");
	}
	
	public void run() {
		chef.cooking();
	}
}
