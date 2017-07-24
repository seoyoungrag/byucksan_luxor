package com.sds.acube.luxor.common.util;

import java.util.*;
import java.io.*;
import java.lang.String;


// GetPrivateProfileInt
//public class PrivateProfile
public class UtilINIControl {
	private final static String CRLF = "\r\n";
	private final static String EQUAL = "=";
	private final static String LEFT_BLACKET = "[";
	private final static String RIGHT_BLACKET = "]";


	private static boolean IsFindName(String strData, String strName) {
		String Data = strData.trim();
		String Name = strName.trim();
		if (Data.compareToIgnoreCase(Name) == 0) // 찾았으면
		return true;
		else return false;
	}


	public static int writeIniInt(String strAppName, String strSection, int iWrite, String strFileName) {
		// chk iWrite
		String strData = "" + iWrite;
		return WriteINI3(strAppName, strSection, strData, strFileName);
	}


	public static int writeIniString(String strAppName, String strSection, String strData, String strFileName) {
		return WriteINI3(strAppName, strSection, strData, strFileName);
	}


	private static int WriteINI3(String strAppName, String strSection, String strData, String strFileName) {
		int iRet = 1;
		// chk parameter
		//
		try {
			RandomAccessFile File = new RandomAccessFile(strFileName, "rw");
			// long filePointer = 0;
			long length = File.length();
			int ilength = (int) length;
			byte FileData[] = new byte[ilength];
			String strLine = "";
			String strResult = "";
			boolean bFound = true;
			File.readFully(FileData);
			strLine = new String(FileData);
			// find App Name
			int iAppSt = 0, iAppEd = -1, iNextAppSt = 0;
			int iLineLen = strAppName.length();
			while (true) {
				// '[' 를 찾는다.
				iAppSt = strLine.indexOf(LEFT_BLACKET, iAppEd + 1);
				if (iAppSt < 0) {
					iAppSt = iLineLen;
					bFound = false;
					break;
				}
				if (iAppSt + iLineLen >= ilength) // App 를 못 찾은 경우
				{
					bFound = false;
					break;
				}
				// ']' 를 찾는다.
				iAppEd = strLine.indexOf(RIGHT_BLACKET, iAppSt + 1);
				if (iAppEd < 0) {
					iAppEd = iLineLen;
					bFound = false;
					break;
				}
				if (IsFindName(strLine.substring(iAppSt + LEFT_BLACKET.length(), iAppEd), strAppName)) {// AppName 을 찾은
					                                                                                    // 경우
					bFound = true;
					break;
				} else {
					if (iAppEd + iLineLen >= ilength) // App 를 못 찾은 경우
					{
						bFound = false;
						break;
					}
				}
			}
			if (bFound == false) {
				strResult = strLine;
				strResult += CRLF;
				// App 쓰기
				strResult += LEFT_BLACKET;
				strResult += strAppName;
				strResult += RIGHT_BLACKET;
				strResult += CRLF;
				// Section = Data 쓰기
				strResult += strSection;
				strResult += EQUAL;
				strResult += strData;
				strResult += CRLF;
				// write str
				File.seek(0);
				FileOutputStream writer = null;
				try {
					writer = new FileOutputStream(File.getFD());
					writer.write(strResult.getBytes());
				} catch (Exception e) {} finally {
					writer.close();
				}
				// File.writeBytes( strResult );
				File.close();
				return 1;
			}
			String strSections = "";
			// 다음 '['를 찾는다.
			iNextAppSt = strLine.indexOf(LEFT_BLACKET, iAppEd);
			if (iNextAppSt == -1) // 찾지 못한 경우
			{
				iNextAppSt = ilength;
				strSections = strLine.substring(iAppEd + 1); // Find Section Name
			} else strSections = strLine.substring(iAppEd + 1, iNextAppSt); // Find Section Name
			int iSecSt = 0, iSecEd = -1, iNextSecSt = 0;
			ilength = strSections.length();
			iLineLen = strSection.length();
			while (true) {
				// "\r\n" 를 찾는다.
				iSecSt = strSections.indexOf(CRLF, iSecEd + 1);
				if (iSecSt < 0) {
					bFound = false;
					break;
				}
				if (iSecSt + iLineLen >= ilength) // Section 을 못 찾은 경우
				{
					bFound = false;
					break;
				}
				// '=' 를 찾는다.
				iSecEd = strSections.indexOf(EQUAL, iSecSt + 1);
				if (iSecEd < 0) {
					bFound = false;
					break;
				}
				if (IsFindName(strSections.substring(iSecSt + CRLF.length(), iSecEd), strSection)) { // Section 을 찾은 경우
					bFound = true;
					break;
				} else {
					if (iSecEd + iLineLen >= ilength) // Section 을 못 찾은 경우
					{
						bFound = false;
						break;
					}
				}
			}
			if ((iSecSt < 0) || (iSecEd < 0)) // 섹션을 하나도 찾지 못한 경우
			{
				if (iSecSt < 0) // "\r\n" 을 찾지 못한 경우
				{ // 파일 끝에 AppName 만 있는 경우
					strResult = strLine.substring(0, iAppEd + iSecSt);
					strResult += CRLF;
				} else // if( iSecEd < 0 )
				{
					strResult = strLine.substring(0, iAppEd + iSecSt + CRLF.length());
				}
				// strResult += CRLF;
				strResult += strSection; // Section = Data 쓰기
				strResult += EQUAL;
				strResult += strData;
				strResult += CRLF;
				strResult += strLine.substring(iNextAppSt);
				// write str
				File.seek(0);
				File.setLength(0);
				FileOutputStream writer = new FileOutputStream(File.getFD());
				writer.write(strResult.getBytes());
				writer.close();
				// File.writeBytes( strResult );
				File.close();
				return 1;
			}
			iNextSecSt = strSections.indexOf(CRLF, iSecEd);
			if (iNextSecSt == -1) // 찾지 못한 경우
			iNextSecSt = ilength;
			if (bFound == false) {
				strResult = strLine.substring(0, iAppEd + iNextSecSt + CRLF.length() + 1);
				if (iNextSecSt == ilength) strResult += CRLF;
				strResult += strSection; // Section = Data 쓰기
				strResult += EQUAL;
				strResult += strData;
				// if( iSecSt < iSecEd )
				strResult += CRLF;
				strResult += strLine.substring(iAppEd + iNextSecSt + CRLF.length() + 1);
				// strResult += strLine.substring( iAppEd +iNextSecSt );
			} else {
				strResult = strLine.substring(0, iAppEd + iSecEd + EQUAL.length() + 1);
				strResult += strData;
				// if( iNextSecSt != ilength )
				if (iNextSecSt + CRLF.length() < ilength) strResult += strLine.substring(iAppEd + iNextSecSt + 1);
				else strResult += CRLF;
			}
			// write str
			File.seek(0);
			File.setLength(0);
			FileOutputStream writer = new FileOutputStream(File.getFD());
			writer.write(strResult.getBytes());
			writer.close();
			// File.writeBytes( strResult );
			// File.write( strResult.getBytes() );
			File.close();
		} catch (Exception eFile) {
			// String err = eFile.toString();
			iRet = -1;
		}
		return iRet;
	}


