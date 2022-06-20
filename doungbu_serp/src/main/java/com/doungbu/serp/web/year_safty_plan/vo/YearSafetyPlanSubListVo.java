package com.doungbu.serp.web.year_safty_plan.vo;

import lombok.Data;

@Data
public class YearSafetyPlanSubListVo {
	
	String nm_activity; // 안전보건활동
	String nm_cycle; // 주기
	String work_time; // 시간
	String work_plan; // 월간추진일정(12)
	String nm_major; // 주관
	String nm_note; // 비고
	
	Boolean work_plan1;
	Boolean work_plan2;
	Boolean work_plan3;
	Boolean work_plan4;
	Boolean work_plan5;
	Boolean work_plan6;
	Boolean work_plan7;
	Boolean work_plan8;
	Boolean work_plan9;
	Boolean work_plan10;
	Boolean work_plan11;
	Boolean work_plan12;
	
}
