<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-tw">
  <head>
	<jsp:include page="/include/page_edit_head.jsp">
		<jsp:param name="title" value="编辑" />
	</jsp:include>
  </head>
  <body style="width:750px; overflow-x:hidden;" path="${path}/admin">
    <!--主区域 -->
    <div class="main-container" id="mainContainer">
      <form  class="form-horizontal" role="_editForm form" action="ajax_changepassword" data-validate="true"  id="editForm" method="post" >
        <input id="" name="id" type="hidden" value="${_admin_user.id }"/>   
        <div class="main-contentWrap cm-f14">
          <table class="table">
            <tbody>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>密碼:</td>
                <td class="col-xs-8">
                  <input type="password" class="table-input cm-w200 {required:true,minlength:6,maxlength:16}" name="password" id="password" value=""/>
                  <!-- <label class="cm-lh31 cm-f14 cm-f-normal">2-30个字符</label> -->
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>確認密碼:</td>
                <td class="col-xs-8">
                  <input type="password" class="table-input cm-w200 {required:true,equalTo:'#password'}" name="confirm" id="confirm" value=""/>
                  <!-- <label class="cm-lh31 cm-f14 cm-f-normal">2-30个字符</label> -->
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

