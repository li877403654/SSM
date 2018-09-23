package com.design.framework.validata.bean;

import java.util.Arrays;
import java.util.List;

public class FilterFileldList {

	public static List<String> filterFieldNameList = Arrays.asList("serialVersionUID", "createTime", "updateTime");

	public static boolean filter(String fieldName) {
		for (String name : filterFieldNameList) {
			if (fieldName.equals(name)) {
				return true;
			}
		}
		return false;
	}
}
