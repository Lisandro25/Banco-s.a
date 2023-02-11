<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidades.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Mis Datos</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />    
<%Cliente c = (Cliente) session.getAttribute("datosCliente");
%>

<h2 class="text-center mt-4">Mis datos</h2>
<div class="d-flex flex-wrap w-100">
	<div class="col">
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Nombre y Apellido</span>
		   	<span><%=c.getNombre()%> <%=c.getApellido()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">DNI</span>
		   	<span><%=c.getDni()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">CUIL</span>
		   	<span><%=c.getCuil()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Direccion</span>
		   	<span><%=c.getDireccion()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Email</span>
		   	<span><%=c.getEmail()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Fecha de nacimiento</span>
		   	<span><%=c.getFecha_nac()%></span>
	   </div>
	</div>
	<div class="col">
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Telefono</span>
		   	<span><%=c.getTelefono()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Genero</span>
		   	<span><%=c.getCod_Genero().getDescripcion()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Nacionalidad</span>
		   	<span><%=c.getCod_nacionalidad().getDescripcion()%></span>
		   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Provincia</span>
		   	<span><%=c.getCod_provincia().getDescripcion()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Localidad</span>
		   	<span><%=c.getCod_localidad().getDescripcion()%></span>
	   </div>
	   <div class="list-group-item d-flex flex-column">
		   	<span class="h6">Usuario</span>
		   	<span><%=c.getUsuario()%></span>
	   </div>
	</div>
</div>

</body>
</html>