package com.doungbu.serp.web.account.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.doungbu.serp.web.account.vo.AccountVo;

public class AccountDetail implements UserDetails{
	
	private AccountVo accountVo;

	public AccountDetail(AccountVo accountVo){
		this.accountVo = accountVo;
	}
	
	public AccountVo getAccount() {
		return accountVo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub {noop}
		String pw = new BCryptPasswordEncoder().encode(accountVo.getPasswd());
		//return pw;
		return "{noop}" + accountVo.getPasswd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
