package com.sds.acube.luxor.framework.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.DeferredFileOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sds.acube.luxor.common.dao.AttachmentDAO;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.framework.config.LuxorConfig;

/**
 * Servlet implementation class UploadServelt
 */
public class AjaxUploadServlet extends HttpServlet {
	
	private AttachmentDAO attachmentDAO;
	private static final String FILE_URI_PATH = "/file/";
	private static final String LIST_URI_PATH = "/list/";
	private static final String UPLOADING_URI_PATH = "/uploading/";
	private static final String DELETE_URI_PATH = "/delete/";

	private static final String UPLOADING = "UPLOADING";

	private static final long serialVersionUID = 1L;
       
	private File repository = new File("/temp/files");
	private Properties config;
	Log log = LogFactory.getLog(AjaxUploadServlet.class);
    private static String encFrom = "";
    private static String encTo = "";


    public static String convertEncoding(String s)
    {
        return convertEncoding(s, encFrom, encTo);
    }

    public static String convertEncoding(String s, String encFrom, String encTo)
    {
        if (s == null) {
            return null;
        }

        String ret = "";
        try
        {
            if (encFrom.equalsIgnoreCase(encTo)) {
                ret = s;
            }
            else {
                ret = new String(s.getBytes(encFrom), encTo);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }

        return ret;
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		super.init();
		if(config == null) {
			config = new Properties();
			config.setProperty("script.url", "upload");
//			config.setProperty("upload.dir", "/temp/upload");
			config.setProperty("buffer.size", "10240");
			config.setProperty("param.name", "files");
//			config.setProperty("max.file.size", "10000000");
			config.setProperty("max.number.of.files", "50");
			config.setProperty("discard.aborted.uploads", "true");
		}
		repository = new File(LuxorConfig.getString("Common", "ATTACH.UPLOAD_TEMP"));
		if(!repository.exists()) {
			repository.mkdirs();
		}
	}
	
	/**
	 * .../file/[fileid]/[filename] - 파일 다운로드
	 * .../list/[모듈:bbs,schedule]/[fid] - fid에 따른 첨부 목록
	 * .../uploading/[filename] - 업로드중인 파일 정보
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.indexOf(FILE_URI_PATH) > 0) {
			if(request.getSession().getAttribute("userProfile") == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Abnormal access!");
				return;
			}
			String fileInfoString = uri.substring(uri.indexOf(FILE_URI_PATH)+FILE_URI_PATH.length());
			String[] fileInfo = fileInfoString.split("/");
			if (fileInfo == null || fileInfo.length < 2) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND,"No file information:"+fileInfoString);
			}
			BufferedInputStream fin = null;
			BufferedOutputStream fout = null;
			String serverFile = repository.getAbsolutePath() + File.separator + fileInfo[1];
			String[] savedFiles = { fileInfo[1] };
			String[] filesToSave = { serverFile };
			try {
				response.setContentType("application/octet-stream;charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; file_name="
						+ fileInfo[2] + ";");
				response.setHeader("Content-Transfer-Encoding", "binary;");
				response.setHeader("Pragma", "no-cache;");
				response.setHeader("Expires", "-1;");
				byte buf[] = new byte[2 * 1024];
				fin = new BufferedInputStream(new FileInputStream(serverFile));
				fout = new BufferedOutputStream(response.getOutputStream());
				int fread = 0;

				// 파일 읽기 및 쓰기
				while ((fread = fin.read(buf)) != -1) {
					fout.write(buf, 0, fread);
				}

			} catch (Exception e) {
				log.error("다운로드(파일명: " + fileInfo[1] + ", fid: "+savedFiles+") 오류 : "
						+ e.getMessage(), e);
			} finally {
				//sjs
				//fileStorage.disconnect();
				if(fout != null) fout.close();
				if(fin != null) fin.close();
				File sfile = new File(fileInfo[1]);
				if(sfile.exists()) {
					sfile.delete();
				}
			}
		} 
	}

	public String getFileNameWithoutPath(FileItem item) {
		String name = convertEncoding(item.getName());
		if(name.indexOf("\\") >= 0 ) { // IE returns a file name with full paths
			String[] nameParts = name.split("\\\\");
			name = nameParts[nameParts.length-1];
		}
		return name;
	}

	private String getJsonContentType(HttpServletRequest request) {
		String contentType = "text/plain;charset=UTF-8";
		String accept = request.getHeader("Accept");
		if(accept != null && accept.indexOf("application/json") >= 0) {
			contentType = "application/json;charset=UTF-8";
		}
		log.info("JSON response contentType: "+contentType);
		return contentType;
	}

	/**
	 * 업로드한 파일 처리 - 파일을 저장하고 결과를 json 으로 돌려줌
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		log.info(":::::::AJAXUPLOAD SERVELT::::::doPost:" +uri);
		if(uri.equals("/ep/servlet/upload/save")){
			String jsonFiles = UtilRequest.getString(request,"filesInfo");
			log.info("#################################### jsonFiles before encoding : "+jsonFiles);
			jsonFiles = convertEncoding(jsonFiles);
			log.info("#################################### jsonFiles after encoding : "+jsonFiles);

			String boardid = UtilRequest.getString(request,"boardid");
			String jndiname = UtilRequest.getString(request,"jndiname");
			String fid = UtilRequest.getString(request,"fid");
			String sdelfiles = UtilRequest.getString(request,"delfiles");
			List<String> delfiles = Arrays.asList(sdelfiles.split(","));
			
			log.info(":::::::AJAXUPLOAD SERVELT::::::boardid:" +boardid);
			log.info(":::::::AJAXUPLOAD SERVELT::::::jndiname:" +jndiname);
			log.info(":::::::AJAXUPLOAD SERVELT::::::fid:" +fid);
			log.info(":::::::AJAXUPLOAD SERVELT::::::delfiles:" +sdelfiles);
			log.info(":::::::AJAXUPLOAD SERVELT::::::filesInfo:" +jsonFiles);
			
			
			if(delfiles.size() == 1 && (delfiles.get(0) == null || delfiles.get(0).length() == 0)) {
				delfiles = Collections.emptyList();
			}
			List<Map> files = parseJson( jsonFiles);
			log.info("PUT parameter filesInfo : "+jsonFiles+", elements number:"+files.size());
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				//fid = deleteAndSaveFiles(jndiname, files, delfiles, fid, boardid);
				result.put("fid", fid);
				result.put("files", files);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.put("error", e.getMessage());
				log.error(e.getMessage(),e);
			}
			JSONObject jobj = JSONObject.fromObject(result);
			response.setContentType(getJsonContentType(request));
			response.setHeader("Pragma","no-cache");
			response.setHeader("Cache-Control","private, no-cache");
			response.setHeader("Content-Disposition","inline; filename=\"files.json\"");
			response.getWriter().write(jobj.toString());
			response.flushBuffer();
			
		} else if(uri.indexOf(DELETE_URI_PATH) > 0){ 
			String[] split = uri.split("/");
			File f = new File(repository,split[split.length-1]);
			response.setContentType(getJsonContentType(request));
			response.setHeader("Pragma","no-cache");
			response.setHeader("Cache-Control","private, no-cache");
			response.setHeader("Content-Disposition","inline; filename=\"files.json\"");
			PrintWriter writer = response.getWriter();
			if(f.exists()) {
				writer.print(f.delete());
			}
			writer.close();
		} else {
			if (ServletFileUpload.isMultipartContent(request)) {
				// Create a factory for disk-based file items
				int bufferSize = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;
				String bufferSizeStr = config.getProperty("buffer.size");
				try {
					bufferSize = Integer.parseInt(bufferSizeStr);
				} catch(Exception e) {
					log.info("buffer.size("+bufferSizeStr+") in jQueryUpload.properties is invalid.", e);
				}
				FileItemFactory factory = new DiskFileItemFactory(bufferSize, repository);
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
	
				// Parse the request
				try {
					List /* FileItem */items = upload.parseRequest(request);
					request.getSession().setAttribute(UPLOADING, items);
					List jsons = new ArrayList();
					// Process the uploaded items
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
					    FileItem item = (FileItem) iter.next();
	
					    if (item.isFormField()) {
					        processFormField(item);
					    } else {
					        jsons.add(processUploadedFile(item, getFileURL(request, item)));
					    }
					}
					response.setContentType(getJsonContentType(request));
					PrintWriter writer = response.getWriter();
	//
	//				if(jsons.size() == 1) {
	//					writer.write(jsons.get(0).toString());
	//				} else if(jsons.size() > 1) {
						writer.write('[');
						String sep = "";
						for (Iterator iterator = jsons.iterator(); iterator
								.hasNext();) {
							String json = (String) iterator.next();
							writer.write(sep);
							writer.write(json);
							sep = ",";
						}
						writer.write(']');
						
