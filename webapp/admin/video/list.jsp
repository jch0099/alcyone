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
  	<jsp:param name="parentName" value="视频管理" />
  	<jsp:param name="parentUrl" value="list" />
  	<jsp:param name="currName" value="视频列表" />
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
             <input class="cm-input-default cm-w200 cm-mr15" name="keyword" placeholder="请输入关键字" value="${fn:escapeXml(param.keyword)}"  />
             <select name="is_free" id="is_free" class="cm-select-default cm-mr15" role="_defaultData" data-defaultValue="${param.is_free}">
          		<option value="">请选择付费类型 </option>
          		<option value="1">免费 </option>
          		<option value="2">付费 </option>
          	</select>
           	 <a href="javascript:void(0);" id="search-btn" role="_searchBtn" class="cm-btn-default search-btn cm-mr25" >搜索</a>
           	 <a href="edit" title="新增视频" class="pull-right cm-btn-confirm-bg cm-btn-default" target="_box">新增视频</a>
           </div>
           <!-- 列表 -->
           <table class="table table-striped table-detailList">
             <thead>
                <tr>
                   <th width="5%">视频id</th>
                   <th width="10%">标题</th>
                   <th width="8%">免费状态</th>
                   <th width="8%">视频类型</th>
                   <th width="10%">视频价格</th>
                   <th width="10%">视频封面</th>
                   <th width="10%">视频链接</th>
                   <th width="5%">视频状态</th>
                   <th width="15%">更新時間</th>
                   <th style="border-right:none;">操作</th>
                </tr>
             </thead>
             <tbody>
             <c:forEach var="result" items="${page.results }">
	            <tr>
	              <td>${result.id }</td>
	              <td>${result.title }</td>
	              <td>${result.is_free==1?"免费":"付费" }</td>
	              <td>${result.type==1?"站内视频":"站外视频" }</td>
	              <td>${result.amount }</td>
	              <td><c:if test="${!empty result.img }"> <img alt="" src="${result.img }"> </c:if></td>
	              <td>${xw:x(result.url)} </td>
	              <td>
          				<c:if test="${result.status == 2 }">
	              			<span class="label label-danger" role="_status">未啟用</span>
	              		</c:if>
						<c:if test="${result.status == 1 }">
	              			<span class="label label-success" role="_status">啟用</span>
	              		</c:if>
	                <%-- <span class="label label-danger" role="_status">
	                <c:if test="${result.stype==1 }">未啟用</c:if>
	                <c:if test="${result.stype==2 }">啓用</c:if>
	                </span> --%>
	              </td>
	              <td>${result.update_time} </td>
	              <td class="detailList-btn">
	                <a href="edit?id=${result.id }" title="編輯视频" class="pull-left icon-edit icon" target="_box"></a>
	                <a href="javascript:void(0)" title="${result.status==2 ? '啟用' : '停用'} " data-id="${result.id }"  data-url="ajax_updateStatus" role="_updateOp" class="${(result.status==2)?'icon-ok-circle icon':'icon-ban-circle icon disable' }"  ></a>
	                <a href="javascript:void(0)" title="刪除" data-id="${result.id }" data-url="ajax_delete" class="pull-left icon-remove icon" role="_deleteOp" ></a>
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


