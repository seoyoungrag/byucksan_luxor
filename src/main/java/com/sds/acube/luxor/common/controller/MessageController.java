package com.sds.acube.luxor.common.controller;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import com.sds.acube.luxor.common.service.MessageService;
import com.sds.acube.luxor.common.util.CommonUtil;
import com.sds.acube.luxor.common.util.UtilRequest;
import com.sds.acube.luxor.common.vo.MessageVO;
import com.sds.acube.luxor.framework.controller.BaseController;

public class MessageController extends BaseController {
	private MessageService messageService;
	
	public void setMessageService(MessageService messageService) {
    	this.messageService = messageService;
    }


	public ModelAndView getMessageById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageVO messageVO = new MessageVO();
		bind(request, messageVO);

		ModelAndView mav = new ModelAndView();
		
		MessageVO[] messages = messageService.getMessageById(messageVO);
		mav.addObject("jsonResult", messages);
		return mav;
	}


	public ModelAndView getMessageByIdLang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageVO messageVO = new MessageVO();
		bind(request, messageVO);
	
		ModelAndView mav = new ModelAndView();
		
		MessageVO message = messageService.getMessageByIdLang(messageVO);
		mav.addObject("jsonResult", message);
		return mav;
	}


	public ModelAndView isDuplicate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageVO messageVO = new MessageVO();
		bind(request, messageVO);
		
		boolean result = false;
		
		String nameAll = messageVO.getMessageNameAll();
		
		String[] token = nameAll.split("\\|");
		for(String part:token) {
			if(CommonUtil.isNullOrEmpty(part)) {
				continue;
			}
			
			messageVO.setLangType(part.split("\\^")[0]);
			messageVO.setMessageName(part.split("\\^")[1]);
			
			result = messageService.isDuplicate(messageVO);
			
			//다국어 메세지중 하나만 변경되었어도 중복되지 않는거라 판단.
			if(result == false) {
				break;
			}
		}
		
		response.getOutputStream().println(result);
		return null;
	}
	
	public ModelAndView getMessageAajx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext sc = request.getSession().getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MessageSource messageSource = (MessageSource)ctx.getBean("messageSource");
		Locale langType = (Locale)request.getSession().getAttribute("LANG_TYPE");
		String msg1 = messageSource.getMessage(UtilRequest.getString(request, "code"), null, langType);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonResult", msg1);
		
		return mav;
	}

}
