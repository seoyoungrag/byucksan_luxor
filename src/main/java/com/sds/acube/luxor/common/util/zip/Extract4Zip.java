package com.sds.acube.luxor.common.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.acube.luxor.common.ConstantList;


/**
 * Zip 파일 압축 풀기
 * 
 * @author Alex, Eum
 * @since 2010.05.11
 */
public class Extract4Zip {
	private static final int BUFFER = 2048;
	private static Log logger = LogFactory.getLog(ConstantList.class);

	public static void extract(String _source, String _target) throws Exception {
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;
		ZipEntry entry = null;
		ZipFile zipFile = new ZipFile(_source);
		String destFolderName = zipFile.getName().substring(zipFile.getName().lastIndexOf(File.separator) + 1, zipFile.getName().lastIndexOf("."));
		logger.debug("_destFolderName=" + destFolderName);
		try {
			File _targetPath = new File(_target + destFolderName);
			if (!_targetPath.exists()) _targetPath.mkdirs();
			Enumeration<?> e = zipFile.entries();
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				// logger.debug("Extracting: " + entry);
				if (entry.isDirectory()) {
					logger.debug("Extracting1: " + entry);
					File dir = new File(_target + destFolderName + "/" + entry.getName());
					if (!dir.exists()) dir.mkdirs();
				}
			}
			Enumeration<?> e2 = zipFile.entries();
			while (e2.hasMoreElements()) {
				entry = (ZipEntry) e2.nextElement();
				if (!entry.isDirectory()) {
					logger.debug("Extracting2: " + entry);
					logger.debug(_target + destFolderName + "/" + entry.getName());
					is = new BufferedInputStream(zipFile.getInputStream(entry));
					int count;
					byte data[] = new byte[BUFFER];
					if (entry.getName().lastIndexOf("/") != -1) {
						logger.debug("path=" + _target + destFolderName + "/"
						    + entry.getName().substring(0, entry.getName().lastIndexOf("/")));
						File outPath = new File(_target + destFolderName + "/"
						    + entry.getName().substring(0, entry.getName().lastIndexOf("/")));
						if (!outPath.exists()) outPath.mkdirs();
					}
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
			zipFile.close();
		}
	}


	/**
	 * @param params
	 * @param destFile
	 * @throws Exception
	 */
	public static void compress(ArrayList<String> params, String destFile) throws Exception {}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Extract4Zip.extract("D:/Temp/testPortlet.zip", "D:/Temp/_target/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