	//				}
					writer.close();
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					request.getSession().removeAttribute(UPLOADING);
				}
			}
		}
	}
	
	

	private String getFileURL(HttpServletRequest request, FileItem item) {
		
		StringBuffer url = request.getRequestURL();
		if(url.indexOf(UPLOADING_URI_PATH) > 1) {
			url.delete(url.indexOf(UPLOADING_URI_PATH), url.length());
		}
		String name = getFileNameWithoutPath(item);

		url.append(UPLOADING_URI_PATH).append(name);
		return url.toString();
	}

	private void processFormField(FileItem item) {
		log.warn("Normal form - skip.");
		// Process a regular form field
		if (item.isFormField()) {
		    String name = item.getFieldName();
		    String value = item.getString();
		    log.warn("  - name:"+name+", value:"+value);
		}
		
	}

	private String processUploadedFile(FileItem item, String servletUrl) throws IOException {
		// Process a file upload
		log.info(":::::::AJAXUPLOAD SERVELT::::::processUploadedFile");
		if (!item.isFormField()) {
		    File uploadedFile = getUploadFile(item);
		    try {
				item.write(uploadedFile);
				log.info("written file length: "+uploadedFile.length());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getJson(item, servletUrl, uploadedFile);
		}
		return "";
	}

	private File getUploadFile(FileItem item) throws IOException{
		log.info(":::::::AJAXUPLOAD SERVELT::::::getUploadFile");
		File uploadedFile = null;
//		if(item.getOutputStream() instanceof DeferredFileOutputStream) {
//			DeferredFileOutputStream dfout = (DeferredFileOutputStream)item.getOutputStream();
//			uploadedFile = dfout.getFile();
//		} else {
			String fileName = item.getName();
			log.info("#################################### fileName before encoding : "+fileName);
			fileName = convertEncoding(fileName);
			log.info("#################################### fileName after encoding : "+fileName);
			String extention = "";
			int dotIndex = fileName.lastIndexOf(".");
			if(dotIndex > 1) {
				extention = fileName.substring(dotIndex);
			}
			uploadedFile = new File(repository, UUID.randomUUID().toString()+extention);
			log.info("uploaded file saved temporarily in "+uploadedFile.getAbsolutePath());
//		}
		return uploadedFile;
	}
	
	private File getTempFile(FileItem item) throws IOException {
		File uploadedFile = null;
		if(item.getOutputStream() instanceof DeferredFileOutputStream) {
			DeferredFileOutputStream dfout = (DeferredFileOutputStream)item.getOutputStream();
			uploadedFile = dfout.getFile();
		}
		return uploadedFile;
	}

	public String getJson(FileItem item, String fileUrl, File uploadedFile) throws IOException {
		//name, size, type, url, error, delete_url, delete_type
		StringBuffer json = new StringBuffer();
		String name = item.getName();
		log.info("#################################### name before encoding : "+name);
		name = convertEncoding(name);
		log.info("#################################### name after encoding : "+name);
		if(name == null) {
			log.warn("file name is null!");
			return "{}";
		}

		name = getFileNameWithoutPath(item);// IE returns a file name with full paths
		String tempFileName = uploadedFile == null ? getTempFile(item).getName() : uploadedFile.getName();
		StringBuffer deleteUrl = new StringBuffer(fileUrl);
		deleteUrl.replace(deleteUrl.lastIndexOf("/"), deleteUrl.length(), DELETE_URI_PATH+tempFileName);
		json.append("{\"name\":\"").append(name)
		.append("\",\"type\":\"").append(item.getContentType())
		.append("\",\"name_temp\":\"").append(tempFileName)
		.append("\",\"size\":\"").append(item.getSize())
		.append("\",\"url\":\"").append(fileUrl)
		.append("\",\"error\":\"").append("")
		.append("\",\"delete_url\":\"").append(deleteUrl.toString())
		.append("\",\"delete_type\":\"").append("POST")
		.append("\"}");
		String result = json.toString();
		log.info(result);
		return result;
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] split = request.getRequestURI().split("/");
		File f = new File(repository,split[split.length-1]);
		response.setContentType(getJsonContentType(request));
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","private, no-cache");
		response.setHeader("Content-Disposition","inline; filename=\"files.json\"");
		PrintWriter writer = response.getWriter();
		if(f.exists()) {
			writer.print(f.delete());
		}
		writer.close();
	}
	
	static List<Map> parseJson(String jsonString) {
		List<Map> list = new ArrayList<Map>();
		Map m = new HashMap();
		Map[] ma = {m};
		JSONArray json = JSONArray.fromObject(jsonString);
		ma = (Map[])json.toArray(ma);
		list = Arrays.asList(ma);
		return list;
	}
	

}
