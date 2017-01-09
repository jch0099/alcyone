<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xushi</title>
<style type="text/css">
	.register-btn{
		color: #FFFFFF;
		float: right;
	}
</style>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- home section -->
<section id="login" class="user-action">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 title">
        <h2>分享</h2>
        <p></p>
      </div>
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 contact-form wow fadeInUp" data-wow-delay="0.9s">
        <form action="${path }/admin/ajax_share" method="post">
          <input type="text" class="form-control account" placeholder="用戶名" name="account">
          <input type="text" class="form-control count" placeholder="數量" name="count">
          <!-- <textarea class="form-control" placeholder="Message" rows="6"></textarea> -->
       	  <input type="button" class="form-control submit-btn" value="確定">
       	  <!-- <a href="register" class="register-btn">確定</a> -->
        </form>
      </div>
    </div>
  </div>
</section>
<jsp:include page="/include/footer.jsp"></jsp:include> 
</body>
<script type="text/javascript">
	$(".submit-btn").click(function(){
		var _this = $(this);
		var _div = _this.parents("section");
		_div.find("p").html("");
		var account = _div.find(".account").val();
		var count = _div.find(".count").val();
		if( isNaN(count) ) {
			layer.msg("數量只能輸入數字");
			_div.find("p").html("數量只能輸入數字");
			return;
		}
		if( null == account || account.length == 0 ) {
			layer.msg("请输入帐号");
			_div.find("p").html("请输入帐号");
			return;
		}
		var _data = _div.find("form").serialize();
		var _url = _div.find("form").attr("action")
		$.ajax({
			url:_url,
			data:_data,
			success:function(data){
				if( null != data.result && data.result ) layer.msg(data.msg);
				else {
					layer.msg(data.msg);
					_div.find("p").html(data.msg);
				}
			}
		}); 
	});
	$(".register-btn").click(function(){
		window.location.href = "register";
	});
</script>
</html>