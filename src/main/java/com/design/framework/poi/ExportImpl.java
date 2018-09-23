package com.design.framework.poi;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.design.framework.bean.Export;
import com.design.framework.exception.bean.ParameterException;

/**
 * 
 * @author Administrator
 *
 */
public interface ExportImpl<T> {

	
	List<T> exportsQueryDateByParam(Export export);
	
	/**
	 * 导出
	 * 
	 * @param json
	 * @param t
	 * @throws ParameterException
	 */
	void exports(@Valid Export export, BindingResult result, T t, HttpServletResponse response)throws ParameterException;

}
