<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banquito Fiel - Login</title>
</head>
<body style="height: 100vh;" class="bg-light">
		<form method="get" action="ServletLogin" class="h-100 d-flex align-content-center">
			<div class="card w-50 m-auto d-flex flex-column shadow">
				<h5 class="card-header text-center">Login</h5>
				<div class="w-75 container p-4 border border-0">
					<div class="d-flex flex-column mb-4">
						<span class="pb-2">Usuario</span>
						<input type="text" name="txtUsuario" class="border-top-0 border-right-0 border-left-0" 
						style="background-color: #efefef;">
					</div>
					<div class="d-flex flex-column mb-4">
						<span class="pb-2">Constraseña</span>
						<input type="password" name="txtPassword" class="border-top-0 border-right-0 border-left-0" 
						style="background-color: #efefef;"/>
					</div>
					<input type="submit" class="btn btn-primary m-auto w-50 d-block" name="btnLogin" value="Ingresar">
				</div>
		         <div style="width:100%;height:50px;float:center">
		         <% if (request.getAttribute("error")!=null){ %>
		         	<p style="text-align:center;font-size: xx-small; color: red;"><strong>Ingrese un usuario y contraseña correcta</strong></p>
		         <%} %>
		         <% if (request.getAttribute("usuarioInactivo")!=null){ %>
		         	<p style="text-align:center;font-size: xx-small; color: red;"><strong>Usuario Inactivo</strong></p>
		         <%} %>
		         </div>
			</div>
		</form>
</body>
</html>