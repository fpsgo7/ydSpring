package com.yedam.app.dept.service;

import lombok.Data;

@Data
public class DeptVO {
	private Integer departmentId;// 부서번호, 기본키
	private String departmentName;// 부서명, Not null
	private Integer managerId;// 부서책임자 사원번호 , FK
	private Integer locationId;// 위치 번호, FK
}
