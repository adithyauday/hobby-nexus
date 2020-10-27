<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>User Profile</title>
	<script type="text/javascript" src="<c:url value="/resources/js/map.js"/>"></script> 
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container pl-4 pt-4">
		<h2> Welcome To Hobby Nexus </h2>
		<c:if test="${user != null}">
		<h2> Hi, ${user.user_name} </h2>
		</c:if>
		<P >  The time on the server is ${serverTime}. </P>
	
	<div id="map" style="margin:auto;height:60%;width:70%;border-radius:60px;margin-bottom: 50px;"></div>
	</div>
	
	
</body>

</html>
