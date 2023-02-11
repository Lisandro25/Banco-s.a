<%@ page import="entidades.Genero"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@page import="java.util.ArrayList"%>
<%@page import=" entidades.Cliente"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Modificación Clientes</title>



</head>
<body>

<jsp:include page="Encabezado.jsp" />  


<div class="parteDer">
   <h3 class="titulo"> Modificación Clientes </h3>
   
   <% if(request.getAttribute("ModifTrue")!=null){
   		if(request.getAttribute("ModifTrue").equals(true)){%>
   			<p class="alert alert-success" role="alert">Cliente Modificado Correctamente!</p>
   		<%} else {%>
   		<p class="alert alert-danger" role="alert">El cliente no se pudo modificar, intente nuevamente!</p>
   		<%}%>
   <%}%>

    <form method="post" action="ServletAdmin">
    <br>
       <p>  Ingrese el usuario del cliente que desea modificar: <input type="text" required name="txtUsuarioModificar"></input>
	    <input type="submit" name="btnBuscarUsuario" class="btn btn-outline-primary" value="Buscar Usuario"></input>
	    </p>
  
   </form>
	   <form action="ServletAdmin" method="post">
		    <input type="submit" name="btnMostrarTodo" class="btn btn-outline-primary" value="Mostrar Todo"></input>
		    </form>
		    <br>
	    
	    <table id="mytable"  class="table table-sm" >
	    	<thead>
			    <tr>
		            <th class="th">Nro Cliente</th>
		            <th class="th">Nombre</th>
		            <th class="th">Apellido</th>
		            <th class="th">Dni</th>
		            <th class="th">Cuil</th>
		            <th class="th">Direccion</th>
		            <th class="th">Telefono</th>
		            <th class="th">Fecha de nacimiento</th>
		            <th class="th">Genero</th>
		            <th class="th">Nacionalidad</th>
		            <th class="th">Provincia</th>
		            <th class="th">Localidad</th>
		            <th class="th">Email</th>
		            <th class="th">Usuario</th>
		            <th class="th">Contraseña</th>
		            <th class="th"></th>
		        </tr>
		   </thead>
	       <tbody>
	       
	       
        <!-- CARGA LA LISTA CON TODOS LOS CLIENTES -->
	        
	    <%ArrayList<Cliente>ListaClienteTodos = null;
	    
	    if(request.getAttribute("ListaClientes")!=null){
	    	ListaClienteTodos = (ArrayList<Cliente>)request.getAttribute("ListaClientes"); 
	    }
	    
	    if(ListaClienteTodos!=null)
        	  for(Cliente CLI : ListaClienteTodos){
        %>
        
    <tr class="tr">  
         <form action="ServletAdmin" method="post">
	     <td class="td" > <%=CLI.getNro_Cliente() %> </td>    
	     <td class="td" > <%=CLI.getNombre() %>  </td>
	     <td class="td" > <%=CLI.getApellido() %> </td> 
	     <td class="td" > <%=CLI.getDni() %> </td> 
	     <td class="td" > <%=CLI.getCuil() %> </td> 
	     <td class="td" > <%=CLI.getDireccion() %> </td> 
	     <td class="td" > <%=CLI.getTelefono() %> </td> 
	     <td class="td" > <%=CLI.getFecha_nac() %> </td> 
	     <td class="td" > <%=CLI.getCod_Genero() %> </td> 
	     <td class="td" > <%=CLI.getCod_nacionalidad() %> </td> 
	     <td class="td" > <%=CLI.getCod_provincia() %> </td> 
	     <td class="td" > <%=CLI.getCod_localidad() %> </td> 
	     <td class="td" > <%=CLI.getEmail() %> </td> 
	     <td class="td" > <%=CLI.getUsuario()%> <input type="hidden" name="hiddenUsuario" value="<%=CLI.getUsuario()%>"> </td> 
	     <td class="td" > <%=CLI.getUsuario().getContraseña() %> </td> 
	     <td class="td" > <input type="submit" name="btnModificarCliente" class="btn btn-outline-secondary" value="Modificar"></input> </td>
	     </form>
	</tr>
	<% }

	///CARGA LA LISTA SOLAMENTE CON EL/LOS USUARIOS BUSCADOS 		 
	    
	    ArrayList<Cliente> ClienteXuser = null;
	
	   if(request.getAttribute("CLIENTE")!=null){
		   ClienteXuser = (ArrayList<Cliente>) request.getAttribute("CLIENTE");
	   }
	   
	   if(ClienteXuser!=null)
		   for(Cliente C : ClienteXuser){
			
		%>	   
			 			 
    <tr>  
		 <form action="ServletAdmin" method="post">
	     <td> <%=C.getNro_Cliente() %>  </td>    
	     <td> <%=C.getNombre() %>  </td>
	     <td> <%=C.getApellido() %> </td> 
	     <td> <%=C.getDni() %> </td> 
	     <td> <%=C.getCuil() %> </td> 
	     <td> <%=C.getDireccion() %> </td> 
	     <td> <%=C.getTelefono() %> </td> 
	     <td> <%=C.getFecha_nac() %> </td> 
	     <td> <%=C.getCod_Genero() %> </td> 
	     <td> <%=C.getCod_nacionalidad() %> </td> 
	     <td> <%=C.getCod_provincia() %> </td> 
	     <td> <%=C.getCod_localidad() %> </td> 
	     <td> <%=C.getEmail() %> </td> 
	     <td> <%=C.getUsuario()%> <input type="hidden" name="hiddenUsuario" value="<%=C.getUsuario()%>"></td> 
	     <td> <%=C.getUsuario().getContraseña() %> </td>
	     <td> <input type="submit" name="btnModificarCliente" class="btn btn-outline-secondary" value="Modificar"
	     onclick="window.location.href='ServletAdmin?btnModificarCuenta=1&nroCuenta=<%=C.getUsuario()%>'"></input> </td>  
	     
	     </form> 
	</tr>	   
		<% }	
		   							
	   /// CARGA LA LISTA CON EL CLIENTE A MODIFICAR 
	   	   
	  ArrayList<Cliente> ClienteMODIFICAR = null;
	  if(request.getAttribute("ClienteModificar")!=null){
		  ClienteMODIFICAR = (ArrayList<Cliente>) request.getAttribute("ClienteModificar");
	  }
	  
	  if(ClienteMODIFICAR!=null)
		  for(Cliente CL : ClienteMODIFICAR){
	%>
		
	<tr>  
	    <form action="ServletAdmin" method="post">
		
	     <td> <input class="form-control" type="text" name="txtNroCliente" readonly value="<%=CL.getNro_Cliente() %>">  </td>    
	     <td> <input class="Tam" type="text" name="txtNombre" required pattern="[A-Za-zñÑ]+" title="Ingrese solo letras" value="<%=CL.getNombre() %>">  </td>
	     <td> <input class="Tam" type="text" name="txtApellido" required pattern="[A-Za-zñÑ]+" title="Ingrese solo letras" value="<%=CL.getApellido() %>"> </td> 
	     <td> <input class="Tam" type="number" name="txtDNI" required pattern = "[0-9]{8}" title="Ingrese un DNI valido" value="<%=CL.getDni() %>"> </td> 
	     <td> <input class="Tam" type="number" name="txtCUIL" required pattern = "[0-9]{11}" title="Ingrese un CUIL valido" value="<%=CL.getCuil() %>"> </td> 
	     <td> <input  type="text" name="txtDireccion" required value="<%=CL.getDireccion() %>"> </td> 
	     <td> <input class="Tam" type="tel" name="txtTelefono" required title="Ingrese un numero telefonico valido" value="<%=CL.getTelefono() %>"> </td> 
	     <td> <input class="Tam" type="text" name="txtFechaNac" required pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es valida" value="<%=CL.getFecha_nac() %>"> </td> 
	    
	     <td> <select name="ddlGenero">
	  		<% ArrayList <Genero> gList = null;
	           if(request.getAttribute("generos")!=null) gList=(ArrayList<Genero>)request.getAttribute("generos");
	           if(gList!=null)
	           for(Genero g : gList){ %>
				<option value="<%=g.getCod_genero()%>"><%=g.getDescripcion()%></option>
				<%}%>
			</select> </td> 
			
	     <td> <select name="ddlNacionalidad" >
	  		<% ArrayList <Nacionalidad> nList = null;
	           if(request.getAttribute("nacionalidades")!=null) nList=(ArrayList<Nacionalidad>)request.getAttribute("nacionalidades");
	           if(nList!=null)
	           for(Nacionalidad nac : nList){ %>
				<option value="<%=nac.getCod_nacionalidad()%>"><%=nac.getDescripcion()%></option>
				<%}%>
			</select> </td> 
			
	     <td> <select name="ddlProvincia" >
	  		<% ArrayList <Provincia> pList = null;
	           if(request.getAttribute("provincias")!=null) pList=(ArrayList<Provincia>)request.getAttribute("provincias");
	           if(pList!=null)
	           for(Provincia prov : pList){ %>
				<option value="<%=prov.getCod_provincia()%>"><%=prov.getDescripcion()%></option>
				<%}%>
			</select> </td> 
			
	     <td> <select name="ddlLocalidad" >
				<% ArrayList <Localidad> lList = null;
	               if(request.getAttribute("localidades")!=null) lList=(ArrayList<Localidad>)request.getAttribute("localidades");
	               if(lList!=null)
	               for(Localidad loc : lList){ %>
				    <option value="<%=loc.getCod_localidad()%>"><%= loc.getCod_provincia().getDescripcion()%> - <%=loc.getDescripcion()%></option>
				<%}%>
			</select>  </td> 
	     
	     <td> <input type="email" name="txtEmail" title="Ingrese un EMAIL valido" value="<%=CL.getEmail() %>"> </td> 
	     <td> <input class="Tam" type="text" name="txtUsuario" readonly value="<%=CL.getUsuario()%>"> </td> 
	     <td> <input class="Tam" type="text" name="txtContraseña" required value="<%=CL.getUsuario().getContraseña() %>"> </td>
	     
	     <td> <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">Aceptar	</button></td>
	      <td>   <input type="submit"  class="btn btn-outline-danger"  name="btnModificarCancelar" value="Cancelar"> </td>  
	           
	</tr>
	         
	         
	         <!-- MENSAJE DE CONFIRMACION -->
	           
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
	        ¿Estas seguro de modificar este registro?
	      </div>
	      <div class="modal-footer">
	        <input type="submit" class="btn btn-secondary" data-dismiss="modal" name="btnModificarCancelar" 
	        value="Rechazar">
	        <input type="submit" name="btnModificarAceptar"  value="Aceptar" class="btn btn-primary">
	      </div>
	    </div>
	  </div>
	</div>
	        </form>  
	    <%} %>
		</tbody>
	</table>
	

</div>

<script>
	$(document).ready( function () {
		$('#mytable').DataTable({
	    	"searching": false,
	    	"lengthMenu": [5, 10, 15, 30, 60],
	    	"language": {
	            "zeroRecords": "No se encontraron datos",
	            "infoEmpty": "No hay datos para mostrar",
	            "info": "Mostrando del _START_ al _END_, de un total de _TOTAL_ entradas",
	            "lengthMenu": "Mostrar _MENU_ registros",
	            "paginate": {
	                "first": "Primeros",
	                "last": "Ultimos",
	                "next": "Siguiente",
	                "previous": "Anterior"
	            },
	        },
	    });
	} );
</script>

</body>
</html>