package com.doungbu.serp.web.year_safty_plan.vo;

import lombok.Data;

@Data
public class YearSafetyPlanTableSearchVo {
	
	private String dt_from;
	private String dt_to;
	private int document_status;
	private String cd_site;
	private String cd_worker;
	private String nm_search;
}
