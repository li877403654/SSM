package com.design.framework.template;

import java.util.ArrayList;
import java.util.List;

/**
 * mysql 转 Java 数据类型枚举
 * @author JohnDeng
 * @date 2018年8月8日下午3:38:22
 */
public enum TypeEnum {
	
 	VARCHAR("VARCHAR","String"),
	CHAR("CHAR","String"),
	BLOB("BLOB","byte[]"),
	TEXT("TEXT","String"),
	INTEGER("INTEGER","Long"),
	TINYINT("TINYINT","Integer"),
	SMALLINT("VARCHAR","Integer"),
	MEDIUMINT("MEDIUMINT","Integer"),
	BIT("BIT","Boolean"),
	BIGINT("VARCHAR","BigInteger"),
	FLOAT("FLOAT","Float"),
	DOUBLE("DOUBLE","Double"),
	DECIMAL("DECIMAL","BigDecimal"),
	BOOLEAN("VARCHAR","Integer"),
	DATE("DATE","Date"),
	DATETIME("DATETIME","Date"),
	INT("INT","int"),
	TIMESTAMP("TIMESTAMP","Date");
 	
	
	private final String code;
	private final String type;
	private TypeEnum(String code, String type) {
		this.code = code;
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	/**
	 * 通过枚举<code>code</code>获得枚举
	 * 
	 * @param code
	 * @return LogResultEnum
	 */
	public static TypeEnum getByCode(String code) {
		for (TypeEnum _enum : values()) {
			if (_enum.getCode().equalsIgnoreCase(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 * 
	 * @return List<LogResultEnum>
	 */
	public List<TypeEnum> getAllEnum() {
		List<TypeEnum> list = new ArrayList<TypeEnum>();
		for (TypeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	
	
	
	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public List<String> getAllEnumCode() {
		List<String> list = new ArrayList<String>();
		for (TypeEnum _enum : values()) {
			list.add(_enum.code);
		}
		return list;
	}
	
	
}
