<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xushi</title>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<!-- preloader section -->
<div class="preloader">
  <div class="sk-spinner sk-spinner-rotating-plane"></div>
</div>
<!-- home section -->
<section id="index">
  <div class="container">
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <!-- <h1 class="wow bounceInDown rotate">Impulse</h1> -->
        <h1 class="wow bounce" style="font-family: FZShuTi;">聲音·明信片</h1>
        <a href="${path }/nologin/wsee" class="btn btn-default smoothScroll">See</a>
        <a href="${path }/platform/list" class="btn btn-default smoothScroll">Get</a>
        </div>
    </div>
  </div>
  <a href="${path }/nologin/contact" class="contactbtn btn-default smoothScroll" target="_blank" style="">Contact</a>
</section>
<%-- <div class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="icon icon-bar"></span> <span class="icon icon-bar"></span> <span class="icon icon-bar"></span> </button>
      <a href="#" class="navbar-brand">XUSHi</a></div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
       	<li><a href="#home" class="smoothScroll">HOME</a></li>
        <li><a href="#index" class="smoothScroll">INDEX</a></li>
        <c:if test="${null != _user }">
	        <li><a href="${path }/nologin/logout" class="smoothScroll">LOGOUT</a></li>
        </c:if>
        <c:if test="${null == _user }">
	        <li><a href="#register" class="smoothScroll">REGISTER</a></li>
	        <li><a href="#login" class="smoothScroll">LOGIN</a></li>
        </c:if>
        <li><a href="#contact" class="smoothScroll">CONTACT</a></li>
      </ul>
    </div>
  </div>
</div> --%>
<%-- <section id="index">
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-sm-4 title">
        <h2>Service</h2>
        <hr>
        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet. Dolore magna aliquam erat volutpat.</p>
      </div>
      <div class="col-md-8 col-sm-8">
        <div class="col-md-12 col-sm-6 bg-black"> <i class="fa fa-eye" data-href="${path }/nologin/see"></i>
          <h3>see</h3>
        </div>
        <div class="col-md-12 col-sm-6 bg-red"> <i class="fa fa-upload" data-href="${path }/platform/list"></i>
          <h3>get</h3>
        </div>
      </div>
    </div>
  </div>
</section>
<c:if test="${null == _user }">
<section id="register" class="user-action">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 title">
        <h2>注册</h2>
        <hr>
        <p></p>
      </div>
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 contact-form wow fadeInUp" data-wow-delay="0.9s">
        <form action="${path }/nologin/register" method="post">
          <input type="text" class="form-control account" placeholder="帐号" name="account">
          <input type="password" class="form-control password" placeholder="密码" name="password">
          <input type="password" class="form-control checkpassword" placeholder="再次输入密码">
          <!-- <textarea class="form-control" placeholder="Message" rows="6"></textarea> -->
          <input type="button" class="form-control submit-btn" value="注册">
        </form>
      </div>
    </div>
  </div>
</section>
<section id="login" class="user-action">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 title">
        <h2>登录</h2>
        <hr>
        <p></p>
      </div>
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 contact-form wow fadeInUp" data-wow-delay="0.9s">
        <form action="${path }/nologin/login" method="post">
          <input type="text" class="form-control account" placeholder="帐号" name="account">
          <input type="password" class="form-control password" placeholder="密码" name="password">
          <!-- <textarea class="form-control" placeholder="Message" rows="6"></textarea> -->
          <input type="button" class="form-control submit-btn" value="登录">
        </form>
      </div>
    </div>
  </div>
</section>
</c:if> --%>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(".submit-btn").click(function(){
		var _this = $(this);
		var _div = _this.parents("section");
		_div.find("p").html("");
		var account = _div.find(".account").val();
		var password = _div.find(".password").val();
		if( null == account || account.length == 0 ) {
			layer.msg("请输入帐号");
			_div.find("p").html("请输入帐号");
			return;
		}
		if(_this.parents("#register").length > 0 ) {//注册
			var checkpassword = _div.find(".checkpassword").val();
			if( password != checkpassword ) {//不相同
				layer.msg("输入密码不同");
				_div.find("p").html("输入密码不同");
				return;
			}
		}
		var _data = _div.find("form").serialize();
		var _url = _div.find("form").attr("action")
		$.ajax({
			url:_url,
			data:_data,
			success:function(data){
				if( null != data.result && data.result ) window.location.href = "index";
				else {
					layer.msg(data.msg);
					_div.find("p").html(data.msg);
				}
			}
		}); 
	});
	$(".fa-eye,.fa-upload").click(function(){
		window.location.href = $(this).data("href");
	});
</script>
</html>