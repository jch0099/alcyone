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
	.nivo-lightbox-nav{
		display: none !important;
	}
</style>
<script src="http://gosspublic.alicdn.com/aliyun-oss-sdk-4.3.0.min.js"></script>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<div class="Mask-layer" style=" width:100%; height:100%;background:#000; position:fixed; left:0;top:0; opacity:0.1; z-index:99; display:none;"></div> 
<jsp:include page="/include/header.jsp"></jsp:include>
<section id="edit">
  <div class="container">
    <div class="row">
      <%-- <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 title">
        <h2>Upload</h2>
        <hr>
        <c:if test="${null != _user }"><input type="button" class="form-control return-btn" value="返回"></c:if> 
        <!-- <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoree.</p> -->
      </div> --%>
      <div style="margin-left: 30px;">
      <div class="square"></div><div class="square"></div><div class="square"></div><div class="square"></div><div class="square"></div><div class="square"></div>
      </div><div class="col-md-4 col-sm-4 col-xs-6 wow fadeIn send-div" data-wow-delay="0.8s">
      	<img src="<c:if test="${null != item.img_name }">${ossimagepath }${item.img_name }@!${ossstylename }</c:if>" class="img-responsive upload-img" alt="">
      	<input type="file" id="uploadimg" name="uploadimg" accept="image/*" style="display: none;">
        <div class="upload-voice">
        	<audio controls="controls">
			  <source src='<c:if test="${fn:length(item.voice_name) > 0 }">${osspath }/${item.voice_name }</c:if>' type="audio/mp3" id="voiceplay"/>
			  <source src='' type="audio/ogg" />
			</audio>
        	<p class="voice-p">說<p>
        </div>
       	<input type="file" id="uploadvoice" name="uploadvoice" accept="audio/*" style="display: none;">
        <!-- <input type="button" class="save-btn" value="保存"> -->
      </div>
   	  <div class="wow fadeIn qrcode" data-wow-delay="1.2s"><a href='#qrimg' data-lightbox-gallery="portfolio-gallery" class="fade-scale"><img src='data:image/jpg;base64,${imgdata }' id="qrimg" alt="qrimg"></a></div>
      <div class="bshare-custom wow fadeIn" data-wow-delay="1.2s"><div class="bsPromo bsPromo2"></div><a title="分享到微信" class="bshare-weixin" href="javascript:void(0);"></a><a title="分享到新浪微博" class="bshare-sinaminiblog" href="javascript:void(0);"></a><a title="分享到QQ空间" class="bshare-qzone"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=9235cd43-caf3-4ecc-9642-b294ceb5636d&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
      <div class="wow fadeIn description-div" data-wow-delay="1.2s">To your heart</div>
      <div class="wow fadeIn" data-wow-delay="1.2s"><input type="button" class="save-btn" value="Send"></div>
      <form id="editform">
	      <input id="img_name" name="img_name" value="${item.img_name }" style="display: none;"/>
	      <input id="voice_name" name="voice_name" value="${item.voice_name }" style="display: none;"/>
	      <input id="create_time" name="create_time" value="${item.create_time }" style="display: none;"/>
	      <input id="qrcode" name="qrcode" value="${item.qrcode }" style="display: none;"/>
	      <input id="uuid" name="uuid" value="${item.uuid }" style="display: none;"/>
	      <input id="status" name="status" value="1" style="display: none;"/>
	      <input id="user.id" name="user.id" value="${item.user.id }" style="display: none;"/>
	      <input id="id" name="id" value="${item.id }" style="display: none;"/>
      </form>
      <!-- <div class="col-md-3 col-sm-3 wow fadeIn qrcode" data-wow-delay="1.2s"><input type="button" class="download-btn" value="下载"></div> -->
    </div>
  </div>
