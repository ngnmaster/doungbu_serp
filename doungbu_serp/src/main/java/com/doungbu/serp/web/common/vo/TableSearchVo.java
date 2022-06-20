package com.doungbu.serp.web.common.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doungbu.serp.web.account.vo.AccountVo;
import com.doungbu.serp.web.common.dao.CodeTableDao;

import lombok.Data;

@Data
public class TableSearchVo {
	private String dt_from;
	private String dt_to;
	private int document_status;
	private String cd_site;
	private String cd_worker;
	private String nm_search;
	
	private List<CodeTable> codeList;
	

	//테이블 search 기본 세팅
	public TableSearchVo SetTableSearchVo(TableSearchVo tableSearchVo, CodeTableDao codetDao, AccountVo accountVo) {
		Calendar calendar = Calendar.getInstance();
		//현재 날짜로 설정
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int start = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(year, month, start);
        String start_date =  dateFormat.format(calendar.getTime());
        calendar.set(year, month, end);
        String end_date= dateFormat.format(calendar.getTime());
        
        tableSearchVo.setCd_site("AA00000");
        tableSearchVo.setDt_from(start_date);
        tableSearchVo.setDt_to(end_date);
        tableSearchVo.setDocument_status(0);
        tableSearchVo.setCd_worker(accountVo.getUser_id());
        tableSearchVo.setNm_search("");
        tableSearchVo.setCodeList(codetDao.boardCodeSelect(0));
        
		return tableSearchVo;
	}
	
	public TableSearchVo SetTableSearchVo_setCodeList(TableSearchVo tableSearchVo, CodeTableDao codetDao) {
		tableSearchVo.setCodeList(codetDao.boardCodeSelect(0));
		return tableSearchVo;
	}
}
