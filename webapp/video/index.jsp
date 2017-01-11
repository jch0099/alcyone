<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/include/video/common.jsp"></jsp:include>
<!--[if lt IE 9]><script src="${path}/skin/ecms082/js/html5.min.js"></script><![endif]-->
</head>
<body class="home blog">
<jsp:include page="/include/video/header.jsp"></jsp:include>
<section class="container">
  <div class="content-wrap">
    <div class="content">
      <div class="title">
        <h3> 最新发布 </h3>
        <div class="more"><a href="?sortstr=read_num">热门浏览</a><!-- <a href="/zan/">最多赞</a>&nbsp;&nbsp;&nbsp;&nbsp;--></div> 
      </div>
      <c:forEach var="item" items="${page.results }">
	       <article class="excerpt">
	        <p class="image-container" ><a class="focus" href="${path }/video/play?id=${item.id}"><img data-src="" class="thumb" src="${item.img }"></a></p>
	        <header><!-- <a class="cat" href="/zxdy/dzp/">动作片<i></i></a> -->
	          <h2><a href="${path }/video/play?id=${item.id}" title="${item.title}">${item.title}</a></h2>
	        </header>
	        <p class="meta"><span class="pv"><i class="fa fa-eye"></i>阅读(${item.read_num==null?0:item.read_num})</span><!-- <a class="pc" href="/zxdy/dzp/2016-01-08/21.html#comments"><i class="fa fa-comments-o"></i>评论(<span class="ds-thread-count" data-thread-key="21" data-count-type="comments">0</span>)</a><span class="pv ding"><i class="fa fa-heart"></i>15</span></p> -->
	      </article>
      </c:forEach>
      <div class="pagination">
        <ul>
          <li class="pre-page"><a href="javascript:void(0);">上一页</a></li>	
          <c:forEach var="v" varStatus="vs" begin="${page.pageIndex.startindex }" end="${page.pageIndex.endindex }">
          	<c:choose>
			  <c:when test="${vs.index == page.pageno}">
				<li class="active"><a href="javascript:void(0);">${page.pageno}</a></li>
			  </c:when>
			  <c:otherwise>
			  	<li><a href="javascript:void(0);" data-page="${vs.index}">${vs.index}</a></li>
			  </c:otherwise>
			</c:choose>
          </c:forEach>
          <li class="next-page"><a href="javascript:void(0);">下一页</a></li>
        </ul>
      </div>
    </div>
  </div>
</section>
<jsp:include page="/include/video/footer.jsp"></jsp:include>
<script type="text/javascript">
	//跳页
	$(".pagination ul > li:not(.pre-page):not(.next-page):not(.active) > a").click(function(){
		var _this = $(this);
		var pageno = _this.data("page");
		if( pageno.length == 0 ) return;
		goPage(pageno);
	});
	//上一页
	$(".pagination .pre-page a").click(function(){
		var nowli = $(".pagination li.active");
		var goli = nowli.prev();
		if( goli.hasClass("pre-page") ) return;
		var pageno = goli.find("a").data("page");
		if( pageno.length == 0 ) return;
		goPage(pageno);
	});
	//下一页
	$(".pagination .next-page a").click(function(){
		var nowli = $(".pagination li.active");
		var goli = nowli.next();
		if( goli.hasClass("next-page") ) return;
		var pageno = goli.find("a").data("page");
		if( pageno.length == 0 ) return;
		goPage(pageno);
	});
	
	function goPage(pageno) {
		var _href="${path}/video/index";
		_href += "?pageno="+pageno;
		var sortstr = "${param.sortstr}";
		if(sortstr.length>0) _href += "&sortstr="+sortstr;
		window.location.href = _href;
	}
</script>
<%-- <script type='text/javascript' src='${path}/skin/ecms082/js/loader.js?ver=1.3'></script> --%>
</body>
</html>