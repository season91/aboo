package com.kh.aboo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;

//HandlerInterceptor 구현
public class AuthInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// session에 userInfo 속성이 없을 경우 mypage로의 접근 막는다.
		// session에 persistInfo 속성이 없을 경우 joinImpl로의 접근을 막는다.
		HttpSession session = request.getSession();
		String[] uriArr = request.getRequestURI().split("/");
		System.out.println(session);
		if(uriArr.length > 0) {
			switch (uriArr[1]) {
			case "bdmin": //aboo 관리자
				switch (uriArr[2]) {
				case "apartment": //
					if(session.getAttribute("bdmin") == null)
					throw new ToAlertException(ErrorCode.AUTH04);			
				case "managerinfo":
					/*
					 * if(session.getAttribute("persistinfo") == null) {
					 * request.setAttribute("alertMsg","이미 만료된 인증입니다."); request.setAttribute("url",
					 * "/member/join"); RequestDispatcher rd =
					 * request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
					 * rd.forward(request, response); return false; }
					 */
					if(session.getAttribute("bdmin") == null)
					throw new ToAlertException(ErrorCode.AUTH04);
				}
				break;
			}
		}
		return true;
	}
}
