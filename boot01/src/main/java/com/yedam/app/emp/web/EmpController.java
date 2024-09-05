package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller // Route : 사용자의 요청(endpoint) 와 그에대한 처리
// UIR + METHOD => Service => View
@RequiredArgsConstructor // final 필드에 대하여 생성자를 만들어준다.
public class EmpController {
	
	// @RequiredArgsConstructor 로 현제 생성자는 하나뿐이라.
	// 자동으로 의존주입이 된다.
	private final EmpService empService;
	
	// GET : 조회 , 빈페이지
	// POST : 데이터 조작(등록, 수정, 삭제)
	// 전체 조회
	@GetMapping("empList")
	public String empList(Model model) {
		// Model = Request + Response
		// 1)  기능 수행 => Service
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list);
		//3) 데이터를 출력할 페이지 결정
		return "emp/list";
		// prefix + return + suffix => 실제 경로 / ViewResolver가 완성시켜줌
		// classpath:/templates/emp/list.html 라는 경로가 완성된다.
	}
	
	// Get 방식은 QueryString 만 다룰 수 있다.
	// 단건 조회 GET => QueryString(커맨드 객체 or @RequestParam)
	// 서비스가 필요로 하는 대상을 기준으로 (커맨드 객체 , @RequestParam)
	// 중하나를 선택하자
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO , Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp",findVO); // == HttpServletRequest.setAttribute(); 와 같다.
		return "emp/info";
	}
	
	// 등록 - 페이지
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : Post => form 태그를 통한 submit(페이지) 
	//				=> QueryString(커맨드 객체)
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			// 정상적으로 등록된경우
			// redirect는 GetMapping 만 가능하다!
			url = "redirect:empInfo?employeeId="+eid; // redirect는 경로를 요청하는 것이다.
		}else {
			// 실패한경우 (돌확률은 거의없다.)
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET , 조건이 필요 <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO , Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp",findVO); 
		return "emp/update";
	}
	
	// 수정 - 처리 : Post AJAX => QueryString
	//@PostMapping("empUpdate")
	@ResponseBody // AJAX 
	public Map<String, Object> 
		empUpdateAJAXQueryString(EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	// 수정 - 처리 : AJAX => JSON(@RequestBody)
	@PostMapping("empUpdate")
	@ResponseBody // AJAX 
	public Map<String, Object> 
		empUpdateAJAXJSON(@RequestBody EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	// 삭제 = 처리 : Get => QueryString(@RequestParam)
	@GetMapping("empDelete")
	public String empDelete(@RequestParam Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	}
	
}
