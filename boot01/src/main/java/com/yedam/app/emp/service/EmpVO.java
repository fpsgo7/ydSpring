package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@ToString
public class EmpVO {
	// Integer와 Double을 사용하여 null 값을 처리할 수 있게한다.
	// 각 필드에 무엇을 의미하는지 적어준다.
	private Integer employeeId;// 사원번호 기본키
	private String firstName;// 이름
	private String lastName;// 성 not null
	private String email;// 이메일 not null
	private String phoneNumber;// 연락처
	@DateTimeFormat(pattern = "yyyy-mm-dd")// 쿼리 파라미터용
	private Date hireDate;// 입사일자 : not null
	private String jobId;// 업무 : not null, 외래키
	private Double salary;// 급여 : > 0
	private Double commissionPct;// 상여금 : 급여율 기준으로
	private Integer managerId;// 메니저 아이디 , 외래키 
	private Integer departmentId;// 부서 아이디 , 외래키
}
