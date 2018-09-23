package com.design.framework.validata.factory;

import com.design.framework.validata.ValidationRules;
import com.design.framework.validata.ValidationRulesImpl;

public class ValidataFactory {

	
	public  static ValidationRules getValidataRules(){
		
		return new ValidationRulesImpl();
	}
}
