package com.doungbu.serp.web.account.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.doungbu.serp.web.account.vo.AccountVo;

@Repository
public class AccountDao {
	
	@Autowired
	private EntityManager eManager;
	
	public void setUser(AccountVo accountVo){
    	return;
		/*
		eManager.createNamedStoredProcedureQuery("sp_user_insert")
				.setParameter("emp_no", accountVo.getUsername())
				.setParameter("passwd", accountVo.getPasswd()).getResultList();
				
				*/
	}
	
	public AccountVo getUser(String username){
		AccountVo accountVo = (AccountVo)eManager.createNamedStoredProcedureQuery("sp_user_select_by_id").setParameter("username", username).getResultList().get(0);
		return accountVo;
	}
	
}
