package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // => AOP이 적용가능한 Bean
public class EmpServiceImpl implements EmpService{
	private EmpMapper empMapper;
	
	@Autowired // 생성자가 한개이면 사실 생략해도된다.
	public EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		// 등록이 성공했다면 등록한 대상의 기본키를 반환할 것이다.
		return result == 1 ? empVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = empMapper.updateEmpInfo(empVO.getDepartmentId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);
		// json 형태로 보면
//		{
//			"result" : "true"
//			"target" : {
//						"employeeId" : "100",
//						"lastName" : "king"
//						}
//		}
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안될 경우 : {}
		// 삭제가 될 경우 : {"employeeId" : 104}
		int result = empMapper.deleteEmpInfo(empId);
		
		// 삭제가 성공할경우 삭제한 대상의 아이디값을 반환한다.
		if(result == 1) {
			map.put("employeedId", empId);
		}
		return map;
	}

}
