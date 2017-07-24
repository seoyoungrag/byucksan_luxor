/*
 * @(#) FileUploadUtil.java 2010. 5. 11.
 * Copyright (c) 2010 Samsung SDS Co., Ltd. All Rights Reserved.
 */
package com.sds.acube.luxor.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.acube.luxor.framework.config.LuxorConfig;

/**
 * @author Alex, Eum
 * @version $Revision: 1.1.6.2 $ $Date: 2011/04/21 00:13:39 $
 */
public class FileUploadUtil {

	private static Log logger = LogFactory.getLog(FileUploadUtil.class);

	private static String UPLOAD_DIR = LuxorConfig.getString("COMMON", "ATTACH.UPLOAD_TEMP");

	/**
	 * MultipartForm 형태로 업로드된 파일을 서버에 저장하고, 파라메터를 파싱하여 HashMap에 저장하여 반환한다. 1.
	 * request의 일반 데이터 : param(HashMa)에 저장한다. 2. request의 파일 데이터 : 업로드된 파일을 서버에
	 * 저장한다. 3. 서버에 저장한 파일명(절대경로)을 fileName이란 이름으로 1의 param 추가한다. 4. return
	 * param
	 */
	public static HashMap<String, String> fileUploadAndParseParam(HttpServletRequest request) throws Exception {

		String serverFileName = "";
		HashMap<String, String> param = new HashMap<String, String>();

		// 현재 method에 해당하는 log
		logger.info("FileUploadUtil : fileUploadAndParseParam");

		// request가 multipart/form-data 형식인지 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		try {
			// 기타 파라메터 바인딩
			if (isMultipart) {
				logger.debug("file upload process starting...");
				int yourMaxMemorySize = 1024 * 10; // threshold 값 설정
				long yourMaxRequestSize = 1024 * 1024 * 100; // 업로드 최대 사이즈 설정
																// (100M)

				File yourTempDirectory = new File(UPLOAD_DIR);

				// fileupload 컴포넌트를 사용하기 위한 초기화
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(yourTempDirectory);

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize); // 임시 업로드 디렉토리 설정
				upload.setHeaderEncoding("EUC_KR"); // 인코딩 설정

				/**
				 * 업로드 진행 상태 출력 (Watching progress)
				 */
				ProgressListener progressListener = new ProgressListener() {

					private long megaBytes = -1;

					public void update(long pBytesRead, long pContentLength, int pItems) {
						long mBytes = pBytesRead / 1000000;
						if (megaBytes == mBytes) {
							return;
						}
						megaBytes = mBytes;
						logger.debug("We are currently reading item " + pItems);
						if (pContentLength == -1) {
							logger.debug("So far, " + pBytesRead + " bytes have been read.");
						} else {
							logger.debug("So far, " + pBytesRead + " of " + pContentLength + " bytes have been read.");
						}
					}
				};
				upload.setProgressListener(progressListener); // 진행상태 리스너 추가

				List<?> items = upload.parseRequest(request);

				String fieldName = null;
				String fieldValue = null;
				String fileName = null;
				String contentType = null;
				long sizeInBytes = 0;

				// upload된 아이템들을 처리(실제로는 사용되지 않음)
				Iterator<?> iter = (Iterator<?>) items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					// 일반데이터 or 파일데이터
					if (item.isFormField()) {
						logger.debug("item is form field.");
						fieldName = item.getFieldName();
						fieldValue = item.getString();
						logger.debug("fieldName=" + fieldName);
						logger.debug("value=" + fieldValue);
						param.put(fieldName, fieldValue);
					} else {
						logger.debug("item is uploaded file.");
						fieldName = item.getFieldName();
						fileName = item.getName();
						contentType = item.getContentType();
						sizeInBytes = item.getSize();

						logger.debug("fileName=" + fileName);
						logger.debug("fieldName=" + fieldName);
						logger.debug("contentType=" + contentType);
						logger.debug("sizeInBytes=" + sizeInBytes);

						// 절대경로명
						String fullFileName = item.getName();
						param.put("fullFileName", fullFileName);

						// 절대경로에서 파일명만 축출
						int pos = fullFileName.lastIndexOf("\\");
						fileName = fullFileName.substring(pos + 1);
						serverFileName = UPLOAD_DIR + "\\" + fileName;

						param.put("fileName", serverFileName);

						// 서버에 파일 저장
						if (!fileName.equals("")) {

							File uploadedFile = new File(serverFileName);

							// 저장 디렉토리 존재확인
							File dir = uploadedFile.getParentFile();
							if (!dir.exists()) {
								dir.mkdirs();
							}
							// 동일명 파일 존재시 덮어씀
							item.write(uploadedFile);

							logger.info("File upload success");

						}
					}
				}
			} else {
				logger.debug("is not multipart form...");
			}
		} catch (Exception e) {
			// logger.error(e.toString(), e);
			throw e;
		}
		return param;
	}

	/**
	 * @param sourcelocation
	 * @param targetdirectory
	 * @throws IOException
	 */
	public static void copyDirectory(File sourcelocation, File targetdirectory) throws IOException {
		// 디렉토리인 경우
		if (sourcelocation.isDirectory()) {
			// 복사될 Directory가 없으면 만듭니다.
			if (!targetdirectory.exists()) {
				targetdirectory.mkdir();
			}

			String[] children = sourcelocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourcelocation, children[i]), new File(targetdirectory, children[i]));
			}
		} else {
			InputStream in = null;
			OutputStream out = null;
			
			try {
	            // 파일 인 경우
	            in = new FileInputStream(sourcelocation);
	            out = new FileOutputStream(targetdirectory);

	            // Copy the bits from instream to outstream
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = in.read(buf)) > 0) {
	            	out.write(buf, 0, len);
	            }
            } catch (Exception e) {
            } finally {
    	        if(in!=null) in.close();
    	        if(out!=null) out.close();
            }
		}
	}
	
	/**
	 * @param srcFile
	 * @param targetPath
	 * @throws IOException
	 */
	public static void copyFile(String srcFile, String targetPath) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		
		try {
			// 파일 인 경우
			in = new FileInputStream(srcFile);
			File f = new File(srcFile);
			out = new FileOutputStream(targetPath + "/" + f.getName());
			
	        // Copy the bits from instream to outstream
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	        	out.write(buf, 0, len);
	        }
        } catch (Exception e) {
        } finally {
	        if(in!=null) in.close();
	        if(out!=null) out.close();
        }
	}
}
