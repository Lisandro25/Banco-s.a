<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cuenta"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Modificar Cuentas</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="container">
   <h3 class="titulo"> Modificar Cuenta </h3> 
   <% if(request.getAttribute("infoModify")!=null){
   		if(request.getAttribute("infoModify").equals(true)){%>
   			<p class="alert alert-success" role="alert">Cuenta Modificada Correctamente!</p>
   		<%} else {%>
   		<p class="alert alert-danger" role="alert">La cuenta no se pudo modificar, intente nuevamente!</p>
   		<%}%>
   <%}%>
 <% if(request.getAttribute("ModificarCuenta")!=null){%>
<form method="post" action="ServletAdmin">
	<table class="table table-sm">
		    <thead>
			    <tr>
		            <th>Nro Cuenta</th>
		            <th>Nro Cliente</th>
		            <th>Fecha de creacion</th>
		            <th>Tipo de cuenta</th>
		            <th>Cbu</th>
		            <th>Saldos</th>
		        </tr>
		    </thead>
		    <tbody>
	
		    	<%  
					ArrayList<Cuenta>  cuentaMod = null;
					
					if(request.getAttribute("ModificarCuenta")!=null)
					{
						cuentaMod = (ArrayList<Cuenta>) request.getAttribute("ModificarCuenta");
					}
		    		if(cuentaMod!=null){
						for(Cuenta c:cuentaMod) 
						{
							%>
						<tr>  
							<td><input class="form-control" type="text" name="nroCuentaM" readonly 
							value="<%=c.getNro_cuenta()%>"></td>    
							<td><input class="form-control" type="text" required name="nroClienteM" 
							value="<%=c.getNro_cliente()%>"></td>   
							<td><input class="form-control" type="text" required name="fechaCreacionM" 
							value="<%=c.getFecha_creacion().toString()%>"></td>
							<td>
								<%if(c.getTipo_cuenta().getTipo_cuenta() == 1){%>
									<select class="form-control" name="ddlTipoCuenta">
									  <option value="1" selected>Caja de ahorro</option>
									  <option value="2">Cuenta Corriente</option>
									</select>
								<%}else{%>
									<select class="form-control" name="ddlTipoCuenta">
									  <option value="1">Caja de ahorro</option>
									  <option value="2" selected>Cuenta Corriente</option>
									</select>
								<%}%>
							</td>   
							<td><input class="form-control" name="cbuM" required value="<%=c.getCbu()%>"></td>
							<td><input class="form-control" name="saldoM" required value="<%=c.getSaldo()%>"></td>
						</tr>
					<%  } 
					}
					%>
		    		
		    </tbody>
	
	</table>
	<!--<input class="btn btn-outline-success" type="submit" name="AceptarModificar" value="Aceptar" data-toggle="modal" data-target="#exampleModal">-->
	  <!-- Button trigger modal -->
	<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
	  Aceptar
	</button>
	<input class="btn btn-outline-danger" type="submit" name="RechazarModificar" value="Rechazar">
	
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
	        ¿Estas seguro de modificar este registro?
	      </div>
	      <div class="modal-footer">
	        <input type="submit" class="btn btn-secondary" data-dismiss="modal" name="RechazarModificar" 
	        value="Rechazar">
	        <input type="submit" name="AceptarModificar"  value="Aceptar" class="btn btn-primary">
	      </div>
	    </div>
	  </div>
	</div>
</form>
<%}%>
<% if(request.getAttribute("ModCuentas")!=null){%>
<form method="post" action="ServletAdmin">
    	<p>Buscar en: </p>
    	<div class="input-group mb-3 w-50">
		    	<select name="dllBusqueda" class="form-control">
				  <option value="todo">todo</option>
				  <option value="Nro_cuenta">Numero de Cuenta</option>
				  <option value="Nro_cliente">Numero de cliente</option>
				  <option value="Fecha_creacion">Fecha de creacion</option>
				  <option value="Descripcion">Tipo de cuenta</option>
				  <option value="Cbu">CBU</option>
				  <option value="Saldo">Saldo</option>
				</select>
				<input type="text" class="form-control" name="txtFiltro" placehorder="Busqueda">
    		<div class="input-group-append">
			    <input type="submit" name="btnModBuscar" value="Buscar" class="btn btn-outline-primary">
			    <input type="submit" name="modCuenta" value="Mostrar todo" class="btn btn-outline-primary">
    		</div>
    	</div>
</form>
	    <table id="mytable" class="table table-sm">
		    <thead>
			    <tr>
		            <th>Nro Cuenta</th>
		            <th>Nro Cliente</th>
		            <th>Fecha de creacion</th>
		            <th>Tipo de cuenta</th>
		            <th>Cbu</th>
		            <th>Saldo</th>
		            <th></th>
		        </tr>
		    </thead>
		    <tbody>
	
		    	<%  
					ArrayList<Cuenta> lista = null;
					
					if(request.getAttribute("ModCuentas")!=null)
					{
						lista = (ArrayList<Cuenta>) request.getAttribute("ModCuentas");
					}
		    		if(lista!=null){
						for(Cuenta c:lista) 
						{
							%>
						<tr>  
							<form action="ServletAdmin" method="post">
								<td><%=c.getNro_cuenta()%><input type="hidden" 
								name="nroCuenta" value="<%=c.getNro_cuenta()%>"></td>    
								<td><%=c.getNro_cliente()%><input type="hidden" 
								name="nroCliente" value="<%=c.getNro_cliente()%>"></td>   
								<td><%=c.getFecha_creacion()%><input type="hidden" 
								name="fechaCreacion" value="<%=c.getFecha_creacion()%>"></td>
								<td><%=c.getTipo_cuenta().getDescripcion()%><input type="hidden" 
								name="tipoCuenta" value="<%=c.getTipo_cuenta().getDescripcion()%>"></td>   
								<td><%=c.getCbu()%><input type="hidden" name="cbu" value="<%=c.getCbu()%>"></td>
								<td><%=c.getSaldo()%><input type="hidden" name="saldo" value="<%=c.getSaldo()%>"></td>
								<td><input type="submit" name="btnModificarCuenta" value="Modificar Cuenta" 
								class="btn btn-outline-secondary"
								 onclick="window.location.href='ServletAdmin?btnModificarCuenta=1&nroCuenta=<%=c.getNro_cuenta()%>'"></input></td>
							</form>
						</tr>
					<%  } 
					}
					%>
		    </tbody>
	</table>
	<%}%>
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