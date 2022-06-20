package com.doungbu.serp.web.year_safty_plan.vo;


import java.util.ArrayList;

import lombok.Data;

@Data
public class YearSafetyPlanSubVo {
	
	public YearSafetyPlanSubVo() {
		super();
		yearSafetyPlanSubListVos = new ArrayList<>();
	}
	
	int nm_type_seq; // 구분 순서
	String nm_type; // 구분 Text
	
	ArrayList<YearSafetyPlanSubListVo> yearSafetyPlanSubListVos;
	
	public void category_list_emthy_add() {
		YearSafetyPlanSubListVo yearSafetyPlanSubListVo = new YearSafetyPlanSubListVo();
		
		yearSafetyPlanSubListVo.setNm_activity("");
		
		this.yearSafetyPlanSubListVos.add(yearSafetyPlanSubListVo);
	}
	
	
}
