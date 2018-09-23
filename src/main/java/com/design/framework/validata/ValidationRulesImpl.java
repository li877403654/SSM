package com.design.framework.validata;

import java.lang.reflect.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.design.framework.utils.ReflectUtils;
import com.design.framework.utils.RegexUtils;
import com.design.framework.validata.bean.ValidationResult;

public class ValidationRulesImpl implements ValidationRules {

	@Override
	public boolean notNullValid(Object object, Field field, NotNull notNull, ValidationResult validationResult) {
		validationResult.setFieldName(field.getName());
		Object value = ReflectUtils.getMethodValue(object, field);
		if (value == null || value.equals("")) {
			if (notNull.message() == null || notNull.message().equals("")) {
				validationResult.setMessage(field.getName() + DefaultValidationMssage.NOT_NULL);
			} else if ("{javax.validation.constraints.NotNull.message}".equals(notNull.message())) {
				validationResult.setMessage(field.getName() + DefaultValidationMssage.NOT_NULL);
			} else {
				validationResult.setMessage(notNull.message());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean minValid(Object object, Field field,Min min, ValidationResult validationResult) {
		validationResult.setFieldName(field.getName());
		Object value = ReflectUtils.getMethodValue(object, field);
		if(!(value == null || value.equals(""))){
			int length=0;
			if(value instanceof String) {
				length=String.valueOf(value).length();
			}else if (value instanceof Integer) {
				Integer ivalue=(int) value;
				length=Integer.toString(ivalue).length();
			}
			if(length<min.value()) {
				
				validationResult.setMessage(field.getName()+DefaultValidationMssage.MIN+min.value());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean maxValid(Object object, Field field, Max max , ValidationResult validationResult) {
		validationResult.setFieldName(field.getName());
		Object value = ReflectUtils.getMethodValue(object, field);
		if(!(value == null || value.equals(""))){
			int length=0;
			if(value instanceof String) {
				length=String.valueOf(value).length();
			}else if (value instanceof Integer) {
				Integer ivalue=(int) value;
				length=Integer.toString(ivalue).length();
			}
			if(length>max.value()) {
				if(value instanceof String) {
					length=String.valueOf(value).length();
				}else if (value instanceof Integer) {
					Integer ivalue=(int) value;
					length=Integer.toString(ivalue).length();
				}
				if(length>max.value()) {
					validationResult.setMessage(field.getName()+ DefaultValidationMssage.MAX+max.value());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean emailValid(Object object, Field field, Email email, ValidationResult validationResult) {
		validationResult.setFieldName(field.getName());
		Object value = ReflectUtils.getMethodValue(object, field);
		if (value == null || value.equals("")) {
			if (email.message() == null || email.message().equals("")) {
				validationResult.setMessage(field.getName() + DefaultValidationMssage.EMAIL);
			} else if ("{org.hibernate.validator.constraints.Email.message}".equals(email.message())) {
				validationResult.setMessage(field.getName() + DefaultValidationMssage.EMAIL);
			} else {
				validationResult.setMessage(email.message());
			}
			return false;
		}else if(RegexUtils.checkEmail((String) value)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean lengthValid(Object object, Field field, Length length, ValidationResult validationResult) {
		validationResult.setFieldName(field.getName());
		
		
		
		
		
		return false;
	}
	
	
	

}
