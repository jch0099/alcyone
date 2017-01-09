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
	#edit .upload-voice {
		width: 450px;
	}
</style>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="/include/header.jsp"></jsp:include>
<section id="edit">
  <div class="container">
     <div class="wow fadeIn see-div" data-wow-delay="0.8s">
       <a href="<c:if test="${null != item }">${ossimagepath }${item.img_name }@!${ossstylename }</c:if>" data-lightbox-gallery="portfolio-gallery"  class="fade-scale"><img src="<c:if test="${null != item }">${ossimagepath }${item.img_name }@!${ossstylename }</c:if>" class="img-responsive upload-img" alt="" alt="portfolio img"></a>
       <div class="upload-voice">
       	<audio controls="controls" style="display: none;" id="voice">
		  <source src='<c:if test="${null != item }">${osspath }${item.voice_name }</c:if>' type="audio/mp3" id="voiceplay"/>
		  <source src='' type="audio/ogg" />
		</audio>
		<p class="voice-p">聽<p>
       </div>
     </div>
  </div>
</section>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${path }/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(".voice-p").click(function(){
		var _voice = $("#voice")[0];
		if( _voice.paused ) {
			_voice.play();
		}else {
			_voice.pause();
		}
	});
	$(function(){
		var lihtml = '<li class="back-li"><a href="${path }/nologin/wsee" class="smoothScroll">BACK</a></li>';
		$(".nav.navbar-nav.navbar-right").prepend(lihtml);
	});
</script>
</html>