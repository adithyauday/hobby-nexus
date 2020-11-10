<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
	<title>Home Page</title>
	<script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initMap&libraries=&v=weekly"></script>	
	<%-- <script src="<c:url value="/resources/js/map.js"/>" ></script> --%>
	<script>
	let map, infoWindow;

	var userPos;
	function initMap() {
	  map = new google.maps.Map(document.getElementById("map"), {
	    center: { lat: -34.397, lng: 150.644 },
	    zoom: 15
	  });
	  infoWindow = new google.maps.InfoWindow();

	  // Try HTML5 geolocation.
	  if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(
	      (position) => {
	        const pos = {
	          lat: position.coords.latitude,
	          lng: position.coords.longitude
	        };
	        console.log(pos);
	        userPos = pos;
	        infoWindow.setPosition(pos);
	        infoWindow.setContent("You are here");
	        infoWindow.open(map);
	        map.setCenter(pos);
	      },
	      () => {
	        handleLocationError(true, infoWindow, map.getCenter());
	      }
	    );
	  } else {
	    // Browser doesn't support Geolocation
	    handleLocationError(false, infoWindow, map.getCenter());
	  }

	  const image = {
	    url:
	      "https://www.pngkey.com/png/detail/30-301664_calendar-emblem-events-icon-white-png.png",
	    // This marker is 32 pixels wide by 32 pixels high.
	    scaledSize: new google.maps.Size(32, 32),
	    // The origin for this image is (0, 0).
	    origin: new google.maps.Point(0, 0),
	    // The anchor for this image is the base of the flagpole at (0, 32).
	    anchor: new google.maps.Point(0, 32)
	  };
	  var features = [
	    {
	      position: new google.maps.LatLng(-33.781721, 151.1863),
	      type: "info"
	    },
	    {
	      position: new google.maps.LatLng(-33.791539, 151.1782),
	      type: "info"
	    },
	    {
	      position: new google.maps.LatLng(-33.79863, 151.1781),
	      type: "info"
	    },
	   	{
	      position: new google.maps.LatLng(-33.79762, 151.17083),
	      type: "info"
	    },
	   	{
	      position: new google.maps.LatLng(-33.7973, 151.16984),
	      type: "info"
	    }
	  ];
	 
	  //Auto adjust zoom based on markers
	  var bounds = new google.maps.LatLngBounds();



	 for (var i = 0; i < features.length; i++) {
		 contentString = 
			 "<div id='iw-container'>"+
	          "<h2 class='iw-content'><h2 class='iw-subTitle'> Event Title</h2>"+
	          "<div id=<h3 id='hobby_title'>Hobby</h3>"+
	          "<p class='iw-subTitle'>Description</p></div>"
	    const infowindow = new google.maps.InfoWindow({
	    content: contentString,
	    });
	    bounds.extend(features[i].position);
	    var marker = new google.maps.Marker({
	      position: features[i].position,
	      icon: image,
	      map: map
	    });
	    google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ 
	        return function() {
	            infowindow.setContent(content);
	            infowindow.open(map,marker);
	        };
	    })(marker,contentString,infowindow));  
	    google.maps.event.addListener(map, 'click', function() {
	        infowindow.close();
	      });
	        
	  }
	  
	  
	}
	function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	  infoWindow.setPosition(pos);
	  infoWindow.setContent(
	    browserHasGeolocation
	      ? "Error: The Geolocation service failed."
	      : "Error: Your browser doesn't support geolocation."
	  );
	  infoWindow.open(map);
	}
</script>
	<link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
	<script>
	if(!localStorage.getItem("eventCount")){
		localStorage.setItem('eventCount', 0)
	}
	
	if(!localStorage.getItem("eventList")){
		var eventMap = new Map();
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
