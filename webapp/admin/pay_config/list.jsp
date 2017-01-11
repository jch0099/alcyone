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
  	<jsp:param name="parentName" value="付费管理" />
  	<jsp:param name="parentUrl" value="list" />
  	<jsp:param name="currName" value="付费列表" />
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
           <!-- 列表 -->
           <table class="table table-striped table-detailList">
             <thead>
                <tr>
                   <th width="20%">类型值</th>
                   <th width="20%">类型名称</th>
                   <th width="20%">类型价格</th>
                   <th style="border-right:none;">操作</th>
                </tr>
             </thead>
             <tbody>
             <c:forEach var="result" items="${page.results }">
	            <tr>
	              <td>${result.type }</td>
	              <td>${result.name }</td>
	              <td>${result.amount }</td>
	              <td class="detailList-btn">
	                <a href="edit?id=${result.id }" title="編輯" class="pull-left icon-edit icon" target="_box"></a>
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


