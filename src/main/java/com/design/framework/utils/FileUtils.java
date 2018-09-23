package com.design.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.imageio.stream.FileImageInputStream;
/**
 * 文件工具类
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:16:49
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * 生成文件
	 * 
	 * @param path
	 *            路径
	 * @param pathName
	 *            文件名
	 * @param text
	 *            文件内容
	 * @author John
	 * @datatime 2017年9月12日上午1:30:32
	 */
	public static void wirteContent(String path, String pathName, String text) {
		File file = new File(path, pathName);
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			file.createNewFile();
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			writer.write(text);
			writer.newLine();// 换行
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param modeltype
	 * @return
	 * @author John
	 * @datatime 2017年9月25日上午1:34:32
	 */
	public static String readFileText(String pathName) {
		try {
			URL url = FileUtils.class.getClassLoader().getResource(pathName);
			File file = new File(url.getFile());
			StringBuffer sb = new StringBuffer();
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			br.close();
			reader.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param destDirName
	 * @return
	 * @author John
	 * @datatime 2017年10月27日下午5:16:32
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param destFileName
	 * @return
	 * @author John
	 * @datatime 2017年10月27日下午5:37:14
	 */
	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		return true;
	}

	/**
	 * 文件流转二进制流
	 * 
	 * @author JohnDeng
	 * @datatime 2018年3月14日下午4:38:48
	 * @param file
	 * @return
	 */
	public static byte[] fileToByte(File file) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}
}
