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
 	
 	<jsp:include page="header.jsp" />
 	<div style="padding-top:100px;"><h3>The Detail of the Event</h3></div><br>
 	<div>
 	<table class="ne-hobby-article" style="padding-top:100px;" font-size:"16px">
 	<tr>
		<th>Event name: </th>
		<td>${event.event_name }</td>
	</tr>
	<tr>
		<th>Event Description: </th>
		<td> ${event.event_desc }</td>
	</tr>
	<tr>
		<th>Skill of limit: </th>
		<td> ${event.skill_level_limit }</td>
	</tr>
	<tr>	
		<th>Number of limit: </th>
		<td> ${event.number_limit }</td>
 	</tr>
 	<tr>	
		<th>event date: </th>
		<td> ${event.event_date }</td>
 	</tr>
 	 <tr>	
		<th>Attendance: </th>
		<td>
		 <c:forEach var="obj" items="${users}">
			<a href="javascript:openDetail('ne-user-${obj.user_id}')">&nbsp;&nbsp;&nbsp;<img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user_name}</a>
			<div style="display:none" class="ne-user-${obj.user_id}">
				<img alt="" width="90px" height="100px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id}&type=user">&nbsp;&nbsp;&nbsp;
				<dl>
				<dt>${obj.user_name}</dt>
				<dt>email:${obj.email}</dt>
				<dt>location:${obj.location }</dt>
				</dl>
			</div>
		</c:forEach></td>
		
 	</tr>
 	
 	</table>
 	</div>
 	<div>
 	<a href="/nexus/eventlist">
 	<input type="button" value="Back" style="float:410px;">
 	</a>
 	</div>	
    </div>
</body>
</html>