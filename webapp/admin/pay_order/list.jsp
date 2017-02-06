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
  	<jsp:param name="parentName" value="订单管理" />
  	<jsp:param name="parentUrl" value="list" />
  	<jsp:param name="currName" value="订单列表" />
  	<jsp:param name="hasPrint" value="false" />
  	<jsp:param name="hasExport" value="false" />
  </jsp:include>
</header>
  <div class="container-fluid clearfix">  
  	<jsp:include page="/include/leftnavi.jsp"></jsp:include>
     <!-- 主内容 -->
    <div class="main-container" id="mainContainer">
       <form id="searchForm" role="_searchForm" action="list" method="post">
       	 <p style="font-size: 30px;color: red; text-align: center;">该功能用于查询订单,并对异常订单进行补足.未支付的订单,才可补足订单,已完成的订单无法继续操作.</p>
         <input id="pageno" name="pageno" type="hidden" value="${page.pageno}">
         <div class="containerWrap center-block">
           <!-- 搜索栏 -->
           <div class="cm-f14 cm-pdt10 cm-tar cm-overflow">
           	<select name="status" id="status" class="cm-select-default cm-mr15" role="_defaultData" data-defaultValue="${param.status}">
          		<option value="">请订单状态</option>
          		<option value="1">已支付</option>
          		<option value="2">未支付</option>
          		<option value="3">补足订单</option>
          	</select>
           	<a href="javascript:void(0);" id="search-btn" role="_searchBtn" class="cm-btn-default search-btn cm-mr25" >搜索</a>
           </div>
           <!-- 列表 -->
           <table class="table table-striped table-detailList">
             <thead>
                <tr>
                   <th width="10%">id</th>
                   <th width="15%">订单帐号</th>
                   <th width="15%">订单类型</th>
                   <th width="10%">购买时长</th>
                   <th width="10%">价格</th>
                   <th width="5%">状态</th>
                   <th width="15%">创建時間</th>
                   <th style="border-right:none;">补足订单</th>
                </tr>
             </thead>
             <tbody>
             <c:forEach var="result" items="${page.results }">
	            <tr>
	              <td>${result.id }</td>
	              <td>${result.user_account }</td>
	              <td>
	              	<c:if test="${result.type==1 }">单个视频 </c:if>
	              	<c:if test="${result.type==2 }">购买用户vip </c:if>
	              	<c:if test="${result.type==3 }">打赏 </c:if>
	              </td>
	              <td>${result.month_length }</td>
	              <td>${result.amount} </td>
	              <td>
          				<c:if test="${result.status == 2 }">
	              			<span class="label label-danger" role="_status">未支付</span>
	              		</c:if>
						<c:if test="${result.status == 1 }">
	              			<span class="label label-success" role="_status">已完成</span>
	              		</c:if>
	              		<c:if test="${result.status == 3 }">
	              			<span class="label label-success" role="_status">补足订单</span>
	              		</c:if>
	              </td>
	              <td>${result.create_time} </td>
	              <td class="detailList-btn">
	              	<c:if test="${result.status == 2 }">
	                <a href="edit?id=${result.id }" title="补足订单" class="pull-left icon-edit icon" target="_box"></a>
	                </c:if>
	                <c:if test="${result.status != 2 }">
	                <a href="detail?id=${result.id }" title="查看详情" class="pull-left icon-search icon" target="_box"></a>
	              	</c:if>
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


