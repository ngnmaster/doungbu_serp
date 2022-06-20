package com.doungbu.serp.web.year_safty_plan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.doungbu.serp.web.year_safty_plan.vo.YearSafetyPlanVo;
import com.microsoft.sqlserver.jdbc.StringUtils;


@Component
public class YearSafetyPlanVoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		YearSafetyPlanVo vo = (YearSafetyPlanVo) target;
		if(StringUtils.isEmpty(vo.getDt_yaer())) {
			errors.rejectValue("Dt_yaer", "key", "년도를 입력해주세요");
		}
	}

}
