package com.design.framework.utils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static boolean isEmpty(Object... obj) {
		if (obj.length > 0) {
			for (Object o : obj) {
				if (o == null || "".equals(o)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isNotEmpty(Object... obj) {

		return !isEmpty(obj);
	}
}
