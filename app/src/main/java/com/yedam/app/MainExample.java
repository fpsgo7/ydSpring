package com.yedam.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.yedam.app.java.JavaTV;
import com.yedam.app.spring.SpringTV;

public class MainExample {

	public static void main(String[] args) {
		// 전통적인 객체 생성방식
		JavaTV tv = new JavaTV();
		tv.turnOn();
		
		// xml 빈 등록 방식 활용
		ApplicationContext ctxXml 
		// resources의 xml 파일을 찾는다.
			= new GenericXmlApplicationContext("classpath:"
					+ "applicationContext.xml");
		// 찾을때는 Bean으로 등록된 id로 찾는다. (대소문자 구분 주의!)
		SpringTV xmlTv1 = (SpringTV)ctxXml.getBean("tv");
		xmlTv1.turnOn();
		// 또는 아예 클래스정보를 넘겨서 찾아도된다.
		SpringTV xmlTv2 = (SpringTV)ctxXml.getBean(SpringTV.class);
		xmlTv2.turnOn();
		// 싱글톤 방식임을 확인할 수 있다.
		if(xmlTv1 == xmlTv2)
			System.out.println("xmlTv1과 xmlTv2은 같은 빈입니다 (같은 인스턴스)");
		
		
		
	}

}
