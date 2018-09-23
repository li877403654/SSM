package com.design.framework.utils;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
/**
 * 导入工具类
 * @author JohnDeng
 * @datatime 2018年7月9日下午4:12:08
 */
public class ImportUtils {

	public static <T> List<T> importExcel(String filePath, Integer titleRows,
			Integer headerRows, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass,
					params);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> importExcel(MultipartFile file,Integer titleRows, Integer headerRows, Class<T> pojoClass) {
		if (file == null) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(),pojoClass, params);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
