package net.dxs.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import net.dxs.pojo.DxsJSONResult;

@ControllerAdvice
public class DxsExceptionHandler {

	public static final String DXS_ERROR_VIEW = "error";

//	@ExceptionHandler(value = Exception.class)
//	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
//
//		e.printStackTrace();
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", e);
//		mav.addObject("url", reqest.getRequestURL());
//		mav.setViewName(DXS_ERROR_VIEW);
//		return mav;
//	}

	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {

		e.printStackTrace();

		if (isAjax(reqest)) {
			return DxsJSONResult.errorException(e.getMessage());
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", reqest.getRequestURL());
			mav.setViewName(DXS_ERROR_VIEW);
			return mav;
		}
	}

	/**
	 * 判断是否是ajax请求
	 * 
	 * @param httpRequest
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}
