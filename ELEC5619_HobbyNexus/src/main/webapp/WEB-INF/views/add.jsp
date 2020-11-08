<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Add Event</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/hobby.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
	 <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>
	 <script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initAutocomplete&libraries=places&v=weekly">
</script>
	<script src="<c:url value="/resources/js/autocompleteMap.js"/>" ></script>
	 <script>
	 var eventLoc;
	 function initAutocomplete() {
		  const map = new google.maps.Map(document.getElementById("map"), {
			    center: { lat: -33.8688, lng: 151.2195 },
			    zoom: 13,
			    mapTypeId: "roadmap",
			  });
			  var infoWindow = new google.maps.InfoWindow();
			  var userPos
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
			  // Create the search box and link it to the UI element.
			  const input = document.getElementById("location");
			  const searchBox = new google.maps.places.SearchBox(input);
			  
			  // Bias the SearchBox results towards current map's viewport.
			  map.addListener("bounds_changed", () => {
			    searchBox.setBounds(map.getBounds());
			  });
			  let markers = []; 
			  // Listen for the event fired when the user selects a prediction and retrieve
			  // more details for that place.
			  searchBox.addListener("places_changed", () => {
			    const places = searchBox.getPlaces();

			    if (places.length == 0) {
			      return;
			    }
			    // Clear out the old markers.
			    markers.forEach((marker) => {
			      marker.setMap(null);
			    });
			    markers = [];
			    // For each place, get the icon, name and location.
			    const bounds = new google.maps.LatLngBounds();
			    places.forEach((place) => {
			      if (!place.geometry) {
			        console.log("Returned place contains no geometry");
			        return;
			      }
			      const icon = {
			        url: place.icon,
			        size: new google.maps.Size(71, 71),
			        origin: new google.maps.Point(0, 0),
			        anchor: new google.maps.Point(17, 34),
			        scaledSize: new google.maps.Size(25, 25),
			      };
			      // Create a marker for each place.
			      markers.push(
			        new google.maps.Marker({
			          map,
			          icon,
			          title: place.name,
			          position: place.geometry.location,
			        })
			      );
			      
			      eventLoc = place.geometry.location;
			      console.log("The location of event is " + eventLoc);`

			      if (place.geometry.viewport) {
			        // Only geocodes have viewport.
			        bounds.union(place.geometry.viewport);
			      } else {
			        bounds.extend(place.geometry.location);
			      }
			    });
			    map.fitBounds(bounds);
			  });
			}
	 		
	 		/* var eventCount = localStorage.getItem("eventCount")
	 		var eventID = eventCount++ 
	 		var eventMap = localStorage.getItem("eventList")
	 		eventMap.set(eventID, eventLoc)
	 		localStorage.setItem("eventList", eventMap)	 		
			localStorage.setItem('eventCount', eventCount++)
			console.log(localStorage.getItem("eventList")) */
			 </script>
</head>
<body>
<jsp:include page="header.jsp" />
	
	<div class="godown-60" id="godown"></div>
	<div class=" main-container">
	 	<h2 class ="pageHeading">Add Event</h2><br>
	 		<div class="container">
	 		<form action="/nexus/addevent" method="post">
	 		<table>
	 		
		 		<tr>
			 		<td>Event Name</td>
			 		<td><input type="text" name="event_name" required /></td>
		 		</tr>
		 		
		 		<tr>
			 		<td>Event Description</td>
			 		<td><input type="text" name="event_desc" required /></td>
		 		</tr>
		 		
		 		<tr>
			 		<td>Skill Level</td>
			 		<td>
						  <select name="skill_level_limit" id="skill_level_limit">
						    <option value="Beginner">Beginner</option>
						    <option value="Intermediate">Intermediate</option>
						    <option value="Advanced">Advanced</option>
						    
						  </select>
			 		</td>
		 		</tr>
		 		
		 		<tr>
			 		<td>Number Limitation</td>
			 		<td><input type="number" name="number_limit" required /></td>
		 		</tr>
		 		
		 		<tr>
			 		<td>Location</td>
				 	<td><input type="text" name="location" id="location" required/></td>
			 	</tr>
			 	
		 		<tr>
			 		<td>Event Date (Format:yyyy-dd-mm hh:mm:ss)</td>
			 		<td><input type="text" name="event_date" required/></td>
		 		</tr>
		 		
		 		<tr>
		 		<td>Hobby</td>
			 		<td>
			 			<select name="hobby.hobby_id">
		 	 			<c:forEach items="${hobbys }" var="hobby">
		 	 			<option value="${hobby.hobby_id} "><c:out value="${hobby.hobby_name}"></c:out></option>
		 	 			</c:forEach> 
							</select>
			 		</td>
		 		</tr>
		 		
		 		
		 		<tr>
			 		<td colspan ='2'><input type="submit" name="ADD" class="btn btn-primary " value="Create Event" /></td>
		 		</tr>
	 		
	 		</table>
	 		</form>
	 		
		<div id="map" style="height: 50%; width: 100%; border-radius: 20px;"></div>
		
 		</div>
    </div>
    
	
</body>
</html>