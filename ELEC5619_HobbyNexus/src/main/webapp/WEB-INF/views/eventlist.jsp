<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>Event list</title>
	
	
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
</head>
<body>
	<!-- include header -->
	
	<jsp:include page="header.jsp" />
	<div id="map" style="margin:auto;height:50%;width:60%;border-radius:60px;"></div>
	<div class="godown-60" id="godown"></div>
	<div class=" main-container pl-4 pt-4">
	
		
		<c:if test="${user != null}">
		
		<div>
		<h3>The Upcoming events</h3>
		<form>
		<table border="1px" cellspacing="0" cellpadding="10">
		<tr>
			<th>Event Number</th>
			<th>Event Name</th>
			<th>Event Date</th>
		</tr>
		<c:forEach var="obj" items="${events}"  varStatus="s" >
		<tr>
			<td><c:out value="${obj.event_id}"></c:out></td>
			<td><a href="/nexus/event?event_id=${obj.event_id}"><c:out value ="${ obj.event_name}"></c:out></a></td>
			<td><c:out value="${obj.event_date}"></c:out></td>
		</tr>
		
		</c:forEach>
		<tr>
			<td colspan="3">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/nexus/addevent">Add</a>
			</td>
		</tr>
		</table>
		</form>
<!-- 		<a href="/nexus/event?event_id=1">  Listen-read-discuss strategy </a> -->
		
<%-- 		<br><img src="<%=request.getContextPath()%>/resources/images/read.png" width="150" height="150" /> --%>
<!-- 		<br> -->
<!-- 		<br><a href="/nexus/event?event_id=2">  Blue Mountains Photography Day Tour</a> -->
<%-- 		<br><img src="<%=request.getContextPath()%>/resources/images/bmt.jpg" width="150" height="150" /> --%>
<!-- 		<br> -->
<!-- 		<br><a href="/nexus/event?event_id=3">Yoga in Watsons bay</a> -->
<%-- 		<br><img src="<%=request.getContextPath()%>/resources/images/yoga.jpg" width="150" height="150" /> --%>
	</div>
	
	</c:if> 
	
	</div>
	
	
	
	
</body>
</html>