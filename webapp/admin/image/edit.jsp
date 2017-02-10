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
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>标题：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:200}" name="title" id="" value="${item.title }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>图片：</td>
                <td class="col-xs-8">
                 <div class="upload-viewer">
                   	<span class="imgDelete icon-remove-sign" title="清空图片" data-baseurl="${path}" data-defaultPhoto="/images/default-photo.jpg"></span>
                   	<a href="javascript:void(0);"></a>
                    <img src="${path}/images/default-photo.jpg" border="0"  width="100%">
                  </div>
                  <input type="hidden" name="img" id="img" <c:if test="${item.id > 0  && fn:length(item.img) > 0}"> data-baseurl="${path}/uploads/"  role="_showPicurl" </c:if> value="${item.img}" class="{required:true,maxlength:200}" />
                  <input class="fileupload" id="fileupload1" type="file" name="files[]" role="_upload" data-show="true" data-target="img" accept="image/gif,image/jpeg,image/png">
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>状态：</td>
                <td class="col-xs-8">
                	<select name="status" class=" cm-select-default cm-mt5 cm-mr10" role="_defaultData"
						data-defaultValue="${item.status }">
							<option value="1">启用</option>
							<option value="2">未启用</option>
					</select>
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

