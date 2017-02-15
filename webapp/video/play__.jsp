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
<div id="a1"></div>
<script type="text/javascript">
    var flashvars={
        f:'${path}/video_file/kkk.mp4',
        c:0
    };
    var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    CKobject.embedSWF('${path}/js/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
</script></body>
</html>