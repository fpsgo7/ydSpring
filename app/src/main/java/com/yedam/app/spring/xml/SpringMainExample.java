package com.yedam.app.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class SpringMainExample {
	public static void main(String[] args) {
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:"
				+ "applicationContext.xml");
		// Chef는 xml 파일쪽에서 생성자 주입을 작성했기 때문에
		// 여기서 Chef를 안넣어도 작동된다.(스프링이 xml 파일을 읽고 처리해주기 때문)
		Restaurant res = (Restaurant) ctx.getBean(Restaurant.class);
		res.run();
	}
	
}
