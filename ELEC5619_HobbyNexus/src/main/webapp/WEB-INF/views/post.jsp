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
	 <link rel="stylesheet" href="<c:url value="/resources/css/post.css"/>">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div style="padding-top:100px;"></div>

	<div class="w">
	<div class="article_box">
		<div class="author">
			<img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${article.user.user_id }&type=user">
			<a href="javascript:openDetail('ne-user-${article.user.user_id}')">&nbsp;&nbsp;&nbsp;${article.user.user_name }</a>
		</div>
		<h4 style="font-size: 35px;">${article.title}</h4>
		<div style="padding:10px;"><p style="font-size: 30px;">${article.content}</p></div>
		<p> Published on  ${article.create_time}</p>
	</div>
	<c:forEach var="comment" items="${article.comments}">
		<div class="comment_box" style="padding:10px;">
			<a href="javascript:openDetail('ne-user-${comment.user.user_id}')"><img alt="" width="30px" height="30px" src="<%=request.getContextPath()%>/imgDisplay?id=${comment.user.user_id }&type=user">&nbsp;&nbsp;&nbsp;${comment.user.user_name }</a>
			<p style="font-size: 30px;">${comment.content }</p>
			<p>${comment.create_time }</p>
		</div>
	</c:forEach>
		
	<div class="submit_box" style="padding:15px;">
		<p style="font-size: 30px;">Please Put Your Comment</p>
		<form name="makeComment" action="makeComment/${article.artice_id}" class="text-info">
	    	Title:<input type="text" name="title" style="margin: 0 5px 15px;"><br/>
	    	<textarea  cols="50" rows="10" name="content" style="margin: 0 5px 15px;"></textarea><br/>
	    	<input type="submit" value="Submit">
		</form>	
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>