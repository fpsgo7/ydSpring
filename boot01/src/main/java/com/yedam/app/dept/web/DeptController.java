package com.yedam.app.dept.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	private DeptService deptService;
	
	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	// 전체 조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		model.addAttribute("depts",list); // 페이지에 전달
		return "dept/list";
	}
	// 단건 조회
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO
						,Model model) {
		DeptVO dept = deptService.deptInfo(deptVO);
		model.addAttribute("dept",dept); // 페이지에 전달
		return "dept/info";
	}
	// 등록 - 페이지
	
	// 등록 - 처리
	
	// 수정 - 페이지
	
	// 수정 - 처리
	
	// 삭제 - 처리
}
