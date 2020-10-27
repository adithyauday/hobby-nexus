<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/open-iconic/1.1.1/font/css/open-iconic-bootstrap.min.css" integrity="sha512-UyNhw5RNpQaCai2EdC+Js0QL4RlVmiq41DkmCJsRV3ZxipG2L0HhTqIf/H9Hp8ez2EnFlkBnjRGJU2stW3Lj+w==" crossorigin="anonymous" />

<%--Dont remove, needed for google api--%>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initMap">
</script>

<header>
<div class="navbar navbar navbar-expand-sm navbar-light bg-light justify-content-between fixed-top" id="navMenu">
	<div class="container-fluid">
	  <div class="navbar-header">
          <a class="navbar-brand" href="home">Hobby Nexus</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
  			</button>
          
      </div>
	  <div class="collapse navbar-collapse" id="myNavbar">
		  <form class="mx-2 my-auto d-inline w-100">
		   <div class="input-group">
		      <input type="text" class="form-control" placeholder="Search">
		       <span class="input-group-append">
		       	<button class="btn btn-outline-secondary" type="button">Search</button>
		       </span>
		    </div>
		  </form>
		<div class="navbar-nav ml-auto inline  "> 
		
			<!--  need to style link -->
			<c:if test="${user != null}">
			
			<form action="editProfile" method="post">
				<button type="submit" name="your_name" value="your_value" class="btn-link">
					<span class="oi oi-person"></span> 
					User Profile
				</button>
			</form>
			
			<form action="logout" method="post">
				<!-- Style this better -->
				<button type="submit" name="your_name" value="your_value" class="btn-link">
					<span class="oi oi-account-logout"></span> 
					Logout
				</button>
			</form>
			
			</c:if>
			
			<c:if test="${user == null}">
				<a class="nav-link" href="register"><span class="oi oi-account-login"></span> Login</a>
			</c:if>
			
		</div>
	 </div>
	</div>
</div>
</header>
