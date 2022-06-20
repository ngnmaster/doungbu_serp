package com.doungbu.serp.web.year_safty_plan.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;
@Entity
@Data
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		    name = "sp_year_safetyplan_table_select", 
		    procedureName = "sp_year_safetyplan_table_select", 
		    resultClasses = YearSafetyPlanTableVo.class,
		    parameters = {  //위치 매개변수 매핑 사용
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "dt_from"),
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "dt_to"),
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "document_status"),
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "cd_site"),
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "cd_worker"),
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "nm_search")  		
		    }
	)
})
public class YearSafetyPlanTableVo {
	@Id
	@Column(name = "nm_document")
	private String nm_document;
	
	@Column(name = "document_status")
	private int document_status;
	
	@Column(name = "dt_year")
	private String dt_year;
	
	@Column(name = "nm_site")
	private String nm_site;
	
	@Column(name = "nm_worker")
	private String nm_worker;
	
	@Column(name = "work_time")
	private String work_time;
	
	@Column(name = "nm_payment_user")
	private String nm_payment_user;
	
	@Column(name = "approval_time")
	private String approval_time;
}
