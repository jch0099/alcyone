<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<footer class="footer">
  <div class="container">
    <div class="fcode"> </div>
    <p>&copy; 2017 © <a href="${path }/video/index">昴宿六</a> <br/><br/>业务合作QQ:&nbsp;&nbsp;&nbsp;<a target=blank href=tencent://message/?uin=810174322>810174322</a><!-- <a href="#" target="_blank">免责声明</a> <a href="#" target="_blank">投稿规则</a> --> <!-- <a href="#" target="_blank">鲁ICP备12345678号</a> --> <br>
    </p>
    </div>
    <!-- <div class="yqlj">
	  <div class="yqljbt">
	    <div class="yqljbt_l"> <a href="javascript:void(0);" target="_blank">友情链接</a></div>
	    <div class="yqljbt_r"><a href="#" target="_blank">申请</a></div>
	  </div>
	  <div class="yqljlb">
	    <ul>
	       <li><a href="http://www.amifan.com" target="_blank">泛渔源码</a></li>
	     </ul>
	  </div>
	</div> -->
    <div class="friend">
    	<div>
    		<i class="fa fa-link"></i>友情链接
    	</div>
    	<div class="links">
    	<c:forEach items="${friend_links }" var="item">
    		<a href="${item.url }" target="_blank">${item.name }</a>
    	</c:forEach>
    	</div>
    </div>
<script type="text/javascript">
	$.ajax({
		url:"${path}/data/json/getFriend_link",
		success:function(data){
			for(var i=0;i<data.length;i++){
				var _html = '<a href="'+data[i].url+'" target="_blank">'+data[i].name+'</a>';
				$(".footer .friend .links").append(_html);
			}
		}
	});
</script>
</footer>
