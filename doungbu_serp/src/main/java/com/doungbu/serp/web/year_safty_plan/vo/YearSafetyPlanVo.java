package com.doungbu.serp.web.year_safty_plan.vo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.print.DocFlavor.CHAR_ARRAY;

import lombok.Data;

@Entity
@Data
@NamedStoredProcedureQueries({	
	@NamedStoredProcedureQuery(name = "sp_year_safetyplan_insert", procedureName = "sp_year_safetyplan_insert",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_document", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dt_year", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cd_site", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_site", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_type_seq", type = int.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_type", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "cd_worker", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_worker", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "document_status", type = int.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "nm_document_out", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_code_out", type = int.class) 
		}),
	@NamedStoredProcedureQuery(name = "sp_year_safetyplan_sub_insert", procedureName = "sp_year_safetyplan_sub_insert",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_document", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_type_seq", type = int.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_activity", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_cycle", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "work_time", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "work_plan", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_major", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_note", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_code_out", type = int.class)
		}),
	@NamedStoredProcedureQuery(name = "sp_year_safetyplan_select", procedureName = "sp_year_safetyplan_select", resultClasses = YearSafetyPlanReturnVo.class,
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_document", type = String.class)
	}),
	@NamedStoredProcedureQuery(name = "sp_year_safetyplan_delete", procedureName = "sp_year_safetyplan_delete", resultClasses = YearSafetyPlanReturnVo.class,
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "nm_document", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_code_out", type = int.class)
	})
})
//sp_year_safetyplan_delete
public class YearSafetyPlanVo {
	
	@Id
	@Column(name = "nm_document")
	String nm_document; // 문서번호
		
	String dt_yaer; // 년도
	String cd_site; // 현장 코드
	String nm_site; // 현장 명칭
	
	String cd_worker; // 사용자 코드
	String nm_worker; // 사용자 명칭
	
	int document_status; // 문서 결재 상태
	String dt_work_time; // 작성일자

	ArrayList<YearSafetyPlanSubVo> yearSafetyPlanSubVos;
}
