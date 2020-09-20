<!-- Courtesy https://github.com/navinreddy20/TeluskoLiveProject -->
<!DOCTYPE html>
<html>
<head>
	<title>Register</title>
</head>
<body>
<form action ="addUser" method="get">
<center>
	<label><h1>Feedback Us</h1></label>
  	<input type="text" name="name" placeholder="Enter your name" required="" /><br><br>
  	<input type="email" name="email" placeholder="Enter your email address" required="" /><br><br>
  	<input type="password" name="password" placeholder="Enter your password" required=""/> <br><br>
	<input type="submit" value="submit">
</center>
</form>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/additional-methods.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
        $("form").validate({
            submitHandler: function(form) {
              return true;
            },
            errorElement: 'div',
            errorPlacement: function(error, element) {
              var placement = $(element).data('error');
              if (placement) {
                $(placement).append(error)
              } else {
                error.insertAfter(element);
              }
            }
          });
        });
      </script>
</body>
</html>