package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService{
	private DeptMapper deptMapper;
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}

	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		// 부서번호를 반환하기위한 작업
		int result = deptMapper.insertDeptInfo(deptVO);
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object > map = new HashMap<>();
		boolean isSuccessed = false;
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(),deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(int empId) {
		Map<String, Object> map = new HashMap<>();

		int result = deptMapper.deleteDeptInfo(empId);
		
		// 삭제가 성공할경우 삭제한 대상의 아이디값을 반환한다.
		if(result == 1) {
			map.put("departmentId", empId);
		}
		return map;
	}

}
