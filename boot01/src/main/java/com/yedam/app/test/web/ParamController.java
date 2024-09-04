package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	
//	 Content-type : application/x-www-form-urlencoded 일경우
//	 QueryString(질의 문자열) : key=value&key=value 형태
//	 Method 상관없다.
	// QueryString => 커맨드 객체 (어노테이션 X ,객체 )
	@RequestMapping(path="comobj",
			method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String comandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	@RequestMapping(path="reqparm",
			method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String requestParam(
			@RequestParam Integer employeeId, // 필수 만약 값이 없다면 400오류 발생
			String lastName, // 생략 가능
			// name  : 매개변수가아닌 여기서 작성한값을 
			// 키 값으로 사용할 것이다. msg를 키값으로 하면 적용이 안된다.
			@RequestParam (name= "message",
					defaultValue = "기본값",
					required = true)String msg
			) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t msg : " + msg;
		return result;
	}
	
	// Content-type : application/json
	// JSON 포맷 : @RequestBody , | 배열 or 객체
	// Method : POST, PUT 
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	// 보낸 json 값
	// {
	//    "employeeId" : "100",
	//    "lastName" : "hong"
	// }
	
	// 배열, 리스트일경우 
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBody(@RequestBody List<EmpVO> list) {
		String result = "";
		for(EmpVO empVO : list) {
			result += "Path : /comobj \n";
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		return result;
	}
	// 보낸 json 값
//	[
//	    {
//	        "employeeId" : "100",
//	        "lastName" : "hong"
//	    },
//	    {
//	        "employeeId" : "200",
//	        "lastName" : "jong"
//	    },
//	    {
//	        "employeeId" : "300",
//	        "lastName" : "kong"
//	    }
//	]
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일값
	// Method는 상관없음
	// Content-type도 상관없음
	@RequestMapping("path/{id}")
	@ResponseBody
	// @PathVariable 에는 기본값이 불가능하다.
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
}
