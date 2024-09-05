package com.yedam.app.web.config;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

// 모든 컨트롤러에 대하여 대응된다.
@ControllerAdvice
public class WebAdvice {
	// 예외처리
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<String> 
		invokeError(IllegalAccessException e){
		return new ResponseEntity<String>("Error Message"
				,HttpStatus.BAD_REQUEST);
	}

	// 공통 변수선언
	/**
	 * 모델에 contextPath 라는 이름으로
	 * 컨텍스트패스를 올려준다.
	 * 그래서 ${contextPath} 으로 접근가능하다.
	 */
	@ModelAttribute("contextPath")
	public String contextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
}
