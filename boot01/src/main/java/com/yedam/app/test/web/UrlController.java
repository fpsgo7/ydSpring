package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Bean 등록, Web 과 관련된부분 담당
public class UrlController {
	
	// http://localhost:8099/test 으로 찾을 수 있다.
	// 매핑 어노테이션에 마우스 올리면 해당 경로를 보여준다.(테스트할때는 여길 이용하자.)
	// @RequestMapping(path="/test", method=RequestMethod.GET) 
	@GetMapping("/test")
	@ResponseBody // => AJAX 용
	//http://127.0.0.1:8099/test?keyword=yedam // 파라미터 변수 keyword는 경로의 파라미터 변수를 받을 수 있다.
	public String urlGetTest(String keyword) {
		return "Server Response : Get Method\n Select - " 
				+ keyword ;
	}
	
	// @RequestMapping(path="/test", method=RequestMethod.POST) 
	@PostMapping("/test")
	@ResponseBody 
	public String urlPostTest(String keyword) {
		return "Server Response : Get Method\n Select - " 
				+ keyword ;
	}
}
