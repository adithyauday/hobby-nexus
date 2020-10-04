<!-- Courtesy https://github.com/navinreddy20/TeluskoLiveProject -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- include header -->

<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/register.css"/>">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script></head>
	<script type="text/javascript" src="<c:url value="/resources/js/register.js"/>" ></script>

<body>
<jsp:include page="header.jsp" />
<div class="godown-60" id="godown"></div>
<div class="small-container">
<div class="container" id="container">

	<!-- Sign up -->
	<div class="form-container sign-up-container">
	<form:form action="addUser" method="post" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2> <br>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Username"
                            autofocus="true" required = "required"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="form-control" placeholder="Email" required = "required"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control"
                            placeholder="Password" required="required"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>
        
        <button>Sign Up</button>
    </form:form>
	</div>
	
	<!-- Sign in -->
	
	<div class="form-container sign-in-container">
	<form:form action="validate" method="post" modelAttribute="loginForm" class="form-signin" >
        <h2 class="form-heading">Log in</h2> <br>
        	<form:errors path="email" />
            <form:input name="email" path="email" type="email" class="form-control" placeholder="Email"
                   autofocus="true" required="required"/><br>
            <form:input name="password" path="password" type="password" class="form-control" placeholder="Password" required="required"/><br>

            <button>Sign In</button>
    </form:form>
    </div>
    
		<%-- <form action ="validate" method="POST">
			<h1>Sign in</h1>
			<input type="email" name="email" placeholder="Email" />
			<input type="password" name="password" placeholder="Password" />
			<a class="register" href="#">Forgot your password?</a>
			<button>Sign In</button>
		</form> --%>
	
	<!-- Transition -->
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>

</div>
<!-- https://codepen.io/colorlib/pen/rxddKy -->     
<script type="text/javascript">


const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});


</script>  
</body>
<jsp:include page="footer.jsp" />
