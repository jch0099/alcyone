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
                  <label class="cm-required">以下内容链接若为站外链接,必须带上协议http或者https</label>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>名称:</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:200}" name="title" id="" value="${item.title }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>图片链接：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:200}" name="img" id="" value="${item.img }"/>
                  <a id="menuBtn" href="javascript:void(0);">选择</a>
                  <ul id="treeDemo" class="ztree" style="display: none;"></ul>
                </td>
              </tr>
              <%-- <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>视频图片：</td>
                <td class="col-xs-8">
                 <div class="upload-viewer">
                   	<span class="imgDelete icon-remove-sign" title="清空图片" data-baseurl="${path}" data-defaultPhoto="/images/default-photo.jpg"></span>
                   	<a href="javascript:void(0);"></a>
                    <img src="${path}/images/default-photo.jpg" border="0"  width="100%">
                  </div>
                  <input type="hidden" name="img" <c:if test="${item.id > 0  && fn:length(item.img) > 0}"> data-baseurl="${path}/uploads/"  role="_showPicurl" </c:if> value="${item.img}" class="{required:true,maxlength:50}" />
                  <input class="fileupload" id="fileupload1" type="file" name="files[]" role="_upload" data-show="true" data-target="small_logo" accept="image/gif,image/jpeg,image/png">
                </td>
              </tr> --%>
              <tr>
                <td class="col-xs-2 table-item"><label class="cm-required">*</label>目标链接：</td>
                <td class="col-xs-8">
                  <input type="text" class="table-input cm-w200 {required:true,maxlength:2000}" name="url" id="" value="${item.url }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">位置：</td>
                <td class="col-xs-8">
                  <label>${item.page==1?"首页":"播放页" } 第${item.position }个广告</label>
                  <input type="hidden" class="" name="position" id="" value="${item.position }"/>
                  <input type="hidden" class="" name="page" id="" value="${item.page }"/>
                </td>
              </tr>
              <tr>
                <td class="col-xs-2 table-item">广告描述：</td>
                <td class="col-xs-8">
                  <textarea rows="5" cols="" class="table-input" style="width:500px;" name="summary"  id="">${item.summary }</textarea>
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
<link rel="stylesheet" href="${path }/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${path }/js/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${path }/js/plugins/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript">
$(function(){
	var zNodes = ${zNodes}; 		
	var setting = {
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
			onClick:function(event, treeId, treeNode){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = zTree.getSelectedNodes();
				var url = "${path}/uploads/"+nodes[0].img;
				$("input[name=img]").val(url);
			}
		}
	};
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$("#menuBtn").click(function(){
		if( $("#treeDemo").is(":visible")){
			$("#treeDemo").hide();
		}else {
			$("#treeDemo").show();
		}
	});
});
</script>
</html>

