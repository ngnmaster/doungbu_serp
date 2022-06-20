package com.doungbu.serp.web.common.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.doungbu.serp.web.common.vo.CodeTable;

@Repository
public class CodeTableDao {
	
	@Autowired
	private EntityManager eManager;
	
	@SuppressWarnings("unchecked")
	public List<CodeTable> boardCodeSelect(int cd_code_type){
		return eManager.createNamedStoredProcedureQuery("sp_tb_code_select").setParameter("cd_code_type", cd_code_type).getResultList();
	}
}
