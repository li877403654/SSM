package com.design.framework.poi;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.design.framework.bean.Export;
import com.design.framework.exception.bean.ParameterException;
import com.design.framework.utils.ExportUtils;

public  class ExportHelp<T> implements ExportImpl<T> {
	

	@Override
	public void exports(@Valid Export export, BindingResult result, T t, HttpServletResponse response)
			throws ParameterException {
		if (result.hasErrors()) {
			throw new ParameterException(
					result.getFieldError().getField() + result.getFieldError().getDefaultMessage());
		}
		List<T> list = exportsQueryDateByParam(export);
		ExportUtils.exportExcel(list, export.getTitleName(), export.getSheetName(), t.getClass(),
				export.getFileName() + ".xlsx", response);
	}

	@Override
	public List<T> exportsQueryDateByParam(Export export) {
		
		return null;
	}
}
