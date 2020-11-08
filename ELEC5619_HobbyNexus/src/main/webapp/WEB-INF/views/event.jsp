<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>Event</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/hobby.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>

</head>

<body>
		<jsp:include page="header.jsp" />
		<div class="godown-60" id="godown"></div>
		<div class=" main-container">
			<h2 class ="pageHeading">Event Details</h2>
		<div class="container">
			<table  >
				<tr>
					<th>Event name:</th>
					<td>${event.event_name }</td>
				</tr>
				<tr>
					<th>Description:</th>
					<td>${event.event_desc }</td>
				</tr>
				<tr>
					<th>Skill level:</th>
					<td>${event.skill_level_limit }</td>
				</tr>
				<tr>
					<th>Attendance limit:</th>
					<td>${event.number_limit }</td>
				</tr>
				<tr>
					<th>Event date:</th>
					<td>${event.event_date }</td>
				</tr>
				<tr>
					<th>Event location:</th>
					<td>${event.location }</td>
				</tr>
				<tr>
					<th>Attendance:</th>
					<td><c:forEach var="obj" items="${users}">
							<a href="javascript:openDetail('ne-user-${obj.user_id}')">&nbsp;&nbsp;&nbsp;<img
								alt="" width="20px" height="20px"
								src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id }&type=user">&nbsp;&nbsp;&nbsp;${obj.user_name}
							</a>
							<div style="display: none" class="ne-user-${obj.user_id}">
								<img alt="" width="90px" height="100px"
									src="<%=request.getContextPath()%>/imgDisplay?id=${obj.user_id}&type=user">&nbsp;&nbsp;&nbsp;
								<dl>
									<dt>${obj.user_name}</dt>
									<dt>email:${obj.email}</dt>
									<dt>location:${obj.location }</dt>
								</dl>
							</div>
						</c:forEach></td>

				</tr>

			</table>
			
		<div class ="mb-3 mt-3">
			<a href="/nexus/eventlist"> <button class="btn btn-primary " type="submit">Back</button>
			</a>
		</div>
		</div>
	</div>
</body>
</html>