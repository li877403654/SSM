package com.design.framework.poi;

import org.springframework.web.multipart.MultipartFile;

public interface ImportImpl<T> {

	void imports( MultipartFile file, T entity);
}
