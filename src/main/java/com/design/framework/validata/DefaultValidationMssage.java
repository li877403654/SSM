package com.design.framework.validata;

import java.lang.reflect.Field;

public class DefaultValidationMssage {

	public static final String NOT_NULL = "不能为空";

	public static final String MIN_LENGTH = "不可少于{rule.param}个字符";

	public static final String MAX_LENGTH = "不可超过{rule.param}个字符";

	public static final String MIN = "最小值为";

	public static final String MAX = "最大值为";
	
	public static final String EMAIL = "邮箱格式不正确";
	
	

	public static String get(String s) {
		try {
			Field field = DefaultValidationMssage.class.getDeclaredField(s);
			return (String) field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
