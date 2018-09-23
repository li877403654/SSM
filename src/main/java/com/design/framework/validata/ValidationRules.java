package com.design.framework.validata;

import java.lang.reflect.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.design.framework.validata.bean.ValidationResult;

public interface ValidationRules {

	/**
	   * 被注释的元素必须不为 null
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午7:21:51
	 * @param object
	 * @param field
	 * @param notNull
	 * @param validationResult
	 * @return
	 */
	boolean notNullValid(Object object, Field field, NotNull notNull, ValidationResult validationResult);

	/**
	    *            被注释的元素必须是一个数字，其值必须大于等于指定的最小值
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午7:22:23
	 * @param object
	 * @param field
	 * @param min
	 * @param validationResult
	 * @return
	 */
	boolean minValid(Object object, Field field, Min min, ValidationResult validationResult);

	/**
	 *    	  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午7:23:35
	 * @param object
	 * @param field
	 * @param max
	 * @param validationResult
	 * @return
	 */
	boolean maxValid(Object object, Field field, Max max, ValidationResult validationResult);
	
	/**
	 * 被注释的元素必须是电子邮箱地址
	 * @author JohnDeng
	 * @datatime 2018年9月20日上午10:49:47
	 * @param object
	 * @param field
	 * @param email
	 * @param validationResult
	 * @return
	 */
	boolean emailValid(Object object, Field field, Email email , ValidationResult validationResult);
	
	
	/**
	 *  被注释的字符串的大小必须在指定的范围内
	 * @author JohnDeng
	 * @datatime 2018年9月20日上午11:00:35
	 * @param object
	 * @param field
	 * @param length
	 * @param validationResult
	 * @return
	 */
	boolean lengthValid(Object object, Field field, Length length , ValidationResult validationResult);
	
	

}