package com.yedam.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.mapper.UserMapper;
import com.yedam.app.security.service.LoginUserVO;
import com.yedam.app.security.service.UserVO;

import lombok.RequiredArgsConstructor;

/**
 * UserDetailsService 을 구현하여
 * 인증 방식 설정하기
 */
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
	private final UserMapper userMapper;
	/**
	 * 유저가 있는지 판단한다.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB 방식으로 정보를 가져오기 위해 Mapper 사용
		UserVO userVO = userMapper.getUserInfo(username);
		
		// 해당 유저가 없을 시 
		if(userVO == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new LoginUserVO(userVO);
	}
	
}
