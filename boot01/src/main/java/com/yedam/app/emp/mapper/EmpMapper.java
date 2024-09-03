package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// 전체 조회
	public List<EmpVO> selectEmpAllList();
	// 단건 조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	// 수정 
	// @Param은 xml 내부에서 사용할 이름을 결정한다.
	public int updateEmpInfo(@Param("eid") int empId,@Param("emp") EmpVO empVO);
	// 삭제
	public int deleteEmpInfo(int empId);
}
