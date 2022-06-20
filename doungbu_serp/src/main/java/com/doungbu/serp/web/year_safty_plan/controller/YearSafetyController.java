package com.doungbu.serp.web.year_safty_plan.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doungbu.serp.web.account.service.AccountDetail;
import com.doungbu.serp.web.account.vo.AccountVo;
import com.doungbu.serp.web.common.dao.CodeTableDao;
import com.doungbu.serp.web.common.vo.TableSearchVo;
import com.doungbu.serp.web.year_safty_plan.dao.YearSafetyDao;
import com.doungbu.serp.web.year_safty_plan.validator.YearSafetyPlanVoValidator;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanSubVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanTableVo;
import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/year_safty_plan")
public class YearSafetyController {
	
	@Autowired
	private YearSafetyPlanVoValidator yearSafetyPlanVoValidator; 
	
	@Autowired
	private YearSafetyDao yearSafetyDao;
	
	@Autowired
	private CodeTableDao codetDao;
	
	@GetMapping("/list")
    public String GetList(Model model, @AuthenticationPrincipal AccountDetail accountDetail) {
		
		TableSearchVo tableSearchVo = new TableSearchVo(); 
		tableSearchVo = tableSearchVo.SetTableSearchVo(tableSearchVo, codetDao, (AccountVo)accountDetail.getAccount());
		List<YearSafetyPlanTableVo> yearSafetyPlanTableVo = yearSafetyDao.GetYearSafetyPlanTableList(tableSearchVo);
		yearSafetyDao.GetYearSafetyPlanTableList(tableSearchVo);
		
		model.addAttribute("tableSearchVo", tableSearchVo);
		model.addAttribute("yearSafetyPlanTableVo", yearSafetyPlanTableVo);
        return "year_safty_plan/list";
    }
	
	@PostMapping("/list")
    public String Serchlist(Model model, @ModelAttribute TableSearchVo tableSearchVo) {
		List<YearSafetyPlanTableVo> yearSafetyPlanTableVo = yearSafetyDao.GetYearSafetyPlanTableList(tableSearchVo);
		yearSafetyDao.GetYearSafetyPlanTableList(tableSearchVo);
		
		tableSearchVo = tableSearchVo.SetTableSearchVo_setCodeList(tableSearchVo, codetDao);
		model.addAttribute("tableSearchVo", tableSearchVo);
		model.addAttribute("yearSafetyPlanTableVo", yearSafetyPlanTableVo);
        return "year_safty_plan/list";
    }
    
    @GetMapping("/form")
    public String Form(Model model , @RequestParam(required = false) String nm_documnet) {
    	
        LocalDate now = LocalDate.now();
        
        YearSafetyPlanVo yearSafetyPlanVo = new YearSafetyPlanVo();
        if(nm_documnet != null) {
        	yearSafetyPlanVo = yearSafetyDao.YearSafetyPlanSelect(nm_documnet);
        	model.addAttribute("yearSafetyPlanVo", yearSafetyPlanVo);
        }
        //문서를 조회했는데 빈값일때, 혹 doc_no가 null일때
        if(yearSafetyPlanVo == null || yearSafetyPlanVo.getYearSafetyPlanSubVos() == null) {
        	yearSafetyPlanVo = new YearSafetyPlanVo();
	    	
        	ArrayList<YearSafetyPlanSubVo> yearSafetyPlanSubVos = new ArrayList<>();
        	YearSafetyPlanSubVo yearSafetyPlanSubVo = new YearSafetyPlanSubVo();
        	
        	yearSafetyPlanSubVo.setNm_type("");
        	yearSafetyPlanVo.setNm_document(nm_documnet);
        	yearSafetyPlanSubVo.category_list_emthy_add();
        	
        	yearSafetyPlanSubVos.add(yearSafetyPlanSubVo);
        	yearSafetyPlanVo.setYearSafetyPlanSubVos(yearSafetyPlanSubVos);
        	
        	yearSafetyPlanVo.setDt_yaer(now.getYear() + "년");
        	
        	
        	model.addAttribute("yearSafetyPlanVo", yearSafetyPlanVo);
        }

        return "year_safty_plan/form";
    }
    
    @PostMapping("/form")
    public String Setform(@ModelAttribute YearSafetyPlanVo yearSafetyPlanVo, BindingResult bindingresult, @AuthenticationPrincipal AccountDetail accountDetail) {
    	
    	yearSafetyPlanVoValidator.validate(yearSafetyPlanVo, bindingresult);
    	
    	if(bindingresult.hasErrors()) {
			return "year_safty_plan/form";
		}
    	
    	if(yearSafetyPlanVo.getNm_document().equals("") 
				|| yearSafetyPlanVo.getNm_document().equals(null) 
				|| yearSafetyPlanVo.getNm_document().length() == 0) {
    		yearSafetyPlanVo.setNm_document(null);
    	}
    	
    	yearSafetyDao.YearSafetyPlanInsert(yearSafetyPlanVo, (AccountVo)accountDetail.getAccount());
    	
    	return "redirect:/year_safty_plan/list";
    }

}
