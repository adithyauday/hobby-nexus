<!-- Courtesy https://github.com/navinreddy20/TeluskoLiveProject -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	<div class="form-container sign-up-container">
		<form action ="addUser" method="get">
			<h1>Create Account</h1><br>
			<input type="text" name="name" placeholder="Enter your name" required="" /><br>
  			<input type="email" name="email" placeholder="Enter your email address" required="" /><br>
  			<input type="password" name="password" placeholder="Enter your password" required=""/> <br>
			<button>Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="#">
			<h1>Sign in</h1>
			<input type="email" placeholder="Email" />
			<input type="password" placeholder="Password" />
			<a class="register" href="#">Forgot your password?</a>
			<button>Sign In</button>
		</form>
	</div>
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
