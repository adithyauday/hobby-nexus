<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<h1>
		Welcome to Hobby Nexus! 
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<div id="map" style="margin:auto;height:50%;width:60%;border-radius:60px;"></div>
</body>
</html>
