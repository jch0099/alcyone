<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/include/video/common.jsp"></jsp:include>
<link rel="stylesheet" href="${path }/css/video/reg.css">
<!--[if lt IE 9]><script src="${path}/skin/ecms082/js/html5.min.js"></script><![endif]-->
</head>
<body class="home blog">
<jsp:include page="/include/video/header.jsp"></jsp:include>
<section class="container">
	<div class="OLogin">
    <div id="login" class="item">
        <a class="head" href="login">登录</a>
            </div>
    <div id="reg" class="item focus">
        <a class="head" href="#">注册</a>
          <div class="content">
            <form id="regForm" action2="server.php?s=112&amp;p=42&amp;action=reg&amp;url=" method="post" action="server.php?s=112&amp;p=42&amp;action=reg&amp;url=" form_init="true" class=" OBase Form OBase Form">
                <input name="sharer" type="hidden" value="">
                <div class="table"><div class="cell">用户名</div><div class="cell"><input id="account" name="account" required="required" placeholder="输入用户名"></div><div class="cell verify"></div></div>                                                                                                    
                 <div class="table"><div class="cell">密码</div><div class="cell"><input id="pwd" minlength="6" name="password" required="required" type="password" placeholder="输入6到16位密码"></div><div class="cell verify"></div></div>
                <div class="table"><div class="cell">确认密码</div><div class="cell"><input id="pwd2" required="required" type="password" placeholder="确认密码"></div><div class="cell verify"></div></div>
                <div><input type="checkbox" checked="checked" class="input ok" tips="请同意用户协议">同意<a class="agreement_btn">《用户协议》</a></div>
                <input class="submit train_background_color train_font_color" type="button" value="注册">
            </form>
        </div>
     </div>
    <div id="agreement" class="hide"><h3 class="title">用户协议</h3><div class="padding"><p style="margin-top:5px;margin-right:0;margin-bottom:5px;margin-left: 0"><span style="font-size: 14px;font-family: 微软雅黑, sans-serif">用户协议</span></p></div></div></div>
</section>
<jsp:include page="/include/video/footer.jsp"></jsp:include>
<%-- <script type='text/javascript' src='${path}/skin/ecms082/js/loader.js?ver=1.3'></script> --%>
<script type="text/javascript">
	$("input.submit.train_font_color").click(function(){
		var account = $("#account").val();
		var pwd = $("#pwd").val();
		var pwd2 = $("#pwd2").val();
		if( account.length == 0 || pwd.length == 0 || pwd2.length == 0 ) {
			layer.msg("请填写帐号/密码/确认密码");
			return;
		}
		if( pwd != pwd2 ) {
			layer.msg("密码 与 确认密码 不同");
			return;
		}
		$.ajax({
			url:"ajax_reg",
			data:{"account":account,"password":pwd},
			success:function(data){
				if( data.result ) {
					window.location.href = "index";
				}else{
					layer.msg(data.msg);
				}
			}
		});
	});
</script>
</body>
</html>