<%@page import="com.kh.aboo.common.code.Configcode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		${id} 비밀번호 변경링크입니다
		<a href = "<%= Configcode.DOMAIN %>/mypage/findpasswordimpl/{authPath}">완료</a>
	
</body>
</html>