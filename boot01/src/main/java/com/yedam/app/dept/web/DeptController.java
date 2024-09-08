package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("deptInsert")
	public String deptInsertForm(){
		return "dept/insert";
	}
	// 등록 - 처리
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int id = deptService.deptInsert(deptVO);
		System.out.println(id);
		String url = null;
		if(id > -1) {
			url = "redirect:deptInfo?departmentId=" + id;
		}else {
			url = "redirect:deptList";
		}
		return url;
	}
	// 수정 - 페이지
	@GetMapping("deptUpdate")
	public String empUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/update";
	}
	// 수정 - 처리
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object>
		empUpdateAJAXQueryString(DeptVO deptVO){
		System.out.println(deptVO);
		return deptService.deptUpdate(deptVO);
	}
	// 삭제 - 처리
}
