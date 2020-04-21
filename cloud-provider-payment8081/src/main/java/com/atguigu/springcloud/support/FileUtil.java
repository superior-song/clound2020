package com.atguigu.springcloud.support;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
/**
 * <p>Title: FileUtil.java</p>
 * <p>Description:通用的文件工具类</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * @author songxiaoliang
 * @date 2019-7-12 12:07:32
 **/
@Slf4j
public class FileUtil {

	public FileUtil() {
	}

	public static boolean newFolders(String path) throws Exception {
		try {
			File myFilePath = new File(path);
			return !myFilePath.exists() ? myFilePath.mkdirs() : true;
		} catch (Exception var2) {
			log.error("创建目录[" + path + "]失败。", var2);
			throw var2;
		}
	}

	public static boolean fileExist(String filePath) throws Exception {
		File myFilePath = new File(filePath);
		return myFilePath.exists();
	}

	public static boolean deleteFile(String filePath) throws Exception {
		File myFilePath = new File(filePath);
		return myFilePath.exists() ? myFilePath.delete() : false;
	}

	public static boolean newFile(String filePath, String fileContent) throws Exception {
		File myFilePath = new File(filePath);
		if (myFilePath.exists()) {
			deleteFile(filePath);
		}

		FileOutputStream outputStream = null;
		BufferedWriter writer = null;

		try {
			outputStream = new FileOutputStream(filePath);
			writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			writer.write(fileContent);
			writer.flush();
		} catch (Exception var16) {
			throw var16;
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException var15) {
					;
				}
			}

			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException var14) {
					;
				}
			}

		}

		return false;
	}
}

