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
	 
</head>
<body>
<jsp:include page="header.jsp" />
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
						    <option value="1">1</option>
						    <option value="2">2</option>
						    <option value="3">3</option>
						    <option value="4">4</option>
						    <option value="5">5</option>
						    <option value="6">6</option>
						    <option value="7">7</option>
						    <option value="8">8</option>
						    <option value="9">9</option>
						    <option value="10">10</option>
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
			 		<td colspan ='2'><input type="submit" name="ADD" class="btn btn-primary " value="Create Hobby" /></td>
		 		</tr>
	 		
	 		</table>
	 		</form>
	 		
		<div id="map" style="height: 50%; width: 100%; border-radius: 20px;"></div>
		
 		</div>
    </div>
    
	
</body>
</html>