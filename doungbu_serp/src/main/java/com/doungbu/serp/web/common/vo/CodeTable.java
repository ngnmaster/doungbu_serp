package com.doungbu.serp.web.common.vo;

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
		    name = "sp_tb_code_select", 
		    procedureName = "sp_tb_code_select", 
		    resultClasses = CodeTable.class,
		    parameters = {
		    	@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "cd_code_type")
		    }
	)
})
public class CodeTable {
	@Id
	@Column(name = "cd_code")
	private String cd_code;
	
	@Column(name = "nm_code")
	private String nm_code;
}
