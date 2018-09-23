package com.design.framework.poi;

import org.springframework.web.multipart.MultipartFile;

import com.design.framework.utils.ImportUtils;


public class ImportHelp<T> implements ImportImpl<T> {
	
	@Override
	public void imports(MultipartFile file, T entity) {
		 ImportUtils.importExcel(file, 1, 1, entity.getClass());
	}

}
