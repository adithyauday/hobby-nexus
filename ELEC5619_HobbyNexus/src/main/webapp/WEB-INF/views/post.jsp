<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post</title>
	 <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/resources/js/hobby.js"/>"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div style="padding-top:100px;">
	</div>
	<div style="padding:15px;">
	<a href="javascript:openDetail('ne-user-${article.user.user_id}')"><img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${article.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${article.user.user_name }</a>
	<p>${article.title}</p>
	<div style="padding:10px;"><p>${article.content}</p></div>
	<p>By  ${article.create_time}</p>
	</div>
	<c:forEach var="comment" items="${article.comments}">
		<div style="padding:10px;">
				<p>${comment.user.user_name }</p>
				<p>${comment.content }</p>
				<p>${comment.create_time }</p>
		</div>
	</c:forEach>
	
	<div style="padding:15px;">
	<p>Please Put Your Comment</p>
	<form name="makeComment" action="makeComment/${article.artice_id}" class="text-info">
    	Title:<input type="text" name="title"><br/>
    	Content:<input type="text" name="content"><br/>
    	<input type="submit" value="Submit">
	</form>	
	</div>
	
</body>
</html>