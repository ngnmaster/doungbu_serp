package com.doungbu.serp.web.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doungbu.serp.web.account.dao.AccountDao;
import com.doungbu.serp.web.account.vo.AccountVo;

@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountVo accountVo = accountDao.getUser(username);
		
		if (accountVo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }
		
		return new AccountDetail(accountVo);
	}

}
