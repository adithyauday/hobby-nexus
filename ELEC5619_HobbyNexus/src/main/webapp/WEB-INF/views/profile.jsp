<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>User Profile</title>
	
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/profile.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container">
		<h2 class ="pageHeading"> User Information </h2>
	  	<div class="container mb-5">
			<div class="row">
				
				<div class="col ml-3"> 
					<img alt="User Profile Image" width="350px" height="270px" src="<%=request.getContextPath()%>/imgDisplay?id=${selected_user.user_id}&type=user">&nbsp;&nbsp;&nbsp;
				</div>
				
				<div class="col-7 mr-4 pb-5 ">
				 
					<div class="row ml-0"> 
						Name: ${selected_user.user_name}
					</div>
					
					<div class="row ml-0 pt-4"> 
						Email: ${selected_user.email}
					</div>
					
					<div class="row ml-0 pt-4"> 
						Location: ${selected_user.location}
					</div>
					<div class="row ml-0 pt-4" >
		        	<h5 class="mb-3">Hobby Groups</h5>		        
		        	<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix-top">			
		          	<ul class="section-nav" id="members">
						<c:forEach var="obj" items="${hobbies}">
							<li class="toc-entry toc-h2" ><a href="/nexus/hobby?hobby_id=${obj.hobby_id}"><img alt="" width="20px" height="20px" src="<%=request.getContextPath()%>/imgDisplay?id=${obj.hobby_id }&type=hobby">&nbsp;&nbsp;&nbsp;${obj.hobby_name}</a></li>
						</c:forEach>
					</ul>
					</nav>
		        </div>
				</div>
				
			
			</div>
		
		</div>
	</div>
	
</body>
</html>
