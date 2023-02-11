<%@ page import="entidades.Genero"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Alta Clientes</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="parteDer">
   <h3 class="titulo"> Alta Clientes </h3> 
   
<form method="post" action="ServletAdmin">
      <%   int nroCli=0;
           if(session.getAttribute("ncli")!=null) nroCli = Integer.parseInt(session.getAttribute("ncli").toString());
           if(request.getAttribute("nuevoNroCli")!=null) nroCli = Integer.parseInt(request.getAttribute("nuevoNroCli").toString());
      %>
	  <p>  Cliente Numero:  <%= nroCli %> </p>
      <p>  Nombre:  <input type="text" name="nombre" placeholder="Ingrese un nombre"
           maxlength="30" required pattern="[A-Za-zñÑ]+" title="Ingrese solo letras" />
           Apellido: <input type="text" name="apellido" placeholder="Ingrese un apellido"
           maxlength="30" required pattern="[A-Za-zñÑ]+" title="Ingrese solo letras" />
      <p>  Dni: <input type="number" name="dni" placeholder="Ingrese un DNI"
           required pattern = "[0-9]{8}" title="Ingrese un DNI valido"/>
           Cuil:  <input type="number" name="cuil" placeholder="Ingrese un CUIL"
           required pattern = "[0-9]{11}" title="Ingrese un CUIL valido"/>
           
	  <p>  Género: 
	  		<select required name="genero" title="Seleccione un genero">
	  		<% ArrayList <Genero> gList = null;
	           if(session.getAttribute("generos")!=null) gList=(ArrayList<Genero>)session.getAttribute("generos");
	           if(gList!=null)
	           for(Genero g : gList){ %>
				<option value="<%=g.getCod_genero()%>"><%=g.getDescripcion()%></option>
				<%}%>
			</select> 
	       Nacionalidad: 
	  		<select required name="nacionalidad" title="Seleccione una nacionalidad">
	  		<% ArrayList <Nacionalidad> nList = null;
	           if(session.getAttribute("nacionalidades")!=null) nList=(ArrayList<Nacionalidad>)session.getAttribute("nacionalidades");
	           if(nList!=null)
	           for(Nacionalidad n : nList){ %>
				<option value="<%=n.getCod_nacionalidad()%>"><%=n.getDescripcion()%></option>
				<%}%>
			</select> 
           
	  <p>  Fecha de Nacimiento: <input type="text" name="fechaNacimiento" placeholder="dd/mm/aaaa" required pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es v&aacute;lida"></input>

      <p>  Dirección:  <input type="text" name="direccion" placeholder="Ingrese una dirección"
           maxlength="50" required  />

	  <p>   Provincia: 
	  		<select required name="provincia" title="Seleccione una provincia">
	  		<% ArrayList <Provincia> pList = null;
	           if(session.getAttribute("provincias")!=null) pList=(ArrayList<Provincia>)session.getAttribute("provincias");
	           if(pList!=null)
	           for(Provincia p : pList){ %>
				<option value="<%=p.getCod_provincia()%>"><%=p.getDescripcion()%></option>
				<%}%>
			</select> 
			
	        Localidad: 
	  		<select required name="localidad" title="Seleccione una localidad">
				<% ArrayList <Localidad> lList = null;
	               if(session.getAttribute("localidades")!=null) lList=(ArrayList<Localidad>)session.getAttribute("localidades");
	               if(lList!=null)
	               for(Localidad l : lList){ %>
				    <option value="<%=l.getCod_localidad()%>"><%= l.getCod_provincia().getDescripcion()%> - <%=l.getDescripcion()%></option>
				<%}%>
			</select> 

           
      <p>  Email: <input type="email" required name="email" placeholder="Ingrese un EMAIL" title="Ingrese un EMAIL valido"></input></p>
      
           Teléfono: <input type="tel" required name="telefono" placeholder="Ingrese un numero telefonico" title="Ingrese un numero telefonico valido"></input></p>

      <p>  Usuario:  <input type="text" required name="usuario" placeholder="Usuario"
           maxlength="30" />

	  <p>  Contraseña: <input type="password" required name="contra" placeholder="Contraseña"></input></p>
	  <p>  Confirmar contraseña: <input type="password" required name="contra2" placeholder="Confirmar contraseña"></input></p>
	  
<!--<input class="btn btn-outline-success" type="submit" name="AceptarAgregar" value="Aceptar" data-toggle="modal" data-target="#exampleModal">-->
	  <!-- Button trigger modal -->
	<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
	  Agregar cliente
	</button>
	<input class="btn btn-outline-danger" type="reset" name="RechazarAgregar" value="Rechazar">
	
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
	        ¿Estas seguro de agregar este cliente?
	      </div>
	      <div class="modal-footer">
	        <input type="reset" class="btn btn-secondary" data-dismiss="modal" name="RechazarAgregar" 
	        value="Rechazar">
	        <input type="submit" name="AceptarAgregar"  value="Aceptar" class="btn btn-primary">
	      </div>
	    </div>
	  </div>
	</div>
</form>

	<%  if(request.getAttribute("exito")!=null) {%> <p class="alert alert-success" role="alert">Cliente Agregado!</p> <%}%>
	<%  if(request.getAttribute("error")!=null) {%> <p class="alert alert-danger" role="alert">No se pudo agregar el cliente. Cliente ya existente</p> <%}%>
	<%  if(request.getAttribute("errorContraseña")!=null) {%> <p class="alert alert-danger" role="alert">Error. Las contraseñas no coinciden</p> <%}%>
	<%  if(request.getAttribute("usuarioExistente")!=null) {%> <p class="alert alert-danger" role="alert">Error. El usuario ingresado ya existe</p> <%}%>
	<%  if(request.getAttribute("errorDNI")!=null) {%> <p class="alert alert-danger" role="alert">Error DNI</p> <%}%>

</div>

</body>
</html>