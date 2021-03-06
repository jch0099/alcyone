<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/include/video/common.jsp"></jsp:include>
<link rel="stylesheet" href="${path }/css/video/play.css">
<%-- <link rel="stylesheet" href="${path }/css/video/player.css">
<script type="text/javascript" src="${path }/js/jsPlayer.js"></script>
<script type="text/javascript" src="${path }/js/dtooltip-min.js"></script> --%>
<script type="text/javascript" src="${path }/js/ckplayer/ckplayer.js" charset="utf-8"></script>
</head>
<body class="home blog">
<jsp:include page="/include/video/header.jsp"></jsp:include>
<div class="index-ad">
	<c:forEach items="${adtop }" var="item">
		<c:if test="${!empty item.url }">
			<div class="ad"><a href="${item.url }" target="_blank"><img alt="" src="${item.img }"></a></div>
		</c:if>
	</c:forEach>
</div>
<div class="content">
	<div class="nav"><a href="index">视频列表</a></div>
	<div class="video-box">
		<div class="video-left"> 
			<div class="video-wrapper">
				<c:choose>
				<c:when test="${!empty video.url && video.url!='-no-auth' && video.type==1 }">
					<div id="ckplayer-div" data-url="${path }/video/get_video/${video.id }.mp4"></div>
				</c:when>
				<c:when test="${video.type==2 }">
					${video.url }
				</c:when>
				<c:otherwise>
				<img src="${path }/images/404video.png"/>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="video-right info">
			<h3 class="title">${video.title }</h3>
			<div class="see"><!-- <i class="fa fa-user" aria-hidden="true"></i> --><span>${video.read_num }&nbsp;次观看</span></div>
			<div class="price_free">${video.is_free==1?"":"收费" }</div>
			<div class="btns">
				<a class="btn btn-success button donate left" target="_blank" href="${path }/video/pay/pay_">打赏</a>
				<c:if test="${video.url=='-no-auth'}">
					<a class="btn btn-success button donate left" target="_blank" href="${path }/video/pay/pay_video?id=${video.id }" style="margin-left: 10px;">购买该视频</a>
				</c:if>
				<br>
				<div class="share">
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style"><span class="jiathis_txt">分享到：</span>
					<a class="jiathis_button_icons_1"></a>
					<a class="jiathis_button_icons_2"></a>
					<a class="jiathis_button_icons_3"></a>
					<a class="jiathis_button_icons_4"></a>
					<a class="jiathis_button_icons_5"></a>
					<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
					</div>
					<script type="text/javascript" >
					var jiathis_config={
						summary:"",
						shortUrl:false,
						hideMore:false
					}
					</script>
					<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->

				</div>
			</div>
		</div>
	</div>
</div>
<%-- <div class="video-info">
	<div class="title">
                视频介绍
    </div>
    <div class="summary">
        ${video.summary }
    </div>
</div> --%>
<div class="index-ad" style="margin-top: 50px;">
	<c:forEach items="${adbottom }" var="item">
		<c:if test="${!empty item.url }">
			<div class="ad"><a href="${item.url }" target="_blank"><img alt="" src="${item.img }"></a></div>
		</c:if>
	</c:forEach>
</div>
<jsp:include page="/include/video/footer.jsp"></jsp:include>
<script type="text/javascript">
	var _path = "${path}";
	$(function(){
		//$('#switcher').themeswitcher();
		//$('video').video();
		//var ps=new jsPlayer("640","640","myVideo");
		if( $("#ckplayer-div").length > 0 ) {
			var url = $("#ckplayer-div").data("url");
			var flashvars={
		    	f:url,
		        c:0
		    };
		    //var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
		    CKobject.embedSWF('${path}/js/ckplayer/ckplayer.swf','ckplayer-div','ckplayer_a1','640','360',flashvars,{});
		}
	});
</script>
<%-- <script type='text/javascript' src='${path}/skin/ecms082/js/loader.js?ver=1.3'></script> --%>
</body>
</html>