	/**
	 * 파일내용으로부터 searchname 의 Value를 찾아서 리턴한다.
	 * 
	 * @param String section 얻고자 하는 값을 section
	 * @param String searchname 얻고자 하는 값의 이름, ex)title, author
	 * @param String athlocalpath : 내용을 읽어올 Full-Path 파일명
	 * @return String
	 */
	public static String getIniString(String section, String searchname, String athlocalpath) {
		String retval = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			fis = new FileInputStream(athlocalpath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String temp = "";
			while (true) {
				temp = br.readLine();
				if (temp == null) break;
				if (temp.indexOf(section) != -1) {
					while (true) {
						temp = br.readLine();
						if (temp == null || (temp.indexOf("[") != -1 && temp.indexOf("[") == 0)) break;
						if (temp.indexOf(searchname + "=") != -1) {
							retval = temp.substring(temp.indexOf("=") + 1);
							break;
						}
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			retval = "-1";
		} finally {
			try {
				if (br != null) br.close();
			} catch (Exception e) {}
			try {
				if (fis != null) fis.close();
			} catch (Exception e) {}
			try {
				if (isr != null) isr.close();
			} catch (Exception e) {}
		}
		return retval;
	}


	/**
	 * ini파일로부터 모든 section명을 Vector형태로 return한다
	 * 
	 * @param String athlocalpath
	 * @return String[]
	 */
	public static String[] getIniSectionName(String athlocalpath) {
		Vector v = null;
		// int count = 0;
		String[] retVal = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			v = new Vector();
			fis = new FileInputStream(athlocalpath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String temp = "";
			String temp2 = "";
			do {
				temp = br.readLine();
				if (temp == null) {
					break;
				} else if (temp.indexOf("[") != -1) {
					temp2 = temp.substring(temp.indexOf("[") + 1, temp.indexOf("]"));
					v.addElement(temp2);
					// count++;
				}
			} while (true);
			retVal = new String[v.size()];
			v.copyInto(retVal);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			retVal = null;
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception e) {}
			try {
				if (isr != null) isr.close();
			} catch (Exception e) {}
			try {
				if (br != null) br.close();
			} catch (Exception e) {}
		}
		return retVal;
	}


	/**
	 * ini파일로부터 모든 section명을 Vector형태로 return한다
	 * 
	 * @param String athlocalpath
	 * @return String[]
	 */
	public static String[] getIniSectionName(String athlocalpath, boolean activecheck) {
		Vector v = null;
		// int count = 0;
		String[] retVal = null;
		String active = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			v = new Vector();
			fis = new FileInputStream(athlocalpath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String temp = "";
			String temp2 = "";
			do {
				temp = br.readLine();
				if (temp == null) {
					break;
				} else if (temp.indexOf("[") != -1) {
					temp2 = temp.substring(temp.indexOf("[") + 1, temp.indexOf("]"));
					if (activecheck) {
						active = CommonUtil.nullTrim(getIniString(temp2, "ACTIVE", athlocalpath));
						if (active.equals("Y")) {
							v.addElement(temp2);
						}
					} else {
						v.addElement(temp2);
					}
					// count++;
				}
			} while (true);
			retVal = new String[v.size()];
			v.copyInto(retVal);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			retVal = null;
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception e) {}
			try {
				if (isr != null) isr.close();
			} catch (Exception e) {}
			try {
				if (br != null) br.close();
			} catch (Exception e) {}
		}
		return retVal;
	}


	/**
	 * ini파일로부터 section의 개수를 return한다
	 * 
	 * @param String athlocalpath
	 * @return int
	 */
	public static int getIniSectionCount(String athlocalpath) {
		int retVal = -1;
		int count = 0;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(athlocalpath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String temp = "";
			do {
				temp = br.readLine();
				if (temp == null) {
					break;
				} else if (temp.indexOf("[") != -1) {
					count++;
				}
			} while (true);
			retVal = count;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			retVal = -1;
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (Exception e) {}
			try {
				if (isr != null) isr.close();
			} catch (Exception e) {}
			try {
				if (br != null) br.close();
			} catch (Exception e) {}
		}
		return retVal;
	}
}
