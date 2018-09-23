package com.design.framework.validata;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.design.framework.utils.ReflectUtils;
import com.design.framework.validata.bean.FilterFileldList;
import com.design.framework.validata.bean.ValidationResult;
import com.design.framework.validata.factory.ValidataFactory;

/**
 * 实体检验类
 * 
 * @author JohnDeng
 * @date 2018年8月8日下午3:41:34
 */
public class BeanValidation {

	/**
	 * 参数检验，检验失败return
	 * 
	 * @author JohnDeng
	 * @datatime 2018年9月12日下午8:04:29
	 * @param object
	 * @param validationResult
	 * @return
	 */
	public static boolean validateParamFail(Object object, ValidationResult validationResult) {
		if (object == null || validationResult == null) {
			return false;
		}
		List<Field> fields = ReflectUtils.getFields(ReflectUtils.getClass(object));
		for (Field field : fields) {
			if (FilterFileldList.filter(field.getName())) {
				continue;
			}
			NotNull notNull = field.getAnnotation(NotNull.class);
			Min min = field.getAnnotation(Min.class);// 获取@Min注解
			Max max = field.getAnnotation(Max.class);// 获取@Max注解
			if (notNull != null) {
				return ValidataFactory.getValidataRules().notNullValid(object, field, notNull, validationResult);
			}
			if (min != null) {
				return ValidataFactory.getValidataRules().minValid(object, field, min, validationResult);
			}
			if (max != null) {
				return ValidataFactory.getValidataRules().maxValid(object, field, max, validationResult);
			}
		}
		return true;
	}

	/**
	 * 检验ID true没值,false有值
	 * 
	 * @author JohnDeng 2017年11月9日上午11:57:21
	 * @param clazz
	 * @return
	 */
	public static boolean validIdFail(Object object) {
		if (object == null) {
			return false;
		}
		List<Field> fields = ReflectUtils.getFields(ReflectUtils.getClass(object));
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				try {
					Object value = ReflectUtils.getMethodValue(object, field);
					if (value == null || value.equals("")) {
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
