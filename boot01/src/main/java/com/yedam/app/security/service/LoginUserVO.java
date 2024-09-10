package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

/**
 * 스프링 시큐리티를 위한 유저라 UserDetails을 구현해야한다.
 */
@Getter
public class LoginUserVO implements UserDetails {

	private UserVO userVO;
	
	public LoginUserVO(UserVO userVO){
		this.userVO = userVO;
	}
	
	/**
	 * 권한들을 리턴한다.
	 * Collection 타입이라 다양한 컬렉션 프레임워크를 활용하여 반환할 수 있다.
	 * 예 : list, map 등
	 * 제네릭 타입으로 GrantedAuthority을 상속받은 대상 타입만
	 * 지정할 수 있다.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auths;
	}

	/**
	 * 비밀번호 리턴
	 * 여기서 리턴한 대상이 스프링 시큐리티의 비번이 된다.
	 */
	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	/**
	 * username(스프링 시큐리티의 아이디)
	 */
	@Override
	public String getUsername() {
		// userVO 의 loginId값을 유저네임으로 쓸 것이다.
		return userVO.getLoginId(); // 유저네임 반환
	}

	/*
	 * 아래 boolean 타입의 메서드들중 하나라도
	 * false를 반환하면  로그인이 안되기 때문에
	 * 임시로 true로 봐꿔준다.
	 * */
	
	/**
	 * 계정 만료 여부
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 잠금 여부
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 패스워드 만료 여부
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 계정 사용여부 
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
