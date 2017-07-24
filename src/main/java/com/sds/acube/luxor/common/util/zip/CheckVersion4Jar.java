package com.sds.acube.luxor.common.util.zip;

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.acube.luxor.common.ConstantList;

public class CheckVersion4Jar {
	private static Log logger = LogFactory.getLog(ConstantList.class);
	JarFile jarFile;
	Manifest manifest;
	Attributes attributes;
	String jarFileName;
	String version;

	public CheckVersion4Jar(String filename) {
		jarFileName = filename;
	}

	public void printVersion() {
		try {
			// jar 파일을 읽습니다.
			jarFile = new JarFile(new File(jarFileName));
			// jar 파일에서 manifest 정보를 가져옵니다.
			manifest = jarFile.getManifest();
			// manifest에서 main 정보를 가져옵니다.
			attributes = (Attributes) manifest.getMainAttributes();

			// attribute 중에서 Implementation-Version 정보를 추출한다.
			version = attributes.getValue("Implementation-Version");
			logger.debug("@@ " + version);

			/*
			 * 아래처럼 하면 manifest의 main 정보 전체를 확인할 수 있다. for (Iterator
			 * it=attributes.keySet().iterator(); it.hasNext(); ) {
			 * Attributes.Name attrName = (Attributes.Name)it.next(); String
			 * attrValue = attributes.getValue(attrName);
			 * logger.debug("## " + attrName + " : " + attrValue); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			logger.debug("Usage : ");
			logger.debug("    java printVersion [jar_file_name]");
			return;
		}

		/*
		 * if( !(new File(args[0])).exists() ) { logger.debug("Error : ");
		 * logger.debug("[" + args[0] + "] file does not exist."); return;
		 * }
		 */

		CheckVersion4Jar cv = new CheckVersion4Jar(args[0]);
		cv.printVersion();
	}
}
