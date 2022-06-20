package com.doungbu.serp.web.year_safty_plan.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class YearSafetyPlanReturnVo {

	@Id
	@Column(name = "idx")
	int idx;
	
	@Column(name = "nm_document")
	String nm_document; // 문서번호

	@Column(name = "cd_site")
	String cd_site; // 현장 코드
	
	@Column(name = "nm_site")
	String nm_site; // 현장 명칭
	
	@Column(name = "dt_year")
	String dt_year; // 년도
	
	@Column(name = "nm_type_seq")
	int nm_type_seq; // 구분 순서
	
	@Column(name = "nm_type")
	String nm_type; // 구분
	
	@Column(name = "nm_activity")
	String nm_activity; // 안전보건활동
	
	@Column(name = "nm_cycle")
	String nm_cycle; // 주기
	
	@Column(name = "work_time")
	String work_time; // 시간
	
	@Column(name = "work_plan")
	String work_plan; // 월간추진일정(12)
	
	@Column(name = "nm_major")
	String nm_major; // 주관
	
	@Column(name = "nm_note")
	String nm_note; // 비고
	
	@Column(name = "document_status")
	int document_status; // 문서 결재 상태
		
	
}
