<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/include/video/common.jsp"></jsp:include>
<link href="${path }/css/pay/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path }/css/pay/help.css"/>
</head>
<body>
<jsp:include page="/include/video/header.jsp"></jsp:include>
<div class="w_1200 fw_box ">
	<div  class="fw_conent"> 
    <!--内容开始------------------------------>
    <div class="bzzx_bt"> <!-- <strong>购买会员</strong> -->请选择时长和数量,总共的时间为时长×数量，以月为单位。<!-- <span>编辑、刷新、置顶</span>等管理 --></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zwfb_shop_table">
      <tr valign="top">
        <td align="right" width="70">购买时长：</td>
        <td><ul class="fwmc_xxk">
        	<c:forEach items="${configs }" var="item">
	       		<li data-length="${item.month_length }">
	              <p>${item.name }</p>
	              <i>${item.amount }</i>元</li>
        	<%-- <c:if test="${item.type==2 }">
        		<li class="arb">
	              <p>${item.name }</p>
	              <i>${item.amount }</i>元/月</li>
        	</c:if> --%>
        	</c:forEach>
          </ul></td>
      </tr>
      <!-- <tr class="month-length-tr" style="display: none;">
        <td align="right">购买时长：</td>
        <td><input type="text" class="month-length" style=""/> </td>
      </tr> -->
      <tr>
        <td height="20"></td>
        <td></td>
      </tr>
      <tr>
        <td align="right">购买数量：</td>
        <td><ul class="nmb_jj">
            <li class="jian">-</li>
            <li>
              <input name="" type="text" value="1" class="srkk" />
            </li>
            <li class="jia">+</li>  
          </ul></td>
      </tr>
      <tr>
        <td height="20"></td>
        <td></td>
      </tr>
      <tr>
        <td align="right">价格：</td>
        <td><div class="zwfb_zje"><span>0</span> <i>元</i></div></td>
      </tr>
      <tr>
        <td height="20"></td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td><a class="ty_but ml20" href="javascript:void(0);">购 买</a></td>
      </tr>
    </table>
    <div class="gmxz_tip"> 购买须知：
      <p>1.请仔细核对时间和价格。</p>
      <p>2.本次服务购买后即生效，不支持退费服务。</p>
    </div>
    <!--内容结束------------------------------> 
  </div>
</div>
<jsp:include page="/include/video/footer.jsp"></jsp:include>
</body>
<!--js区域-->
<script type="text/javascript">
$(document).ready(function(e) {
    $(".fwmc_xxk li").click(function(){
		/* if( $(this).hasClass("arb") ) {
			$(".month-length-tr").show();
		}else {
			$(".month-length-tr").hide();
		} */
		$(this).addClass("on").siblings().removeClass("on");
		tola();
	});
	//加
	$(".jia").click(function(){
		var fw_numb = $(".srkk").val();	
		fw_numb++;
		$(".srkk").val(fw_numb);		
		tola();
	});
	//减
	$(".jian").click(function(){
		var fw_numb = $(".srkk").val();	
		if(fw_numb <= 1){
			layer.alert("数量不足了，请住手！")
		}else{
			fw_numb--;
			$(".srkk").val(fw_numb);		
		}
		tola();
	});
	//购买
	$("a.ty_but").click(function(){
		var length = $("ul.fwmc_xxk .on").data("length");
		var count = $(".srkk").val();
		if( length == null || count == 0 || length == 0 || count <= 0 ) {
			layer.msg("请选择时长");
			return;
		}
		console.info("购买时长:"+length);
		console.info("购买数量:"+count);
		
	});
});

function tola(){
		var $li = $(".fwmc_xxk").children("li.on");
		var money = $li.children("i").text();
		var count = $(".srkk").val();
		$(".zwfb_zje span").text(money*count);
}

</script>
</html>
