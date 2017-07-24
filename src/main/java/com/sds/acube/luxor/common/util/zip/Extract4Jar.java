/*
 * @(#) Extract4Jar.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.acube.luxor.common.ConstantList;


/**
 * Jar 파일 압축 풀기
 * 
 * @author Alex, Eum
 * @version $Revision: 1.1.6.2 $ $Date: 2011/04/21 00:13:39 $
 */
public class Extract4Jar {
	private static final int BUFFER = 2048;
	private static Log logger = LogFactory.getLog(ConstantList.class);

	/**
	 * @param _source
	 * @param _target
	 * @throws Exception
	 */
	public static void extract(String _source, String _target) throws Exception {
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;
		JarEntry entry = null;
		JarFile jarFile = new JarFile(_source);
		String destFolderName = jarFile.getName().substring(jarFile.getName().lastIndexOf(File.separator) + 1, jarFile.getName().lastIndexOf("."));
		logger.debug("destFolderName=" + destFolderName);
		try {
			File _targetPath = new File(_target + destFolderName);
			if (!_targetPath.exists()) _targetPath.mkdirs();
			Enumeration<?> e = jarFile.entries();
			while (e.hasMoreElements()) {
				entry = (JarEntry) e.nextElement();
				if (entry.isDirectory()) {
					logger.debug("Extracting: " + entry);
					File dir = new File(_target + destFolderName + "/" + entry.getName());
					if (!dir.exists()) dir.mkdirs();
				}
			}
			Enumeration<?> e2 = jarFile.entries();
			while (e2.hasMoreElements()) {
				entry = (JarEntry) e2.nextElement();
				if (!entry.isDirectory()) {
					logger.debug("Extracting: " + entry);
					logger.debug(_target + destFolderName + "/" + entry.getName());
					is = new BufferedInputStream(jarFile.getInputStream(entry));
					int count;
					byte data[] = new byte[BUFFER];
					FileOutputStream fos = new FileOutputStream(_target + destFolderName + "/" + entry.getName());
					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (dest != null) dest.close();
			} catch (Exception e) {}
			try {
				if (is != null) is.close();
			} catch (Exception e) {}
			jarFile.close();
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Extract4Jar.extract("D:/Temp/ibatis-2.3.0.677.jar", "D:/Temp/_target/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
