package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest// junit 환경에서도 컨테이너를 사용할 수 있게한다.
class Boot01ApplicationTests {

	@Autowired // 필드 주입 (접근 지시자를 무시한다. (보안상 위험))
	private EmpMapper empMapper;
	
	
	//@Test
	void contextLoads() {
		assertNotNull(empMapper);
	}

	//@Test
	//@DisplayName("리스트가 존재하는지 확인")
	void empList() {
		// 전체조회 : 입력X -> 출력 , 1건이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}
	
	//@Test
	//@DisplayName("emp 단건조회 테스트")
	void emp() {
		// 전체조회 : 입력, 사원번호 (100) -> 출력 , 사원이름(king)
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(),"King");
	}
	
	//@Test
	void empInsert() {
		// 사원 등록 : 입력, 사원 이름, 이메일, 업무
		// -> 출력 , 1
		
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("KdHong@gmail.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	//@Test
	//@DisplayName("insert에서 내용 더체우기")
	void empInsertFull() throws ParseException {
		// 사원 등록 : 입력, 사원 이름, 이메일, 입사날짜 ,업무, 급여 
		// -> 출력 , 1
		
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Han");
		empVO.setEmail("jhHan@gmail.com");
		// 날짜 포맷 지정후 날짜값을 넣어준다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date today = sdf.parse("2024-08-15");
		empVO.setHireDate(today);
		empVO.setJobId("SA_REP");
		empVO.setSalary(15000.0);
		
		int result = empMapper.insertEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	//@Test
	void empDelete() {
		int result = empMapper.deleteEmpInfo(209);
		assertEquals(result, 1);
		result = empMapper.deleteEmpInfo(208);
		assertEquals(result, 1);
	}
	
	//@Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmail("kjHong@naver.com");
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(result, 1);
	}
	
	//@Test
	void empUpdateDynamic() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		empVO.setLastName("kim");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		assertEquals(result, 1);
	}
}
