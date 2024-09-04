package com.yedam.app.emp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ThymeleafController {

	@GetMapping("thymeleaf")
	public String thymeleafTest(EmpVO empVO ,  Model model) {
		model.addAttribute("emp", empVO);
		
		// 실제로는 prefix 와 surfix가 설정되있어
		// classpath:/templates/test/main.html 로 인식된다.
		return "test/main";

	}
}
