<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>昴宿六</title>
<link href="${path }/css/base.css" rel="stylesheet" type="text/css" />
<link href="${path }/css/login.css" rel="stylesheet" type="text/css" />
<link href="${path }/css/alert.css" rel="stylesheet" type="text/css" />
<script>
if(location.href!=top.location.href){
	top.location.href = '${path }/admin/login.do?ref=${path}' + top.location.href.replace("${webpath}","");
}
</script>
</head>

<body>
<form class="login_form" action="#" method="post" id="theForm">
    <input id="ref" name="ref" value="${param.ref }" type="hidden"/>
    <div class="logo"></div>
    <div class="content_box">
        <div class="title">歡迎登錄    昴宿六內容管理系統</div>
        <div class="content">
            <fieldset class="well_large">
                 <div class="control_group">
                      <label class="control_label" for="username">帳戶</label>
                      <input id="account" type="text" name="account" value="" />
                 </div>
                 <div class="control_group">
                      <label class="control_label" for="pwd">密碼</label>
                      <input id="pwd" type="password" name="pwd" value="" />
                 </div>
                 <div class="control_group">
                      <label class="control_label" for="checkcode">驗證碼</label>
                      <input id="checkcode" type="text" name="checkcode" />
                      <img id="checkimg" width="65" height="36" src="${path }/admin/verifyimage" />
                      <p style="clear:both"></p>
                 </div>
                 <button class="btn btn-large yellow submit" type="submit">登錄</button>
            </fieldset>
        </div>
    </div>
</form>
<script src="${path }/js/libs/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${path }/js/alert.js" type="text/javascript"></script>
<script>
$(function(){
	$('input[type=text],input[type=password]').focus(function(){
		$(this).next('.overtip').hide();
	}).blur(function(){
		if(($.trim($(this).attr('value'))).length==0){$(this).next('.overtip').show();}
	});
	
	/** 表单提交 */
	$("#theForm").submit(function(){
		var $this = $(this),
		account = $this.find("#account"),
		password = $this.find("#pwd"),
		checkcode = $this.find("#checkcode");
		
		if("" == $.trim(account.val())){
			JSmartMsg("comm_msgBox","賬號不能為空.");
			account.focus();
			return false;
		}
		if("" == $.trim(password.val())){
			JSmartMsg("comm_msgBox","密碼不能為空.");
			password.focus();
			return false;
		}
		if("" == $.trim(checkcode.val())){
			JSmartMsg("comm_msgBox","驗證碼不能為空.");
			checkcode.focus();
			return false;
		}
		//登录验证
		JFadeWaitMsg('comm_msgBox','登錄中,請等待..');
		$.ajax({
			   type: "POST",
			   url: "${path}/admin/ajax_login",
			   cache: false,
			   async: true,//不锁住浏览器
			   data: $("#theForm").serialize(),
			   success: function(msg){
			   		if(msg.result){
			   			window.location.replace("${path}/"+msg.msg);
			   		}else{
			   			JFadeMsg('comm_msgBox',3,msg.msg);
			   			checkImg();
			   		}
			   },
			   error:function(XMLHttpRequest, textStatus){
				 //清除层  
				   JFadeMsg('comm_msgBox',3,"鏈接失敗.");
			   }
		});
		return false;
	});
});

function checkImg() {
	$("#checkcode").val("");
	var url = "${path }/admin/verifyimage" + "?a=" + Math.random();
	$("#checkimg").attr("src", url);
	return false;
}
</script>
</body>
</html>
