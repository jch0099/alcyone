<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<header class="header">
 	<a href="${path }/video/index" class="logo"><img alt="昴宿六" src="${path }/images/logo.png"> </a>
 	<c:if test="${_video_user==null }">
 		<%-- <a href="${path }/video/reg" class="signup-loader" style="margin-right: 200px;margin-top: 22px;float: right;">注册</a> --%>
 		<a class="btn btn-login" href="${path }/video/login" style="margin-right: 120px;margin-top: 15px;float: right;">登录</a>
 	</c:if>
 	<c:if test="${_video_user!=null }">
 		<%-- <a href="${path }/video/logout" class="signup-loader" style="margin-right: 200px;margin-top: 22px;float: right;">登出</a> --%>
 		<!-- <a href="changepwd" class="" style="margin-right: 30px;margin-top: 22px;float: right;">修改密码</a> -->
 		<div class="user-info">
	 		<img data-toggle="dropdown" aria-expanded="true" src="${path }/images/user-head.png" id="user-info-img" style="margin-top: 8px;float: right;cursor: pointer;width: 50px;height: 50px;"/>
	 		<!-- <i class="fa fa-user" style="margin-right: 30px;margin-top: 22px;float: right;cursor: pointer;font-size: 20px;"></i> -->
	 		<ul class="dropdown-menu">
	           <div class="info">
		           <div id="nickname">${_video_user.account }</div>
		           <div id="level">${_video_user.type==1?"超级管理员":(_video_user.type==2?"普通会员":"vip会员") }</div>
		           <c:if test="${_video_user.type==3 }">
		           ${_video_user.end_date }
		           </c:if>
	           </div>
	           <li><a href="${path }/video/changepwd">修改密码</a></li>
	           <li><a href="${path }/video/pay/index">购买会员</a></li>
	           <li><a href="${path }/video/logout">退出系统</a></li>
	       </ul>
 		</div>
 	</c:if>
 	<script type="text/javascript">
 		/* $("#user-info").click(function(){
 			$(".header ul").toggle();
 		}); */
 	</script>
</header>