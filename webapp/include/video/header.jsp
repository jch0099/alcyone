<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<header class="header">
 	<a href="${path }/video/index" class="logo"><img alt="昴宿六" src="${path }/images/logo.png"> </a>
 	<c:if test="${_video_user==null }">
 		<a href="reg" class="signup-loader" style="margin-right: 200px;margin-top: 22px;float: right;">注册</a>
 		<a class="btn btn-login" href="login" style="margin-right: 30px;margin-top: 15px;float: right;">登录</a>
 	</c:if>
 	<c:if test="${_video_user!=null }">
 		<a href="logout" class="signup-loader" style="margin-right: 200px;margin-top: 22px;float: right;">登出</a>
 		<a href="changepwd" class="" style="margin-right: 30px;margin-top: 22px;float: right;">修改密码</a>
 	</c:if>
</header>