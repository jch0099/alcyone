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
             <input class="cm-input-default cm-w200 cm-mr15" name="keyword" placeholder="請輸入關鍵字" value="${fn:escapeXml(param.keyword)}"  />
             <%-- <select name="status" id="status" class="cm-select-default cm-mr15" role="_defaultData" data-defaultValue="${param.status}">
          		<option value="">請選擇帳戶狀態 </option>
          		<c:forEach var="arr" items="${commArray.arr_Status }" varStatus="i" begin="1">
          			<option value="${i.index }">${arr }</option>
          		</c:forEach>
          	</select> --%>
           	 <a href="javascript:void(0);" id="search-btn" role="_searchBtn" class="cm-btn-default search-btn cm-mr25" >搜索</a>
           	 <a href="edit" title="新增帳戶" class="pull-right cm-btn-confirm-bg cm-btn-default" target="_box">新增广告</a>
           </div>
           <!-- 列表 -->
           <table class="table table-striped table-detailList">
             <thead>
                <tr>
                   <th width="10%">帳戶</th>
                   <th width="15%">中文名稱</th>
                   <th width="15%">英文名稱</th>
                   <th width="10%">帳戶狀態</th>
                   <th width="15%">更新時間</th>
                   <th width="15%">更新人</th>
                   <th style="border-right:none;">操作</th>
                </tr>
             </thead>
             <tbody>
             <%-- <c:forEach var="result" items="${page.results }">
             	<c:if test="${result.id != sessionScope._user.id }">
		            <tr>
		              <td>${result.account }</td>
		              <td>${result.cn_name }</td>
		              <td>${result.en_name }</td>
		              <td>
	          				<c:if test="${result.stype == 1 }">
		              			<span class="label label-danger" role="_status">未啟用</span>
		              		</c:if>
							<c:if test="${result.stype == 2 }">
		              			<span class="label label-success" role="_status">啟用</span>
		              		</c:if>
		                <span class="label label-danger" role="_status">
		                <c:if test="${result.stype==1 }">未啟用</c:if>
		                <c:if test="${result.stype==2 }">啓用</c:if>
		                </span>
		              </td>
		              <td>${empty result.updatedate ? result.createdate : result.updatedate} </td>
             			<td>${empty result.updateby ? result.createby : result.updateby} </td>
		              <td class="detailList-btn">
		                <a href="edit?id=${result.id }" title="編輯帳戶" class="pull-left icon-edit icon" target="_box"></a>
		                <a href="changepassword?id=${result.id }" title="修改密碼" class="pull-left icon-lock icon" target="_box"></a>
		                <a href="javascript:void(0)" title="${result.stype==1 ? '啟用帳戶' : '停用帳戶'} " data-id="${result.id }"  data-url="ajax_updateSType" role="_updateOp" class="${(result.stype==1)?'icon-ok-circle icon':'icon-ban-circle icon disable' }"  ></a>
		                <a href="javascript:void(0)" title="刪除" data-id="${result.id }" data-url="ajax_delete" class="pull-left icon-remove icon" role="_deleteOp" ></a>
		              </td>
		            </tr>
            	</c:if>
             </c:forEach> --%>
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


