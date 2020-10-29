<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>Event list</title>
	
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container pl-4 pt-4">
	<div id="map" style="margin:auto;height:50%;width:60%;border-radius:60px;"></div>
		<c:if test="${user != null}">
		
		<div>
		<h3>The Upcoming events</h3>
		<a href="/nexus/event?event_id=1"> ${event.event_name} Listen-read-discuss strategy </a>
		
		<br><img src="<%=request.getContextPath()%>/resources/images/read.png" width="150" height="150" />
		<br>
		<br><a href="/nexus/event?event_id=2">  Blue Mountains Photography Day Tour</a>
		<br><img src="<%=request.getContextPath()%>/resources/images/bmt.jpg" width="150" height="150" />
		<br>
		<br><a href="/nexus/event?event_id=3">Yoga in Watsons bay</a>
		<br><img src="<%=request.getContextPath()%>/resources/images/yoga.jpg" width="150" height="150" />
	</div>
	<div>
	<c:forEach var="item" items="${event} ">
	${item}
	</c:forEach>
	</div>
	</c:if>
	
	</div>
	
	
	
	
</body>
</html>