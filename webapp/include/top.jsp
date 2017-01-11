<%@ taglib uri="/jstl1.1/core.tld" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>

	<h1 class="logo" onclick="location.href='${path}/admin/video/list'" style="cursor: pointer;"></h1>
    <div class="navbar-wrapper">
       <!-- 顶部导航 -->
       <div class="navbar-wrapper-top clearfix">
          <ul id="topMenu" class="nav navbar-nav header-navbar-page clearfix">
          </ul>
          <ul class="nav navbar-nav header-navbar-user pull-right">
             <!--li>
                <a href="#" class="header-navbar-icon">
                   <i class="icon-envelope-alt"></i>
                   <span class="label label-badge">8</span>
                </a>
             </li-->
             <li class="cm-f14">
                <a href="#" class="cm-pdr25 clearfix" data-toggle="dropdown">
                   <i class="icon-user pull-left"></i><span class="pull-left cm-ml5">
	                   	${sessionScope._user.account }
	               </span><i class="pull-left icon-chevron-down icon-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-user cm-pd0" role="menu">
                   <!-- li>
                      <a href="#">个人资料</a>
                   </li -->
                   <li>
                      <a href="${path}/admin/changepassword" target="_box" title="修改密碼">修改密碼</a>
                   </li>
                   <li>
                      <a href="${path}/admin/logout">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出</a>
                   </li>
                </ul>
             </li>
          </ul>
       </div>
  		<!-- 登录信息 -->
	  <div class="navbar-wrapper-bottom cm-f14">
	     <ol class="breadcrumb pull-left" style="padding-left:15px;">
	        <li><a href="${param.parentUrl}">${param.parentName}</a></li>
	        <li class="active">${param.currName}</li>
	     </ol>
	     <!-- div class="pull-right printWrap">
	        <a href="javascript:void(0);"><img src="${platform}/images/excel_icon.png"></a>
	        <a href="javascript:void(0);"><img src="${platform}/images/print_icon.png"></a>
	     </div -->
	  </div>
  </div>