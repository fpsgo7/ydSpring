package com.yedam.app.spring.xml;

public class JavaMainExample {

	public static void main(String[] args) {
		Chef cf = new Chef();
		Restaurant res = new Restaurant(cf);
		res.run();

	}

}
