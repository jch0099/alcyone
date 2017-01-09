<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>

<div class="navbar navbar-default navbar-static-top" role="navigation" style="height: 50px;">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="icon icon-bar"></span> <span class="icon icon-bar"></span> <span class="icon icon-bar"></span> </button>
      <a href="${path }/nologin/index" class="navbar-brand">Voicecard</a></div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <%-- <li class="back-li"><a href="${path }/platform/list" class="smoothScroll">BACK</a></li> --%>
        <c:if test="${null != _user.type && _user.type == 1 }"> <li><a href="${path }/admin/share" class="smoothScroll">SHARE</a></li></c:if>
        <c:if test="${null != _user }"> <li><a href="${path }/nologin/logout" class="smoothScroll">LOGOUT</a></li></c:if>
        <!-- <li><a href="#contact" class="smoothScroll">CONTACT</a></li> -->
      </ul>
    </div>
  </div>
</div>
</html>