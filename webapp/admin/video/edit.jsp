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
       		<input id="" name="create_time" type="hidden" value="${item.create_time }"/>
        </c:if>
        <div class="main-contentWrap cm-f14">
          <table class="table">
            <tbody>
                <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>标题:</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:200}" name="title" id="" value="${item.title }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>视频图片：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:200}" name="img" id="" value="${item.img }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>视频地址：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:2000}" name="url" id="" value="${item.url }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">收费价格：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {number:true}" name="amount" id="" value="${item.amount }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>视频描述：</td>
                <td class="col-xs-8">
                  <textarea rows="5" cols="" class="table-input" style="width:500px;" name="summary"  id="">${item.summary }</textarea>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>是否免费：</td>
                <td class="col-xs-8">
                  <input type="radio" class="table-input" name="is_free" <c:if test="${item.is_free==null||item.is_free==1 }">checked="checked"</c:if>  value="1"/>免费&nbsp;
                  <input type="radio" class="table-input" name="is_free" <c:if test="${item.is_free!=null&&item.is_free!=1 }">checked="checked"</c:if>  value="2"/>收费
                </td>
              </tr>
              <c:if test="${item.id > 0 }">
              <tr>
                <td class="col-xs-2 table-item">創建時間：</td>
                <td class="col-xs-8">
                  <label class="cm-lh31 cm-f14 cm-f-normal cm-mg0">${item.create_time }</label>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">更新時間：</td>
                <td class="col-xs-8">
                  <label class="cm-lh31 cm-f14 cm-f-normal cm-mg0">${item.update_time }</label>
                </td>
              </tr>
              </c:if>
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

