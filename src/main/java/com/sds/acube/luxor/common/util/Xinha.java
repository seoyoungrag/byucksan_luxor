/**
 * Xinha Editor의 ImageManager Plug-in에 필요한 모든 서버로직을 구현
 * 
 * @author  Junghyun Kim
 * @version $1.0$ $2008/8/7$
 */

package com.sds.acube.luxor.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class Xinha {
	private static final String IMG_REGEX = "(<img[^>]*>)";
	private static int count = 1000;
	private static String tempDir = LuxorConfig.getString("ATTACH.UPLOAD_TEMP");
	
	/**
	 * 본문 HTML에서 IMG 부분 인코딩
	 * 
	 * @param html
	 * @return
	 */
	public static String encode(String html) {
		return replaceImageToBase64(html);
	}
	
	
	/**
	 * 본문 HTML에서 IMG 부분 디코딩
	 * @param base64str
	 * @return
	 */
	public static String decode(String base64str, String prefixDir) {
		return replaceBase64ToImage(base64str, prefixDir);
	}

	
	/**
	 * HTML 소스에서 img 태그 부분을 찾아내서 이미지 파일을 Base64 문자열로 치환 
	 * 
	 * @param html
	 * @return
	 */
	private static String replaceImageToBase64(String html) {
		String retValue = html;
		StringBuffer sb = new StringBuffer();
		
		try {
	        int flags = Pattern.CASE_INSENSITIVE;
	        Pattern p = Pattern.compile(IMG_REGEX, flags);
	        Matcher m = p.matcher(html);
	        
	        while(m.find()) {
		        String imgTag = m.group(1);
		        
		        // 이미 base64로 되어있는거나 외부 링크 이미지는 그냥 패스
		        if(imgTag.indexOf("data:image") > -1 || imgTag.indexOf("view.do") == -1) {
		        	continue;
		        }
		        /*
		        // 외부 링크 이미지는 그냥 패스
		        else if(imgTag.indexOf("ViewImage.jsp") == -1) {
		        	continue;
		        }
		        */
		        
		        m.appendReplacement(sb, replaceIMG(imgTag));
	        }
	        
	        m.appendTail(sb);
	        retValue = sb.toString();
        } catch(Exception e) {
	        e.printStackTrace();
	        retValue = html;
        }
        
		return retValue;
	}

	
	/**
	 * Base64 문자열을 디코더해서 Image파일로 생성해 냄
	 * 
	 * @param html
	 * @return
	 */
	private static String replaceBase64ToImage(String html, String prefixDir) {
		String retValue = html;
		StringBuffer sb = new StringBuffer();
		
		try {
	        int flags = Pattern.CASE_INSENSITIVE;
	        Pattern p = Pattern.compile(IMG_REGEX, flags);
	        Matcher m = p.matcher(html);
	        
	        while(m.find()) {
		        String imgTag = m.group(1);
		        
		        // 외부 링크 이미지는 그냥 패스
		        if(imgTag.indexOf("data:image")==-1 && imgTag.indexOf("view.do")==-1) {
		        	continue;
		        }  
		        
		        m.appendReplacement(sb, replaceIMG(imgTag, prefixDir));
	        }
	        
	        m.appendTail(sb);
	        retValue = sb.toString();
        } catch(Exception e) {
	        e.printStackTrace();
	        retValue = html;
        }
        
		return retValue;
	}


	/**
	 * img src의 url부분을 base64로 인코딩해서 img태그 새로 만듬
	 * 
	 * @param img
	 * @return
	 */
	private static String replaceIMG(String img) {
		return replaceIMG(img, null);
	}
	
	
	/**
	 * <img.../> 태그 부분에서 각각 attribute부분을 추출해내서
	 * src는 base64로 인코딩하거나 또는 base64를 url로 변환해서 img태그를 새로 만듬
	 * 
	 * @param img
	 * @return
	 */
	private static String replaceIMG(String img, String prefixDir) {
		String result = "<img ";
		String src = "";
		String style = "";
		String width = "";
		String height = "";
		
		try {
			int srcIdx = img.indexOf("src=");
			int styleIdx = img.indexOf("style=");
			int widthIdx = img.indexOf("width=");
			int heightIdx = img.indexOf("height=");
			
			int srcSIdx = img.indexOf("\"", srcIdx);
			int srcEIdx = img.indexOf("\"", srcSIdx+1);
			src = img.substring(srcSIdx+1, srcEIdx);

			String tempSrc = "";
			if(prefixDir==null) {
				// url --> base64 string
		        String imgDir = getParameter(src ,"image_dir");
		        String imgFile = getParameter(src ,"image_file");
		        //imgFile = URLDecoder.decode(imgFile, "euc-kr");
		        String imagePath = tempDir + "/" +imgDir + "/" + imgFile;
		        tempSrc = "data:"+getContentType(imgFile)+";base64,"+toBase64(imagePath);
			} else {
				// base64 string --> url
		        tempSrc = saveToImageFile(src, prefixDir);
			}
			result += "src=\""+tempSrc+"\" ";
			
			int styleSIdx = img.indexOf("\"", styleIdx);
			int styleEIdx = img.indexOf("\"", styleSIdx+1);
			style = img.substring(styleSIdx+1, styleEIdx);
			result += "style=\""+style+"\" ";
			
			if(widthIdx > -1) {
				int widthSIdx = img.indexOf("\"", widthIdx);
				int widthEIdx = img.indexOf("\"", widthSIdx+1);
				width = img.substring(widthSIdx+1, widthEIdx);
				result += "width=\""+width+"\" ";
			}
			if(heightIdx > -1) {
				int heightSIdx = img.indexOf("\"", heightIdx);
				int heightEIdx = img.indexOf("\"", heightSIdx+1);
				height = img.substring(heightSIdx+1, heightEIdx);
				result += "height=\""+height+"\" ";
			}
			
			result += "/>";
		} catch (Exception e) {
			e.printStackTrace();
			result = img;
		}

		return result;
	}


	/**
	 * BASE64 문자열을 IMAGE파일로 저장 후 경로를 리턴
	 * 
	 * @param base64str
	 * @return
	 */
	private static String saveToImageFile(String base64str, String prefixDir) {
		if(!base64str.startsWith("data:")) {
			return null;
		}
		
		OutputStream out = null;
		
		// 헤더정보 문자열 추출
		String headerStr = base64str.substring(0, base64str.indexOf(","));
		// 헤더에서 이미지 확장자 구해옴
		String imageExt = headerStr.substring(headerStr.indexOf("/")+1, headerStr.indexOf(";"));
		if(imageExt.equals("jpeg")) {
			imageExt = "jpg";
		}
		
		// 헤더정보를 제외하고 BASE64 인코딩 부분만 가져옴
		base64str = base64str.substring(base64str.indexOf(",") + 1);
		
		// 이미지가 저장될 기본 경로 설정
		String tmpDir = generateID("X");
		String imgDir = prefixDir + "/" + tmpDir;
		String imgFile = generateID("I")+"."+imageExt;
		
		String imagePath = tempDir + "/" + imgDir + "/" + imgFile;
		String imageViewUrl = "/ep/image/xinha/view.do?image_dir="+imgDir+"&image_file="+imgFile;
		
		try {
			File f1 = new File(tempDir  + "/" + prefixDir);
			File f2 = new File(tempDir  + "/" + imgDir);
			
			if(!f1.exists()) {
				f1.mkdir();
			}
			if(!f2.exists()) {
				f2.mkdir();
			}
			
			byte b[] = Base64Decoder.decodeToBytes(base64str);
			out = new BufferedOutputStream(	new FileOutputStream(imagePath));
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null) out.close();
			} catch (Exception e) {				
			}
		}
		
		return imageViewUrl;	
	}
	
	
	/**
	 * 이미지 컨텐트 타입 가져옴
	 * 
	 * @param source
	 * @return
	 */
	public static String getContentType(String imgSrc) {
		String contentType = "";
		if(imgSrc.toLowerCase().lastIndexOf(".jpg") > -1 || imgSrc.toLowerCase().lastIndexOf(".jpeg") > -1) {
			contentType = "image/jpeg";
		} else if(imgSrc.toLowerCase().lastIndexOf(".gif") > -1) {
			contentType = "image/gif";
		} else if(imgSrc.toLowerCase().lastIndexOf(".png") > -1) {
			contentType = "image/png";
		} else if(imgSrc.toLowerCase().lastIndexOf(".bmp") > -1) {
			contentType = "image/bmp";
		} else {
			contentType = "unknown/unknown";
		}
		return contentType;
	}
	
	
	/**
	 * Image SRC 문자열에서 파라미터 값 추출
	 * 
	 * @param source
	 * @param attrName
	 * @return
	 */
	public static String getParameter(String source, String attrName) {
		String paramstr = source.substring(source.indexOf("?")+1);
		
		String[] param = paramstr.split("&amp;");
		String value = "";
		
		for(int i=0; i < param.length; i++) {
			String[] token = param[i].split("=");
			if(token[0].equals(attrName)) {
				value = token[1];
				break;
			}
		}
		return value;
	}

	
    /**
     * 입력된 파일을 Base64로 인코딩
     * 
     * @param Base64로 인코딩할 파일 경로
     * @return Base64 인코딩 문자열
     */
	private static String toBase64(String fileToEncode) {
		String encStr = null;
		InputStream is = null;

		try {
			is = new FileInputStream(fileToEncode);
			int bytesRead = 0;
			int chunkSize = 10000000;
			byte[] chunk = new byte[chunkSize];
			while((bytesRead = is.read(chunk)) > 0) {
				byte[] ba = new byte[bytesRead];
				for(int i = 0; i < bytesRead; i++) {
					ba[i] = chunk[i];
				}
				
				encStr = Base64Encoder.encode(ba);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) is.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return encStr;
	}
	
	
	/**
	 * 파일/디렉토리 삭제
	 * 
	 * @param path
	 */
	public static void delete(String path) {
		try {
			File f = new File(path);
			if(f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 디렉토리 하위의 모든 디렉토리 & 파일을 Recursive로 돌면서 삭제함
	 * 
	 * @param dir
	 */
	public static void deleteAll(String src) {
		try {
			File f = new File(src);
			if(!f.exists()) {
				return;
			}
			
			if(f.isFile()) {
				f.delete();
			} else {
				String[] ls = f.list();
				for(int i=0; i < ls.length; i++) {
					String srcPath = src + "/" + ls[i];
					// Recursive call
					deleteAll(srcPath);
					// Delete Parent Directory
					deleteParent(new File(srcPath));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 해당 파일/디렉토리의 상위 디렉토리를 삭제
	 * 
	 * @param f
	 */
	private static void deleteParent(File f) {
		File parent = new File(f.getParent());
		if(isEmpty(parent)) {
			parent.delete();
		}
	}
	
	
	/**
	 * 빈 디렉토리인지 검사
	 * 
	 * @param f
	 * @return
	 */
	private static boolean isEmpty(File f) {
		return f.list().length == 0;
	}
	
	
	/**
	 * 임시로 이미지 파일을 생성할때 해당 사용자가 생성하는 이미지는 
	 * 이 메소드로 생성하는 ID값을 가지는 폴더 하위에 다 생성됨
	 * 사용자마다 고유의 폴더를 생성하기위해 Session ID를 이용함 
	 * 
	 * @param session
	 * @return
	 */
	public static String getPrefixID(HttpSession session) {
		String retValue = "";
		try {
			String ssID = session.getId();
			Random random = new Random();
			int tmpI = random.nextInt(1000);
			retValue = ssID.substring(0,10) + Integer.toString(tmpI);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}
	
	
	/**
	 * UID생성
	 * 여기서 생성되는 ID는 폴더 이름으로 사용되는데
	 * getPrefixID로 생성되는 폴더의 하위에 위치함
	 * 폴더 구조가 .../getPrefixID()/generateID("X") 이런식으로 됨
	 * getPrefixID에서 이미 다른 사용자와 분리를 하기때문에
	 * 이 메소드에서는 다른 사용자와의 Duplicate를 고려할 필요없음
	 * 
	 * @param prefix
	 * @return
	 */
	public synchronized static String generateID(String prefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd", new Locale("ko"));
		Date date = new Date();
		String today = sdf.format(date);
		long currentMilli = System.currentTimeMillis();
		
		if(count > 9999) {
			count = 1000;
		}
		
		int key = count++;
		return prefix+today+currentMilli+key+"";
	}
	
}
