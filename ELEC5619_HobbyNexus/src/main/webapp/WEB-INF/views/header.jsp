<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/header.css"/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/open-iconic/1.1.1/font/css/open-iconic-bootstrap.min.css" integrity="sha512-UyNhw5RNpQaCai2EdC+Js0QL4RlVmiq41DkmCJsRV3ZxipG2L0HhTqIf/H9Hp8ez2EnFlkBnjRGJU2stW3Lj+w==" crossorigin="anonymous" />

<%--Dont remove, needed for google api--%>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<!-- <script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initAutocomplete&libraries=places&v=weekly">
</script>
 -->
<!-- <script defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCfsB5BOxZG-MyxFJ_ecDHBewP46PCKpw&callback=initMap&libraries=&v=weekly"></script> -->

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
		  <form class="mx-2 my-auto d-inline w-100" action="search" method="post">
		   <div class="input-group">
		      <input type="text" class="form-control" placeholder="Search" name = "searchText">
		       <span class="input-group-append">
		       	<input type="submit" value="Search" name = "searchButton"/>
		       </span>
		    </div>
		  </form>
	  </div>
	  
	  <div class="navbar-nav ml-auto inline  "> 
			
		<div class="dropdown">
			<c:if test="${user != null}">
			    <button class="btn btn-link dropdown-menu-right dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    	${user.user_name}
			    </button>
			  
			    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton"> 
			    	<form action="editProfile" method="post">
						<button type="submit" name="your_name" value="your_value" class="btn-link dropdown-item">
							<span class="oi oi-person"></span> 
							Edit Profile
						</button>
					</form>
			
					<form >
						<button type="button" name="your_name" value="your_value" class="btn-link dropdown-item" data-toggle="modal" data-target="#myModal">
							<span class="oi oi-credit-card"></span> 
							Create Hobby
						</button>
					</form>
			
					<form action="logout" method="post">
						<button type="submit" name="your_name" value="your_value" class="btn-link dropdown-item">
							<span class="oi oi-account-logout"></span> 
							Logout
						</button>
					</form>
    			</div>
			</c:if>
		</div>
		
		
		<c:if test="${user == null}">
			<a class="nav-link" href="register"><span class="oi oi-account-login"></span> Login</a>
		</c:if>
			
	</div>
  </div>
 </div>


  <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <div class="modal-header">
          <h4 class="modal-title">Create Hobby</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
   
        <div class="modal-body">
            <form enctype="multipart/form-data" method="post" id="form1" name="form1" >

          <div class="form-group">
            <label for="recipient-name" class="control-label">Hobby Name<font style="color: red;">*</font>:</label>
            <input type="text" class="form-control" id="hobbyname">
          </div>

          <div class="form-group">
            <label for="recipient-name" class="control-label">Hobby Picture<font style="color: red;">*</font>:</label>
              <input id="file-fr"  style="display: block;" name="file" type="file" multiple>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Description<font style="color: red;">*</font>:</label>
            <textarea class="form-control"  id="description"></textarea>
          </div>
    </form>

   
        <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="sub()">Save message</button>
        </div>
   
      </div>
    </div>
  </div>
  
</div> 

  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
function sub(){
	var hobbyname=$("#hobbyname").val();
	if(hobbyname==""||hobbyname.length==0){
		alert("Hobby Name cannot be empty");
		return;
	}
	var fileInput = $('#file-fr').get(0).files[0];
	if(!fileInput){
		alert("Please select upload file!");
		return;
	}
	var description=$("#description").val();
	if(description==""||description.length==0){
		alert("Description cannot be empty");
		return;
	}
	$("#form1").ajaxSubmit({
		url:'<%=request.getContextPath()%>/savehobby?hobby_name='+hobbyname+'&hobby_desc='+description,
		dataType:'json',
		type:'post',
		success:function(res){
			if(res.result){
				alert(res.msg);
				$('#myModal').modal('hide');
				location.reload();
				window.location.href='<%=request.getContextPath()%>/hobby?hobby_id='+res.hobby_id;

			}else{
				alert(res.msg);
			}
		}
	});
}
</script>
</header>