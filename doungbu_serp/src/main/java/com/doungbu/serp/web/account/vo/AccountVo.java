package com.doungbu.serp.web.account.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;



import lombok.Data;

@Entity
@Data
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "sp_user_select_by_id", procedureName = "sp_user_select_by_id", resultClasses = AccountVo.class,
	parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class)}),
	
	@NamedStoredProcedureQuery(name = "sp_user_insert", procedureName = "sp_user_insert", resultClasses = AccountVo.class,
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class)
	}),
})
public class AccountVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "emp_no")
	String user_id;
	
	@Column(name = "emp_nm")
	String username;
	
	@Column(name = "reg_no")
	String req_no;
	
	@Column(name = "passwd")
	String passwd;
	
	@Column(name = "mobile")
	String mobile;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "job_title_nm")
	String job_title_nm;
	
	@Column(name = "duty_nm")
	String duty_nm;
	
	@Column(name = "useYN")
	Boolean useYN;

}
