package com.kh.aboo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.aboo.common.code.ErrorCode;
import com.kh.aboo.common.exception.ToAlertException;

//HandlerInterceptor 구현
public class AuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// session에 userInfo 속성이 없을 경우 mypage로의 접근 막는다.
		// session에 persistInfo 속성이 없을 경우 joinImpl로의 접근을 막는다.
		HttpSession session = request.getSession();
		String[] uriArr = request.getRequestURI().split("/");
		System.out.println(session);
		if (uriArr.length > 0) {
			switch (uriArr[1]) {
			case "bdmin": // aboo 관리자
				switch (uriArr[2]) {
				case "management": //
					if (session.getAttribute("bdmin") == null)
						throw new ToAlertException(ErrorCode.AUTH04);
				case "managerinfo":
					/*
					 * if(session.getAttribute("persistinfo") == null) {
					 * request.setAttribute("alertMsg","이미 만료된 인증입니다."); request.setAttribute("url",
					 * "/member/join"); RequestDispatcher rd =
					 * request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
					 * rd.forward(request, response); return false; }
					 */
					if (session.getAttribute("bdmin") == null)
						throw new ToAlertException(ErrorCode.AUTH04);
				}
				break;

			case "admin": // 아파트 관리자
				switch (uriArr[2]) {
				case "login":
					if (session.getAttribute("admin") != null) {
						throw new ToAlertException(ErrorCode.AUTH08);
					}
					break;
				case "mypage":
					switch (uriArr[3]) {
					case "modifyinfo":
						if (session.getAttribute("admin") == null)
							throw new ToAlertException(ErrorCode.AUTH07);
					}
					break;
				case "authority":
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH07);
				case "mgmtfee": // 관리비
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH05);
				case "car": // 차량
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH05);
				case "chat": // 채팅
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH05);
				case "schedule": // 일정
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH05);
				case "vote": // 투표
					if (session.getAttribute("admin") == null)
						throw new ToAlertException(ErrorCode.AUTH05);
				}
				break;

			case "mypage": // generation
				if (session.getAttribute("generation") == null) {
					throw new ToAlertException(ErrorCode.AUTH06);
				}
				break;

			case "login":
				if (session.getAttribute("generation") != null) {
					throw new ToAlertException(ErrorCode.AUTH09);
				}
				break;
			case "board": // generation
				switch (uriArr[2]) {
				case "used":
					switch (uriArr[3]) {
					case "usedlist":
						if (session.getAttribute("generation") == null && session.getAttribute("admin") == null) {
							throw new ToAlertException(ErrorCode.AUTH06);
						}
						break;
						
					case "usedupload":
						if (session.getAttribute("generation") == null) 
							throw new ToAlertException(ErrorCode.AUTH06);
						
					case "usedcmtupload":
						if (session.getAttribute("generation") == null) 
							throw new ToAlertException(ErrorCode.AUTH06);
					}
					break;
				case "info":
					switch (uriArr[3]) {
					case "listinfo":
						if (session.getAttribute("generation") == null && session.getAttribute("admin") == null) {
							throw new ToAlertException(ErrorCode.AUTH06);
						}
						break;
						
					case "upload":
						if (session.getAttribute("generation") == null) 
							throw new ToAlertException(ErrorCode.AUTH06);
						
					case "uploadinfocmt":
						if (session.getAttribute("generation") == null) 
							throw new ToAlertException(ErrorCode.AUTH06);
					}
					break;
				}
				break;
			case "myapt":
				if (session.getAttribute("generation") == null && session.getAttribute("admin") == null) {
					throw new ToAlertException(ErrorCode.AUTH06);
				}	
				break;
			}
		}
		return true;
	}
}
