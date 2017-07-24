<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="com.sds.acube.luxor.portal.vo.*"%>
<%@ page import="com.sds.acube.luxor.common.vo.*"%>
<%@ page import="com.sds.acube.luxor.common.util.*"%>
<%@ page import="com.sds.acube.luxor.framework.config.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.util.List"%>
<%@ page import="jxl.*"%>
<%@ page import="jxl.write.*"%>
<%
	String todayDate = CommonUtil.getNow();
	List<LoginHistoryVO> list = (List<LoginHistoryVO>) request.getAttribute("list");
	String arg1 = (String) request.getAttribute("arg1");
	String arg2 = (String) request.getAttribute("arg2");
	String arg3 = (String) request.getAttribute("arg3");
	String arg4 = (String) request.getAttribute("arg4");
	String arg5 = (String) request.getAttribute("arg5");
	String arg6 = (String) request.getAttribute("arg6");
	WritableWorkbook workbook = null;
	try {
	//System.out.println(":::::getLoginHistoryMkFile:::list.size()::" + list.size());
	if (list.size() > 30000) {
%><script>alert("Please reduce under 30000 rows");</script>
<%
	}
	/*================== Start DownLoad ==========================================*/
	String sFileName = "LOGIN_HISTORY_" + todayDate;
	//sFileName = new String(sFileName.getBytes(cm_char_set),"8859_1");
	response.reset();
	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition", "attachment;filename=" + sFileName + ".xls;");
	response.setHeader("Content-Description", "JSP Generated Data");
	
	out.clear();
	out= pageContext.pushBody();
	
	workbook = Workbook.createWorkbook(response.getOutputStream());
	WritableSheet sheet = workbook.createSheet("Sheet1", 0);
	WritableFont arial11ptBold = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD);
	WritableFont arial10pt = new WritableFont(WritableFont.ARIAL, 10);
	WritableCellFormat titleRowStyle = new WritableCellFormat(arial11ptBold);
	titleRowStyle.setBackground(Colour.YELLOW);
	titleRowStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	titleRowStyle.setWrap(true);
	titleRowStyle.setAlignment(Alignment.CENTRE);
	titleRowStyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	WritableCellFormat rowStyleLeft = new WritableCellFormat(arial10pt);
	WritableCellFormat rowStyleRight = new WritableCellFormat(arial10pt);
	WritableCellFormat rowStyleCenter = new WritableCellFormat(arial10pt);
	rowStyleLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
	rowStyleLeft.setWrap(true);
	rowStyleLeft.setAlignment(Alignment.LEFT);
	rowStyleLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
	rowStyleRight.setBorder(Border.ALL, BorderLineStyle.THIN);
	rowStyleRight.setWrap(true);
	rowStyleRight.setAlignment(Alignment.RIGHT);
	rowStyleRight.setVerticalAlignment(VerticalAlignment.CENTRE);
	rowStyleCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
	rowStyleCenter.setWrap(true);
	rowStyleCenter.setAlignment(Alignment.CENTRE);
	rowStyleCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
	int idx = 0;
	sheet.setColumnView(idx++, 30);
	sheet.setColumnView(idx++, 20);
	sheet.setColumnView(idx++, 20);
	sheet.setColumnView(idx++, 20);
	sheet.setColumnView(idx++, 20);
	sheet.setColumnView(idx++, 20);
	//sheet.setRowView(0, 500);
	idx = 0;
	Label label = null;
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg1"), titleRowStyle);
	sheet.addCell(label);
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg2"), titleRowStyle);
	sheet.addCell(label);
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg3"), titleRowStyle);
	sheet.addCell(label);
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg4"), titleRowStyle);
	sheet.addCell(label);
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg5"), titleRowStyle);
	sheet.addCell(label);
	label = new Label(idx++, 0, UtilRequest.getString(request, "arg6"), titleRowStyle);
	sheet.addCell(label);
	for (int i = 0; i < list.size(); i++) {
		idx = 0;
		label = new Label(idx++, i + 1, list.get(i).getLoginId(), rowStyleRight);
		sheet.addCell(label);
		label = new Label(idx++, i + 1, list.get(i).getUserName(), rowStyleCenter);
		sheet.addCell(label);
		label = new Label(idx++, i + 1, list.get(i).getDptName(), rowStyleRight);
		sheet.addCell(label);
		label = new Label(idx++, i + 1, list.get(i).getLoginIp(), rowStyleRight);
		sheet.addCell(label);
		label = new Label(idx++, i + 1, list.get(i).getLoginTime2String(), rowStyleRight);
		sheet.addCell(label);
		label = new Label(idx++, i + 1, list.get(i).getLogOutTime2String() + "", rowStyleRight);
		sheet.addCell(label);
	}
		workbook.write();
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		if(workbook!=null) {
		workbook.close();		
		}
	}
%>