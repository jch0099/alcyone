<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
  <jsp:include page="/include/page_list_head.jsp">
		<jsp:param name="title" value="昴宿六" />
	</jsp:include>
</head>
<body>
<header>
  <jsp:include page="/include/top.jsp">
  	<jsp:param name="parentName" value="广告管理" />
  	<jsp:param name="parentUrl" value="list" />
  	<jsp:param name="currName" value="广告列表" />
  	<jsp:param name="hasPrint" value="false" />
  	<jsp:param name="hasExport" value="false" />
  </jsp:include>
</header>
  <div class="container-fluid clearfix">  
  	<jsp:include page="/include/leftnavi.jsp"></jsp:include>
     <!-- 主内容 -->
    <div class="main-container" id="mainContainer">
       <form id="searchForm" role="_searchForm" action="list" method="post">
          <input id="pageno" name="pageno" type="hidden" value="${page.pageno}">
         <div class="containerWrap center-block">
           <!-- 搜索栏 -->
           <div class="cm-f14 cm-pdt10 cm-tar cm-overflow">
           </div>
           <!-- 列表 -->
           <table class="table table-striped table-detailList">
             <thead>
                <tr>
                   <th width="10%">名称</th>
                   <th width="15%">图片</th>
                   <th width="15%">位置</th>
                   <th width="15%">目标链接</th>
                   <th width="10%">状态</th>
                   <th width="15%">更新時間</th>
                   <th style="border-right:none;">操作</th>
                </tr>
             </thead>
             <tbody>
             <c:forEach var="result" items="${page.results }">
	            <tr>
	              <td>${result.title }</td>
	              <td><c:if test="${!empty result.img }"> <img alt="" src="${result.img }"> </c:if></td>
	              <td>${result.page==1?"首页":"播放页" } 第${result.position }个广告</td>
	              <td><a href="${result.url }" target="_blank">${result.url }</a></td>
	              <td>
          				<c:if test="${result.status == 2 }">
	              			<span class="label label-danger" role="_status">未啟用</span>
	              		</c:if>
						<c:if test="${result.status == 1 }">
	              			<span class="label label-success" role="_status">啟用</span>
	              		</c:if>
	              </td>
	              <td>${result.update_time} </td>
	              <td class="detailList-btn">
	                <a href="edit?id=${result.id }" title="編輯广告" class="pull-left icon-edit icon" target="_box"></a>
	                <a href="javascript:void(0)" title="${result.status==2 ? '啟用' : '停用'} " data-id="${result.id }"  data-url="ajax_updateStatus" role="_updateOp" class="${(result.status==2)?'icon-ok-circle icon':'icon-ban-circle icon disable' }"  ></a>
	              </td>
	            </tr>
             </c:forEach>
             </tbody>
           </table>
            <!--分页 -->
     		<jsp:include page="/include/pagebar.jsp"></jsp:include>
         </div>
       </form>	
    </div>
  </div>
  <jsp:include page="/include/page_list_footer.jsp"></jsp:include>
</body>
</html>


