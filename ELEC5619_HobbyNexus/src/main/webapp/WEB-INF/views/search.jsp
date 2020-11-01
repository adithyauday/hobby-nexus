
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<br>
<head>
	<title>Search Page</title>
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
<div class="container pt-3 col-sm-8">
<c:if test="${!empty userList}">
	<h6>Search Results for ${searchedText}</h6>
</c:if>
<h5>People</h5>
<c:if test="${!empty userList}">
	<div class="container-fluid">	
	<div class="col-sm-3"></div>
	<div class="card" style="width:400px">
		<div class="list-group">
			<c:forEach items="${userList}" var="user">
      			<a href="/nexus/viewProfile?user_id=${user.user_id}" class="list-group-item list-group-item-action">${user.user_id} ${user.user_name}</a>  			
    		</c:forEach>
		</div>
	</div>
	</div>
</c:if>
<br>
<h5>Hobby</h5>
<c:if test="${!empty hobbyList}">
	<div class="container-fluid">	
	<div class="col-sm-3"></div>
	<div class="card" style="width:400px">
		<div class="list-group">
			<c:forEach items="${hobbyList}" var="hobby">
      			<a href="/nexus/hobby?hobby_id=${hobby.hobby_id}" class="list-group-item list-group-item-action">${hobby.hobby_id} ${hobby.hobby_name}</a>  			
    		</c:forEach>
		</div>
	</div>
	</div>
</c:if>
</div>
</body>
</html>