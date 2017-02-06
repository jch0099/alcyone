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
                <td class="col-xs-2 table-item"></td>
                <td class="col-xs-8">
                  <label class="cm-required">收费价格为总共的价格，并且时长只能是整数。</label>
                </td>
              </tr>
           		<tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>名字:</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:10}" name="name" id="" value="${item.name }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">时长：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {number:true}" name="month_length" id="" value="${item.month_length }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">收费价格：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {number:true}" name="amount" id="" value="${item.amount }"/>
                </td>
              </tr>
              <tr>
                <td class="cm-tac" colspan="2" >
                  <input type="submit" class="cm-btn-default cm-border0" value="確定">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <a href="javascript:window.Util.showBox.closeBox();" class="cm-btn-default" >取消</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </form>
    </div>
    <footer></footer>
	<jsp:include page="/include/page_edit_footer.jsp"></jsp:include>
  </body>
</html>

