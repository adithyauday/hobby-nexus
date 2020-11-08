<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>Home Page</title>
	<script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initMap&libraries=&v=weekly"></script>	
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
	<script>
	if(!localStorage.getItem("eventCount")){
		localStorage.setItem('eventCount', 0)
	}
	
	if(!localStorage.getItem("eventList")){
		Var eventMap = new Map()
		localStorage.setItem('eventList', eventMap)
	}	
	
	</script>
</head>
<body data-spy="scroll">
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<!-- <div class="godown-60" id="godown"></div> -->
	<div class="aspect-ratio-box">
  		<img src="<c:url value="/resources/images/displayBlur.png"/>"  />
  		<div class="centered">
  			<h1 class="mainHeading">Welcome to <span class="webName">HobbyNexus</span></h1>
  			<p class="subHeading pt-3 pb-3"> A platform connecting hobby enthusiasts all over the world</p>
			<div class="grid-container">
			  <div class="grid-item"><a href="#exploreSection"><button class="mainButton">Explore</button></a></div>
			  <c:if test="${user == null}">
			  <form action="register" method="post">
			  	<div class="grid-item"><button class="mainButton">Join us</button></div>
			  </form>
			  </c:if>
			  <c:if test="${user != null}">
			  	<form action="editProfile" method="post">
			  	<div class="grid-item"><button class="mainButton" type="submit">My Profile</button></div>
			  	</form>
			  </c:if>
			</div>
  		</div>
	</div>
	<div class=" main-container pl-4 pt-4" id="exploreSection">
		<div class="dots">&hellip;</div>
		<h3 class="center"><i> Find and Explore your interests!</i> </h3>
		
		<div class="grid-container">
		<a href='/nexus/hobbyList'>
		  <div class="grid-item2">
		  	<img class ="pb-3" width="260" height="200" src="<c:url value="/resources/images/hobby.jpg"/>"  /><br>
		  	<a>Hobbies</a><br>
		  	 <p class="desc">Learn hobbies you have always wanted to and share your skills with other members!</p>
		  </div>
		  </a>
		  <a href='/nexus/eventlist'>
		  <div class="grid-item2">
		  	<img class ="pb-3" width="260" height="200" src="<c:url value="/resources/images/event.jpg"/>"  /> <br>
		  	<a>Events</a><br>
		  	<p class="desc">Find and participate in local events and meet your fellow hobby enthusiasts!</p>
		  </div>
		  </a>
		</div>
	<div id="feed">
		<h2 class=headings>Events and people near you</h2>
		<div id="map" style="border-radius:20px"></div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
