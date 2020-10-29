<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Event</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/hobby.css"/>">
	 <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	 
</head>
<body>
	<div class="container bs-docs-container" style="padding-top:100px;">
 	<div class="ne-hobby-name">
 	<jsp:include page="header.jsp" />
 	 <p>Event name:    ${event.event_name }</p>
     <p>Event Details: ${event.event_desc}</p>
     <p>Skill of limit: ${event.skill_level_limit}</p>
     <p>number of limit: ${event.number_limit}</p>
     <p>event date: ${event.event_date}</p>
     <p>attendance : ${event.attandance}</p>
     </div>
 	
 	<div class="ne-hobby-name">

          <nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top">			
          <ul class="section-nav" id="members">
			<c:forEach var="obj" items="${users}">
			<li class="toc-entry toc-h2" ><a href="javascript:openDetail('ne-user-${obj.user_id}')"><img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user_name}</a></li>
			<div style="display:none" class="ne-user-${obj.user_id}">
				<img alt="" width="90px" height="100px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id}&type=user">&nbsp;&nbsp;&nbsp;
				<dl>
				<dt>${obj.user_name}</dt>
				<dt>email:${obj.email}</dt>
				<dt>location:${obj.location }</dt>
				</dl>
			</div>
			</c:forEach>
			</ul>
          </nav>

        
      </div>
    </div>

        
      
    

</body>
</html>