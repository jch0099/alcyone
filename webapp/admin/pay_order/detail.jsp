<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-tw">
  <head>
	<jsp:include page="/include/page_edit_head.jsp">
		<jsp:param name="title" value="编辑" />
	</jsp:include>
  </head>
  <body style="width:750px; overflow-x:hidden;" path="${path}">
    <!--主区域 -->
    <div class="main-container" id="mainContainer">
      <form  class="form-horizontal" role="_editForm form" action="ajax_edit" data-validate="true"  id="editForm" method="post">
        <c:if test="${item.id > 0 }">
       		<input id="" name="id" type="hidden" value="${item.id }"/>
        </c:if>
        <div class="main-contentWrap cm-f14">
          <table class="table">
            <tbody>
              <tr>
                <td class="col-xs-2 table-item">id:</td>
                <td class="col-xs-8">
                	${item.id }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">用户名:</td>
                <td class="col-xs-8">
                	${item.user_account }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">支付帐号:</td>
                <td class="col-xs-8">
                	${item.pay_account }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">支付时间:</td>
                <td class="col-xs-8">
                	${item.pay_time }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">类型:</td>
                <td class="col-xs-8">
                	<c:if test="${item.type==1 }">单个视频 </c:if>
	              	<c:if test="${item.type==2 }">购买用户vip </c:if>
	              	<c:if test="${item.type==3 }">打赏 </c:if>
                </td>
              </tr>
              <c:if test="${item.type==1 }">
	              <tr>
	                <td class="col-xs-2 table-item">视频:</td>
	                <td class="col-xs-8">
	                	${item.video_title }
	                </td>
	              </tr>
              </c:if>
              <c:if test="${item.type==2 }">
	              <tr>
	                <td class="col-xs-2 table-item">购买时长:</td>
	                <td class="col-xs-8">
	                	${item.month_length }
	                </td>
	              </tr>
              </c:if>
           	  <tr>
                <td class="col-xs-2 table-item">阿里订单号:</td>
                <td class="col-xs-8">
                	${item.alipay_order_num }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required"></label>价格:</td>
                <td class="col-xs-8">
                	${item.amount }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required"></label>状态:</td>
                <td class="col-xs-8">
                	<c:if test="${item.status == 2 }">
              			未支付
              		</c:if>
					<c:if test="${item.status == 1 }">
              			已完成
              		</c:if>
              		<c:if test="${item.status == 3 }">
              			补足订单
              		</c:if>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required"></label>创建时间:</td>
                <td class="col-xs-8">
                	${item.create_time }
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required"></label>更新时间:</td>
                <td class="col-xs-8">
                	${item.update_time }
                </td>
              </tr>
              <!-- <tr>
                <td class="cm-tac" colspan="2" >
                  <input type="submit" class="cm-btn-default cm-border0" value="確定">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <a href="javascript:window.Util.showBox.closeBox();" class="cm-btn-default" >取消</a>
                </td>
              </tr> -->
            </tbody>
          </table>
        </div>
      </form>
    </div>
    <footer></footer>
	<jsp:include page="/include/page_edit_footer.jsp"></jsp:include>
  </body>
</html>

