package com.design.framework.bean;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 导出
 * 
 * @author JohnDeng
 * @date 2018年8月8日下午3:35:04
 */
public class Export {

	@NotBlank(message = "标题名称不能为空")
	private String titleName;
	
	@NotBlank(message = "页面名称不能为空")
	private String sheetName;
	
	@NotBlank(message = "文件名称不能为空")
	private String fileName;
	
	@NotNull(message = "查询参数不能为空")
	private Map<String, Object> param;

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

}
