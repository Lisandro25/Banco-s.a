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
<title>Mis Prestamos</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="container">

<h3 class="titulo"> Mis Prestamos </h3> 

<br>

<form method="post" action="ServletAdmin">
	<table class="table table-sm">
		    <thead>
			    <tr>
		            <th>NroPrestamo</th>
		            <th>FechaSolicitud</th>
		            <th>Importe</th>
		            <th>ImpConInt</th>
		            <th>NroCuenta</th>
		            <th>MontoPorMes</th>
		            <th>Cuotas</th>
		            <th>EstadoPrestamo</th>
		        </tr>
		    </thead>
		    <tbody>
	
		    	<%  
		    		ArrayList<Prestamo> listaPrestamos = null;	
		    						
					if(session.getAttribute("misprestamos")!=null)
					{
						listaPrestamos = (ArrayList<Prestamo>) session.getAttribute("misprestamos");
					}
		    		if(listaPrestamos!=null){
						for(Prestamo p:listaPrestamos) 
						{
							%>
						<tr>  
							<td><input class="form-control" type="text" readonly name="nroCuentaM" readonly 
							value="<%=p.getNro_prestamo()%>"></td>    
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getFecha()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getImp_solicitado()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getImp_con_intereses()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getNro_cuenta_deposito()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getMonto_pago_por_mes()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getCant_cuotas()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getEst_prestamo().getDescripcion()%>"></td>   
						</tr>
					<%  } 
					}
					%>
		    		
		    </tbody>
	
	</table>
	
	
</form>

</body>
</html>