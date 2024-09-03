package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	
	@Test
	void contextLoads() {
		assertNotNull(empMapper);
	}

	@Test
	@DisplayName("리스트가 존재하는지 확인")
	void empList() {
		// 전체조회 : 입력X -> 출력 , 1건이상
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty());
	}
}
