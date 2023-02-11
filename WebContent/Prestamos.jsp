<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Prestamo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Prestamos</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="parteDer">
   <h3 class="titulo"> Prestamos </h3>    
<form method="post" action="ServletAdmin">
	<% if(request.getAttribute("aceptado")!=null){
   		if(request.getAttribute("aceptado").equals(true)){%>
   			<p class="alert alert-success" role="alert">Solicitud de prestamo aceptada</p>
   		<%} 
    }%>
    <% if(request.getAttribute("rechazado")!=null){
   		if(request.getAttribute("rechazado").equals(true)){%>
   			<p class="alert alert-danger" role="alert">Solicitud de prestamo rechazada</p>
   		<%} 
    }%>
    	<p>Filtrar por: </p>
    	<div class="input-group mb-3 w-50">
		    	<select name="ddlFiltro" class="form-control">
				  <option value="Nro_prestamo">Numero de prestamo</option>
				  <option value="Nro_cliente">Numero de cliente</option>
				  <option value="fecha_dmy">Fecha de solicitud</option>
				</select>
				<input type="text" class="form-control" name="txtFiltro" placehorder="Busqueda">
    		<div class="input-group-append">
			    <input type="submit" name="filtrarPrestamos" value="Buscar" class="btn btn-outline-primary">
			    <input type="submit" name="LPrestamos" value="Mostrar todo" class="btn btn-outline-primary">
    		</div>
    	</div>
</form>
<table id="myTable" class="table table-sm">
	    <thead>
		    <tr>
	        <th>Nro Prestamo</th>
            <th>Nro Cliente</th>
            <th>Fecha de solicitud</th>
            <th>Importe solicitado</th>
            <th>Importe solicitado (con intereses)</th>
            <th>Nro de cuenta a depositar</th>
            <th>Plazo de pago (meses)</th>
            <th>Monto de pago (por mes)</th>
            <th>Cantidad de cuotas</th>
            <th>Estado</th>        
            <th></th>
            <th></th>
	        </tr>
	    </thead>
    <tbody>
	    
	    <%  
				ArrayList<Prestamo> lista = null;
				if(request.getAttribute("prestamos")!=null) lista = (ArrayList<Prestamo>) request.getAttribute("prestamos");

	    		if(lista!=null){
					for(Prestamo c:lista) 
					{
						%>
					<tr>
						<form action="ServletAdmin" method="get">
								<td><%=c.getNro_prestamo()%><input type="hidden" 
								name="nroPrestamo" value="<%=c.getNro_prestamo()%>"></td>    
								<td><%=c.getNro_cliente().getNro_Cliente() %><input type="hidden" 
								name="nroCliente" value="<%=c.getNro_cliente().getNro_Cliente()%>"></td>   
								<td><%=c.getFecha()%><input type="hidden" 
								name="fechaSolicitud" value="<%=c.getFecha()%>"></td>
								<td><%=c.getImp_solicitado()%><input type="hidden" name="impSol" value="<%=c.getImp_solicitado()%>"></td>
								<td><%=c.getImp_con_intereses()%><input type="hidden" 
								name="impInt" value="<%=c.getImp_con_intereses()%>"></td>   
								<td><%=c.getNro_cuenta_deposito()%><input type="hidden" name="cuentDep" value="<%=c.getNro_cuenta_deposito()%>"></td>
								<td><%=c.getPlazo_pago_meses()%><input type="hidden" name="plaPag" value="<%=c.getPlazo_pago_meses()%>"></td>
								<td><%=c.getMonto_pago_por_mes()%><input type="hidden" name="montPag" value="<%=c.getMonto_pago_por_mes()%>"></td>
								<td><%=c.getCant_cuotas()%><input type="hidden" name="cantCuo" value="<%=c.getCant_cuotas()%>"></td>
								<td><%=c.getEst_prestamo().getDescripcion()%><input type="hidden" name="cantCuo" value="<%=c.getEst_prestamo().getDescripcion()%>"></td>
								<td><input type="submit" name="btnAceptarSolicitud" value="Aceptar" 
								class="btn btn-outline-success"
								 onclick="window.location.href='ServletAdmin?btnAceptarSolicitud=1'"></input></td>
							    <td><input type="submit" name="btnRechazarSolicitud" value="Rechazar" 
							     class="btn btn-outline-danger"
								 onclick="window.location.href='ServletAdmin?btnRechazarSolicitud=1'"></input></td>
						 </form>
					</tr>
				<%  } 
				}
			%>
		 </tbody>
	</table>
   </div>
  </body>
</html>