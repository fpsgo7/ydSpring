package com.yedam.app.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.user.service.UserVO;

@CrossOrigin// 일시적으로 Cors를 비활성화
@Controller
public class UserController {
	
	@GetMapping("getAjax")
	@ResponseBody // AJAX 처리용 => 리턴 결과가 순수 데이터
	// 파라미터변수로 어노테이션없이 객체 : 커멘트 객체
	// QueryString을 처리해준다.
	public Map<String, Object> getAjax(UserVO userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("type", "getAjax");
		map.put("data", userVO);
		return map;
	}
	
	@PostMapping("postAjax")
	@ResponseBody 
	public Map<String, Object> postAjax(UserVO userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("type", "postAjax");
		map.put("data", userVO);
		return map;
	}
	
	@PostMapping("jsonAjax")
	@ResponseBody 
	// JSON : @RequestBody + 객체 , 배열
	public Map<String, Object> jsonAjax(@RequestBody UserVO userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("type", "jsonAjax");
		map.put("data", userVO);
		return map;
	}
	
	// json 배열 처리
	@PostMapping("jsonListAjax")
	@ResponseBody 
	// JSON : @RequestBody + 객체 , 배열
	public Map<String, Object> jsonListAjax(@RequestBody List<UserVO> userVO){
		Map<String, Object> map = new HashMap<>();
		map.put("type", "jsonListAjax");
		map.put("data", userVO);
		return map;
	}
}
