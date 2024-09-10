package com.yedam.app.security.service;

import lombok.Data;

/**
 * Mapper의 실행결과로 오는
 * 컬럼값을 기준으로 작성해주면된다.
 */
@Data
public class UserVO {
	private String loginId;
	private String password;
	private String roleName;
}
