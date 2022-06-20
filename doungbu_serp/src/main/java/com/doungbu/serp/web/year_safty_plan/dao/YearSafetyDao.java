package com.doungbu.serp.web.year_safty_plan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.doungbu.serp.web.account.vo.AccountVo;
import com.doungbu.serp.web.common.vo.TableSearchVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanReturnVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanSubListVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanSubVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanTableVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanVo;


@Repository
public class YearSafetyDao {
	
	@Autowired
	private EntityManager eManager;
	
	public void YearSafetyPlanInsert(YearSafetyPlanVo yearSafetyPlanVo, AccountVo accountVo){
		
		if(yearSafetyPlanVo.getNm_document() != null) {
			this.eManager.createNamedStoredProcedureQuery("sp_year_safetyplan_delete")
				.setParameter("nm_document", yearSafetyPlanVo.getNm_document()).execute();
		}
		
		int cnt = 0;
		 
		for(YearSafetyPlanSubVo yearSafetyPlanSubVo : yearSafetyPlanVo.getYearSafetyPlanSubVos()) {
			StoredProcedureQuery spq = this.eManager.createNamedStoredProcedureQuery("sp_year_safetyplan_insert");
			
			spq.setParameter("nm_document", yearSafetyPlanVo.getNm_document());
			spq.setParameter("dt_year", "2022-06-20");
			spq.setParameter("cd_site", "AA00000");
			spq.setParameter("nm_site", "현장명");
			spq.setParameter("nm_type_seq", cnt);
			
			spq.setParameter("nm_type", yearSafetyPlanSubVo.getNm_type());
			spq.setParameter("cd_worker", accountVo.getUser_id());
			spq.setParameter("nm_worker", accountVo.getUsername());
			spq.setParameter("document_status", 0);
			
			spq.execute();
			
			String nm_document_out = (String)spq.getOutputParameterValue("nm_document_out");
			
			
			if(yearSafetyPlanVo.getNm_document() == null){
				yearSafetyPlanVo.setNm_document(nm_document_out);
			}
			
			for(YearSafetyPlanSubListVo yearSafetyPlanSubListVo :  yearSafetyPlanSubVo.getYearSafetyPlanSubListVos()) {
				//표에서 삭제된 줄 처리
				if(yearSafetyPlanSubListVo.getNm_activity() != null
						&& yearSafetyPlanSubListVo.getNm_cycle() != null
						&& yearSafetyPlanSubListVo.getWork_time() != null
						&& yearSafetyPlanSubListVo.getNm_major() != null
						&& yearSafetyPlanSubListVo.getNm_note() != null) {
					
					StoredProcedureQuery getNextKeyQuery2 = this.eManager.createNamedStoredProcedureQuery("sp_year_safetyplan_sub_insert");
					
					getNextKeyQuery2.setParameter("nm_document", yearSafetyPlanVo.getNm_document());
					getNextKeyQuery2.setParameter("nm_type_seq", cnt);
					getNextKeyQuery2.setParameter("nm_activity", yearSafetyPlanSubListVo.getNm_activity());
					getNextKeyQuery2.setParameter("nm_cycle", yearSafetyPlanSubListVo.getNm_cycle());
					getNextKeyQuery2.setParameter("work_time", yearSafetyPlanSubListVo.getWork_time());
					getNextKeyQuery2.setParameter("work_plan", Get_Work_Plan(yearSafetyPlanSubListVo));
					getNextKeyQuery2.setParameter("nm_major", yearSafetyPlanSubListVo.getNm_major());
					getNextKeyQuery2.setParameter("nm_note", yearSafetyPlanSubListVo.getNm_note());
					
					getNextKeyQuery2.execute();
				}
			}
			cnt++;
		}		
	}
	public YearSafetyPlanVo YearSafetyPlanSelect(String nm_document) {
		
		@SuppressWarnings("unchecked")
		List<YearSafetyPlanReturnVo> yearSafetyPlanReturnVos = this.eManager.createNamedStoredProcedureQuery("sp_year_safetyplan_select").setParameter("nm_document", nm_document).getResultList();
		
		if(yearSafetyPlanReturnVos.size() == 0) {
			return null;
		}
		
		//YearSafetyPlanVo
		//nm_document, dt_yaer, cd_site, nm_site, document_status, list - YearSafetyPlanSubVo
		YearSafetyPlanVo yearSafetyPlanVo = new YearSafetyPlanVo();
		
		yearSafetyPlanVo.setNm_document(yearSafetyPlanReturnVos.get(0).getNm_document());
		yearSafetyPlanVo.setDt_yaer(yearSafetyPlanReturnVos.get(0).getDt_year());
		yearSafetyPlanVo.setCd_site(yearSafetyPlanReturnVos.get(0).getCd_site());
		yearSafetyPlanVo.setNm_site(yearSafetyPlanReturnVos.get(0).getNm_site());
		yearSafetyPlanVo.setDocument_status(yearSafetyPlanReturnVos.get(0).getDocument_status());
		
		int nm_type_seq = yearSafetyPlanReturnVos.get(0).getNm_type_seq();
		
		ArrayList<YearSafetyPlanSubVo> yearSafetyPlanSubVos = new ArrayList<>();
		
		//YearSafetyPlanSubVo
		//nm_type_seq, nm_type, list - YearSafetyPlanSubListVo
		YearSafetyPlanSubVo yearSafetyPlanSubVo = new YearSafetyPlanSubVo();
		
		yearSafetyPlanSubVo.setNm_type(yearSafetyPlanReturnVos.get(0).getNm_type());
		yearSafetyPlanSubVo.setNm_type_seq(nm_type_seq);
		
		ArrayList<YearSafetyPlanSubListVo> yearSafetyPlanSubListVos = new ArrayList<>();
		
		for(YearSafetyPlanReturnVo yearSafetyPlanReturnVo : yearSafetyPlanReturnVos) {
			//YearSafetyPlanSubListVo
			//nm_activity, nm_cycle, work_time, work_plan, nm_major, nm_note
			if(nm_type_seq != yearSafetyPlanReturnVo.getNm_type_seq()) {
				yearSafetyPlanSubVo.setYearSafetyPlanSubListVos(yearSafetyPlanSubListVos);
				
				yearSafetyPlanSubVos.add(yearSafetyPlanSubVo);
				
				yearSafetyPlanSubVo = new YearSafetyPlanSubVo();
				yearSafetyPlanSubVo.setNm_type(yearSafetyPlanReturnVo.getNm_type());
				yearSafetyPlanSubVo.setNm_type_seq(yearSafetyPlanReturnVo.getNm_type_seq());
				yearSafetyPlanSubListVos = new ArrayList<>();
				
				nm_type_seq = yearSafetyPlanReturnVo.getNm_type_seq();
			}
			
			YearSafetyPlanSubListVo yearSafetyPlanSubListVo = new YearSafetyPlanSubListVo();
			yearSafetyPlanSubListVo.setNm_activity(yearSafetyPlanReturnVo.getNm_activity());
			yearSafetyPlanSubListVo.setNm_cycle(yearSafetyPlanReturnVo.getNm_cycle());
			yearSafetyPlanSubListVo.setWork_time(yearSafetyPlanReturnVo.getWork_time());
			yearSafetyPlanSubListVo = Set_Work_Plan(yearSafetyPlanReturnVo.getWork_plan(), yearSafetyPlanSubListVo);
			yearSafetyPlanSubListVo.setNm_major(yearSafetyPlanReturnVo.getNm_major());
			yearSafetyPlanSubListVo.setNm_note(yearSafetyPlanReturnVo.getNm_note());
			yearSafetyPlanSubListVos.add(yearSafetyPlanSubListVo);
		}
		yearSafetyPlanSubVo.setYearSafetyPlanSubListVos(yearSafetyPlanSubListVos);
		
		yearSafetyPlanSubVos.add(yearSafetyPlanSubVo);
		yearSafetyPlanVo.setYearSafetyPlanSubVos(yearSafetyPlanSubVos);
		
		return yearSafetyPlanVo;
	}
	
