
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<br>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<head>
	<title>Hobby List Page</title>
	<jsp:include page="header.jsp" />
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>

<br>
<br>
<br>
<div class=" main-container">
<div class="container p-3 col-sm-8">

<h5>List of Hobbies</h5>

<div class="container-fluid">	
	<div class="col-sm-3"></div>
	<div class="card">
		<div class="list-group">
			<c:forEach items="${hobbyList}" var="hobby">
      			<a href="/nexus/hobby?hobby_id=${hobby.hobby_id}" class="list-group-item list-group-item-action">
    		 	<img alt="" width="50px" height="50px" src="<%=request.getContextPath()%>/imgDisplay?id=${hobby.hobby_id }&type=hobby">
    			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${hobby.hobby_name}<br>${hobby.hobby_desc}
    			<p class="text-right" style="margin-bottom: 0rem; font-style: italic"><i class='fas fa-calendar-alt'></i>   ${hobby.create_time}</p></a>
    		</c:forEach>
		</div>
	</div>
	</div>
</div>
</div>
</body>
</html>