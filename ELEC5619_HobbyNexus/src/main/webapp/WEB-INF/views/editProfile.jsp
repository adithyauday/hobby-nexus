<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>User Profile</title>
	
	<script src="<c:url value="/resources/js/map.js"/>" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/editProfile.css"/>">
</head>
<body>
	<!-- include header -->
	<jsp:include page="header.jsp" />
	<div class="godown-60" id="godown"></div>
	<div class=" main-container">
	  <h2 class ="pageHeading"> Edit Information </h2>
		
	  <div class="container">
			<div class="row">
				
				<div class="col ml-3"> 
					<img alt="" width="350px" height="270px" src="<%=request.getContextPath()%>/imgDisplay?id=${user.user_id}&type=user">&nbsp;&nbsp;&nbsp;
					<form:form action="uploadImage/${user.user_id}" method="post"  enctype="multipart/form-data" class="form-signin">
						  <div class="pt-3 input-group">
							  <div class="input-group-prepend">
							    <button class="btn input-group-text" id="inputGroupFileAddon01">Upload</button>
							  </div>
							  <div class="custom-file">
							    <input type="file" name="file" class="custom-file-input" id="inputGroupFile01"
							      aria-describedby="inputGroupFileAddon01">
							    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
							  </div>
							</div>
					</form:form>
				</div>
				
				<div class="col-7 mr-4 pb-5 ">
				 <form:form action="changeUserDetails/${user.user_id}" method="post" modelAttribute="detailsForm" class="form-signin">
		
					<div class="row ml-0"> 
						<label for="fname">Name:</label><br>
  						<form:input class="form-control input-sm" path="user_name" type="text" id="fname" name="fname"  value="${user.user_name}" /><br> 
					</div>
					
					<div class="row ml-0 pt-4"> 
						<label for="location">Location:</label><br>
  						<form:input class="form-control input-sm" path="location" type="text" id="location" name="location" value="${user.location}" /><br>
					</div>
					
					<div class="row ml-0 pt-4"> 
						<label for="email">Email:</label><br>
  							<form:input class="form-control input-sm" path="email" type="text" id="email" name="email" value="${user.email}"/><br>
					</div>
					<div class="row ml-0 pt-4"> 
						<button class="btn btn-primary">Save Details</button>
					</div>
					  </form:form>
				</div>
			
			</div>
		</div>
	
	  
	  <!--<form:form action="changePass/${user.user_id}" method="post" modelAttribute="passForm" class="form-signin">
		<div class="container">
			<div class="row">
			
				<div class="col ml-3"> 
				</div>
				
				<div class="col-7 mr-4 pb-5 ">
				 
					<div class="row ml-0"><b> Change Password  </b></div>
					<br>
					
					<div class="row ml-0"> 
						<label for="oldpass">Old Password:</label><br>
  							<input class="form-control input-sm" type="password" id="oldpass" name="oldpass" value=""><br>
					</div>
					
					<div class="row ml-0 pt-4"> 
						<label for="newpass">New Password:</label><br>
  							<input class="form-control input-sm" type="password" id="newpass" name="newpass" value=""><br>
					</div>
					
					<div class="row ml-0 pt-4"> 
						<label for="confirmpass">Confirm Password:</label><br>
  							<input class="form-control input-sm" type="password" id="confirmpass" name="confirmpass" value=""><br>
					</div>
					
					<div class="row ml-0 pt-4"> 
						<button class="btn btn-primary " type="submit">Change Password</button>
					</div>
				</div>
			
			</div>
		</div>
	  </form:form>-->
	  
	</div>
	
</body>
</html>