	public String Get_Work_Plan(YearSafetyPlanSubListVo yearSafetyPlanSubListVo) {
		
		String work_plan = "";
		
		Boolean[] work_plan_list = {
				yearSafetyPlanSubListVo.getWork_plan1()
				,yearSafetyPlanSubListVo.getWork_plan2()
				,yearSafetyPlanSubListVo.getWork_plan3()
				,yearSafetyPlanSubListVo.getWork_plan4()
				,yearSafetyPlanSubListVo.getWork_plan5()
				,yearSafetyPlanSubListVo.getWork_plan6()
				,yearSafetyPlanSubListVo.getWork_plan7()
				,yearSafetyPlanSubListVo.getWork_plan8()
				,yearSafetyPlanSubListVo.getWork_plan9()
				,yearSafetyPlanSubListVo.getWork_plan10()
				,yearSafetyPlanSubListVo.getWork_plan11()
				,yearSafetyPlanSubListVo.getWork_plan12()
				};
		
		
		
		for(Boolean value :  work_plan_list) {
			if(value == null) {
				work_plan += "1";
			}else if(value == true) {
				work_plan += "1";
			}else if(value == false){
				work_plan += "0";
			}else {
				work_plan += "0";
			}
		}
		
		return work_plan;
		
	}
	
	public YearSafetyPlanSubListVo Set_Work_Plan(String work_plan, YearSafetyPlanSubListVo yearSafetyPlanSubListVo) {
		
		if(work_plan.length() != 12) {
			return yearSafetyPlanSubListVo;
		}
		char[] charArray = work_plan.toCharArray();

		yearSafetyPlanSubListVo.setWork_plan1((charArray[0] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan2((charArray[1] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan3((charArray[2] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan4((charArray[3] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan5((charArray[4] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan6((charArray[5] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan7((charArray[6] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan8((charArray[7] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan9((charArray[8] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan10((charArray[9] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan11((charArray[10] == '1') ? true : null);
		yearSafetyPlanSubListVo.setWork_plan12((charArray[11] == '1') ? true : null);
		
		return yearSafetyPlanSubListVo;
	}
	
	@SuppressWarnings("unchecked")
	public List<YearSafetyPlanTableVo> GetYearSafetyPlanTableList(TableSearchVo tableSearchVo){
		return this.eManager.createNamedStoredProcedureQuery("sp_year_safetyplan_table_select")
				.setParameter("dt_from", tableSearchVo.getDt_from())
				.setParameter("dt_to", tableSearchVo.getDt_to())
				.setParameter("document_status", tableSearchVo.getDocument_status())
				.setParameter("cd_site", tableSearchVo.getCd_site())
				.setParameter("cd_worker", tableSearchVo.getCd_worker())
				.setParameter("nm_search", tableSearchVo.getNm_search()).getResultList();
	}
}
