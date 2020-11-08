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
	 <link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
	 <link rel="stylesheet" href="<c:url value="/resources/css/post.css"/>">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container">
	<div class="container mr-3">

	<div class="article_box mt-3 mb-3">
		<div class="author pb-2">
			<img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${article.user.user_id }&type=user">
			<a href="javascript:openDetail('ne-user-${article.user.user_id}')">&nbsp;&nbsp;&nbsp;${article.user.user_name }</a>
		</div>
		<h2 style="font-size: 25px; color:green;">${article.title}</h2>
		<div ><p style="font-size: 20px;">${article.content}</p></div>
		<p style="font-size: 10px;"> Published on  ${article.create_time}</p>
	</div>
	
	<c:forEach var="comment" items="${article.comments}">
		<div class="comment_box  mb-3" style="padding:10px;">
			<a href="javascript:openDetail('ne-user-${comment.user.user_id}')"><img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${comment.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${comment.user.user_name }</a>
			<p>${comment.content }</p>
			<p style="font-size: 10px;">${comment.create_time }</p>
		</div>
	</c:forEach>
		
	<c:if test="${user != null}">	
		<div class="submit_box  mb-3" style="padding:15px;">
			<p style="font-size: 30px;">Add Comment</p>
			<form name="makeComment" action="makeComment/${article.artice_id}" class="text-info">
		    	Title:<input type="text" name="title" style="margin: 0 5px 15px;"><br/>
		    	<textarea  cols="50" rows="10" name="content" style="margin: 0 5px 15px;"></textarea><br/>
		    	<input type="submit" value="Submit">
			</form>	
		</div>
	</c:if>
	
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>