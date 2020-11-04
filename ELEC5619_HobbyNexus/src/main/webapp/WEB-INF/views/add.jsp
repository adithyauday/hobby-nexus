<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Add Event</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/hobby.css"/>">
	 <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	 
</head>
<body>
<jsp:include page="header.jsp" />
	<div class="container bs-docs-container" style="padding-top:100px;">
 	<div style="padding-top:100px;"><h3>The Detail of the Event</h3></div><br>
 	
 		<form action="/nexus/addevent" method="post">
 		<table>
 		<tr>
 			<th colspan="2"> Add an Event </th>
 		</tr>
 		<tr>
 		<td>Event Name</td>
 		<td>
 			<input type="text" name="event_name" />
 		</td>
 		</tr>
 		<tr>
 		<td>Event Description</td>
 		<td>
 			<input type="text" name="event_desc" />
 		</td>
 		</tr>
 		<tr>
 		<td>Skill Level</td>
 		<td>
 			<input type="text" name="skill_level_limit" />
 		</td>
 		</tr>
 		<tr>
 		<td>Number Limitation</td>
 		<td>
 			<input type="text" name="number_limit" />
 		</td>
 		</tr>
 		<tr>
 		<td>Location</td>
	 		<td>
	 			<input type="text" name="location" />
	 		</td>
 		</tr>
 		<tr>
	 		<td>Event Date (Format:yyyy-dd-mm hh:mm:ss)</td>
	 		<td>
	 			<input type="text" name="event_date" />
 		</td>
 		</tr>
 		<tr>
 		<td>Hobby</td>
	 		<td>
	 			<select name="hobby.hobby_id">
	 			<option>-SELECT Hobby-</option>
 	 			<c:forEach items="${hobbys }" var="hobby">
 	 			<option value="${hobby.hobby_id} "><c:out value="${hobby.hobby_name}"></c:out></option>
 	 			</c:forEach> 
					</select>
	 		</td>
 		</tr>
 		
 		<tr>
	 		<td colspan ='2'>
	 			<input type="submit" name="ADD" />
	 			
	 		</td>
 		</tr>
 		
 		</table>
 		</form>
<%--  		<p>${hobbys[0]["hobby_id"]}</p> --%>
 		
    </div>
</body>
</html>