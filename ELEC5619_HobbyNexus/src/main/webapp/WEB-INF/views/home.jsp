<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>User Profile</title>
	<script type="text/javascript" src="<c:url value="/resources/js/map.js"/>"></script> 
	<link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container pl-4 pt-4">
		<h1 id="title"> Welcome To Hobby Nexus </h1>
		<c:if test="${user != null}">
		<h2> Hi, ${user.user_name} </h2>
		</c:if>
		<%--<P >  The time on the server is ${serverTime}. </P> --%>
	<div id="feed">
		<h2 class=headings>Events and people near you</h2>
		<div id="map" style="border-radius:20px"></div>
	</div>
	</div>
	
</body>

</html>
