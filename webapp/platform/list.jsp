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
	/* .fa-close {
	    position: absolute;
	    z-index: 99;
	    right: 7%;
	} */
</style>
</head>
<body data-spy="scroll" data-target=".navbar-collapse" data-offset="50">
<jsp:include page="/include/header.jsp"></jsp:include>
<div id="list">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 title">
        <h2>Stamp</h2>
        <p style="color: red;">${errmsg }</p>
        <input type="button" class="form-control download-btn" value="下載所有">
        <!-- <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoree.</p> -->
      </div>
      <div class="col-md-12 col-sm-12"></div>
      <c:forEach items="${list }" var="item"  >
      	  <c:set var="imgpath" value="${osspath}${item.img_name}"></c:set>
      	  <c:set var="qrcode" value="data:image/jpg;base64,${item.qrcode }"></c:set>
	      <div class="wow fadeIn list-item" data-wow-delay="0.6s"><a href="${path }/platform/edit?id=${item.id}" ><img src="${(null == item.status || item.status == 1)?qrcode:imgpath }" alt="edit"></a>
	      	  <span style="color: #FFFFFF;">${item.last_time }</span>
	      </div>
      </c:forEach>
      <div class="wow fadeIn list-item" data-wow-delay="0.6s"><a href="${path }/platform/edit" ><span class="collect-span" alt="edit">Collect +</span></a>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(".add-btn").click(function(){
		window.location.href="edit";
	});
	$(".fa-close").click(function(){
		var _id = $(this).data("id");
		layer.confirm("确定删除?",{title:"提示"},function(){
			$.ajax({
				url:"${path}/platform/delete",
				data:{"id":_id},
				success:function(data){
					if( null != data.result && data.result ) window.location.href="list";
					layer.msg(data.msg);
				}
			});
		});
	});
	$(".download-btn").click(function(){
		$(".Mask-layer").show();
		$.ajax({
			url:"${path}/platform/downloadall",
			success:function(data){
				$(".Mask-layer").hide()
				if( null != data.result && data.result ) {
					window.location.href = data.ret.url;
				}
			},
			error:function(){
				$(".Mask-layer").hide();
			}
		});
	});
</script>
</html>