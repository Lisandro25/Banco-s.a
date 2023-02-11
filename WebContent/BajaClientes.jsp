<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Baja Clientes</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="parteDer">
   <h3 class="titulo"> Baja Clientes </h3> 

	<form method="post" action="ServletAdmin">
	<p>  Ingrese el DNI del cliente que desea eliminar: <input type="text" required name="UsuarioEliminado"></input>
	     
	  <!--<input class="btn btn-outline-success" type="submit" name="AceptarEliminar" value="Aceptar" data-toggle="modal" data-target="#exampleModal">-->
	  <!-- Button trigger modal -->
	<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
	  Eliminar cliente
	</button>
	<input class="btn btn-outline-danger" type="reset" name="RechazarEliminar" value="Rechazar">
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
	aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Atencion!</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        ¿Estas seguro de eliminar este cliente?
	      </div>
	      <div class="modal-footer">
	        <input type="reset" class="btn btn-secondary" data-dismiss="modal" name="RechazarEliminar" 
	        value="Rechazar">
	        <input type="submit" name="AceptarEliminar"  value="Aceptar" class="btn btn-primary">
	      </div>
	    </div>
	  </div>
	</div>
	
	</form>
	<%  if(request.getAttribute("exito")!=null) {%>	<p class="alert alert-success" role="alert">Cliente Eliminado!</p> <%}%>
	<%  if(request.getAttribute("error")!=null) {%> <p class="alert alert-danger" role="alert">Error. El cliente NO existe</p> <%}%>
    
	      
</div>

</body>
</html>