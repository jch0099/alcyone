<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${page.pageIndex.endindex>1}">
<nav role="_pager" data-currpagenum = "${page.pageno }" >
   <ul class="pager pager-pills pull-right cm-mr25">
      <li><a href="javascript:void(0);" id="prePage" >&laquo;</a></li>
	  <c:forEach var="v" varStatus="vs" begin="${page.pageIndex.startindex }" end="${page.pageIndex.endindex }">
		<c:choose>
		  <c:when test="${vs.index == page.pageno}">
			<li class="active"><a href="javascript:void(0);">${page.pageno}</a></li>
		  </c:when>
		  <c:otherwise>
			<li><a href="javascript:void(0);" data-page="${vs.index}">${vs.index}</a></li>
		  </c:otherwise>
		</c:choose>
	  </c:forEach>
      <li><a href="javascript:void(0);"  id="nextPage">&raquo;</a></li>  
   </ul>
</nav> 
</c:if>       