<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include/common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xushi</title>
<style type="text/css">
	#edit .container{
		height: 500px;
	}
</style>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="/include/header.jsp"></jsp:include>
<section id="edit">
  <div class="container">
  	  <input type="file" id="uploadimg" name="uploadimg" style="display: none;">
      <div class="" data-wow-delay="1.2s"><div class="upload-qrcode"><span class="voice-p">上传二维码</span></div></div>
  </div>
</section>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${path }/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	 /* $(function(){
		if( null == $(".upload-img").attr("src") || $(".upload-img").attr("src").length == 0 ) {
			$(".upload-img").attr("src","${path}/images/team1.jpg");
			$(".upload-img").parents("a").attr("href","${path}/images/team1.jpg");
		}
	}); */
	 $(".upload-qrcode").click(function(){
		$("input[name=uploadimg]").trigger("click");
	});
	$(document).on("change","#uploadimg",function(){
		var file = this.value;
		//验证文件格式
		var filepath=$(this).val(); 
	    var extStart=filepath.lastIndexOf("."); 
	    var ext=filepath.substring(extStart,filepath.length).toUpperCase(); 
	    if( $(this).attr("name") == "uploadimg" ) {//图片
	        if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){ 
	        	layer.msg("只能上传bmp png gif jpg jpeg");
		       	return;
		    }
	    }
		readURL(this);
	});
	//上传
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.readAsDataURL(input.files[0]);
	        reader.onload = function (e) {
		      	$.ajaxFileUpload({
		        	url: '${path}/api/getQrCodeString', //用于文件上传的服务器端请求地址
		            secureuri: false, //一般设置为false
		            fileElementId: $(input).attr("id"), //文件上传空间的id属性  <input type="file" id="file" name="file" />
		            dataType: 'JSON', //返回值类型 一般设置为json
		            success: function (data)  //服务器成功响应处理函数
		            {
		            	var _data = JSON.parse(data);
		            	if( _data.result ) {
		            		window.location.href = _data.ret.url;
		            	}else {
		            		layer.msg(_data.msg);
		            	}
		            },
		            error:function(data){
		            	layer.msg('上傳失敗');
		            }
		      	});
	        };
	    }else{
	    	layer.msg('請選擇文件');
	    }
	}
</script>
</html>