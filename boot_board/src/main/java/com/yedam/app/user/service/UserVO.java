package com.yedam.app.user.service;

import lombok.Data;

@Data
public class UserVO {
	private String id;
	private String password;
	private String gender;
	private String name;
	private String joinDate;
}