</section>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${path }/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		//图片默认
		if( null == $(".upload-img").attr("src") || $(".upload-img").attr("src").length == 0 ) {
			$(".upload-img").attr("src","${path}/images/uploading.jpg");
		}
		if( null == $(".qrcode").find("img").attr("src") || $(".qrcode").find("img").attr("src").length == 0 ) {
			//$(".qrcode").remove();
		}
		var _src = $(".upload-voice").find("#voiceplay").attr("src");
		if( null != _src && _src.length > 0 ) {
			$(".upload-voice").find("audio").show();
			$(".upload-voice").find(".voice-p").hide();
		}else {
			$(".upload-voice").find("audio").hide();
			$(".upload-voice").find(".voice-p").show();
		}
		var lihtml = '<li class="back-li"><a href="${path }/platform/list" class="smoothScroll">BACK</a></li>';
		$(".nav.navbar-nav.navbar-right").prepend(lihtml);
	});
	/* var TimeFn = null; */
	$(".upload-voice").click(function(e){
		/* if( $(e.target).is("audio") ) return;
		clearTimeout(TimeFn); */
		$("input[name=uploadvoice]").trigger("click");
	});
	/* $(".upload-voice").click(function(e){
		if( $(e.target).is("audio") ) return;
		clearTimeout(TimeFn); 
		TimeFn = setTimeout(function(){
			var _this = $(".upload-voice");
			var recording = _this.data("recording");
			if( null == recording || !recording ) {
				layer.msg("錄音中.再點擊按鈕,結束錄音並上傳.",{time:200000});
				_this.data("recording",true);
			}else{
				_this.data("recording",false);
				layer.closeAll();
				layer.msg("錄音結束",{time:1000});
				//TODO 上傳
			}
		},300);
	}); */
	$(".upload-img").click(function(){
		$("input[name=uploadimg]").trigger("click");
	});
	var client = new OSS.Wrapper({
      region: 'oss-cn-shanghai',
      accessKeyId: 'uDjvUhC4f6arNskS',
      accessKeySecret: '9JLJgraJfZntf3t31BKjLrhbQwtsOa',
      bucket: 'xushisend'
    });
	$(document).on("change","#uploadvoice,#uploadimg",function(){
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
	    }else {
	    	if(ext!=".MP3"&&ext!=".WMA"){ 
	    		layer.msg("只能上传mp3 和 wma");
		       	return;
		    }
	    }
	    $(".Mask-layer").show();
	    layer.msg("上傳中.....",{time:10*60*1000})
	    readOSSURL(this);
		//readURL(this);
	});
	function readOSSURL(input) {
		var isimg = $(input).attr("name") == "uploadimg"?true:false;
		var filepath=$(input).val(); 
	    var extStart=filepath.lastIndexOf("."); 
	    var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		var file = input.files[0];
		var newfilename = uuid();
	    var storeAs = newfilename+ext;

	    client.multipartUpload(storeAs, file).then(function (result) {
	      var _name = result.name;
	      var _url = "${osspath}"+_name;
	      if( isimg ) {//图片
	    		$(".upload-img").attr("src",_url);
	    		$("#img_name").val(_name);
	        }else {
	        	$("#voiceplay")[0].src=_url;
	        	$("audio").load();
	        	$("audio").show();
	        	$(".voice-p").hide();
	        	$("#voice_name").val(_name);
	        }
	      	var _data = $("form#editform").serialize();
		    $.ajax({
				url:"${path}/platform/ajax_edit",
				data:_data,
				success:function(data){
					if( null != data.result && data.result ) {
						$("#id").val(data.ret.id);
					}
					layer.msg(data.msg);
					$(".Mask-layer").hide();
				}
			});
	    }).catch(function (err) {
	      console.log(err);
	      $(".Mask-layer").hide();
	    });
	}
	//上传
	function readLocalURL(input) {
	    if (input.files && input.files[0]) {
	    	var isimg = $(input).attr("name") == "uploadimg"?true:false;
	        var reader = new FileReader();
	        reader.readAsDataURL(input.files[0]);
	        reader.onload = function (e) {
		      	$.ajaxFileUpload({
		        	url: '${path}/uploadfile', //用于文件上传的服务器端请求地址
		            secureuri: false, //一般设置为false
		            fileElementId: $(input).attr("id"), //文件上传空间的id属性  <input type="file" id="file" name="file" />
		            dataType: 'JSON', //返回值类型 一般设置为json
		            success: function (data)  //服务器成功响应处理函数
		            {
		            	var _url = JSON.parse(data).url;
		            	var _name = JSON.parse(data).name;
		            	if( isimg ) {//图片
		            		$(".upload-img").attr("src",_url);
		            		$("#img_name").val(_name);
		                }else {
		                	$("#voiceplay")[0].src=_url;
		                	$("audio").load();
		                	$("audio").show();
		                	$(".voice-p").hide();
		                	$("#voice_name").val(_name);
		                }
		            	//保存
		            	var _data = $("form#editform").serialize();
		        		$.ajax({
		        			url:"${path}/platform/ajax_edit",
		        			data:_data,
		        			success:function(data){
		        				if( null != data.result && data.result ) {
		        					$("#id").val(data.ret.id);
		        				}
		        				layer.msg(data.msg);
		        				$(".Mask-layer").hide();
		        			}
		        		});
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
	
	//保存
	$(".save-btn").click(function(e){
		var img_name = $("#img_name").val();
		var voice_name = $("#voice_name").val();
		if( null == img_name || null == voice_name || img_name.length == 0 || voice_name == 0 ) {
			layer.msg("必须先上传图片和音频");
			return;
		}
		$("#status").val(2);
		var _data = $("form#editform").serialize();
		$.ajax({
			url:"${path}/platform/ajax_edit",
			data:_data,
			success:function(data){
				if( null != data.result && data.result ) {
					window.location.href="list";
				}
				layer.msg(data.msg);
				$(".Mask-layer").hide();
			}
		});
	});
	//返回
	$(".return-btn").click(function(){
		window.location.href="list";
	});
	
	function uuid() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 36; i++) {
		s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";
	
		var uuid = s.join("");
		return uuid;
	} 
</script>
</html>