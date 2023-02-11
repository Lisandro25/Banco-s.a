<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Movimiento"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Reportes</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="parteDer">
   <h3 class="titulo"> Reportes </h3> 

<form method="post" action="ServletAdmin">
<% if(request.getAttribute("errorPrestamo")!=null){
   		if(request.getAttribute("errorPrestamo").equals(true)){%>
   			<p class="alert alert-danger" role="alert">Debe completar ambas fechas para filtrar el prestamo</p>
   		<%} 
    }%>
    <% if(request.getAttribute("errorMovimiento")!=null){
   		if(request.getAttribute("errorMovimiento").equals(true)){%>
   			<p class="alert alert-danger" role="alert">Debe completar ambas fechas para filtrar el movimiento</p>
   		<%} 
    }%>
<%   int total=0;
     if(request.getAttribute("total")!=null) total = Integer.parseInt(request.getAttribute("total").toString()); %>
<div>
<br><br>
<span>Dinero total depositado en el banco actualmente: <b>$ <%= total %></b></span>
<br><br>
<span>Prestamos desde: </span><input type="text" name="presIni" placeholder="dd/mm/aaaa""  pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es v&aacute;lida"></input>
<span> Hasta: </span><input type="text" name="presFin" placeholder="dd/mm/aaaa""  pattern="\d{1,2}/\d{1,2}/\d{4}" title="La fecha no es v&aacute;lida"/></input>
<span>Filtrar por: </span>
<select name="filtroPre">
				<option value="Todo">Todos</option>
				<option value="1">Aprobados</option>
				<option value="2">Rechazados</option>
</select> 
<br><br>
<input type="submit" name="btnFiltrarPres" value="Filtrar" class="btn btn-primary" ></input>
<input type="submit" name="btnMostrarPres" value="Mostrar todos" class="btn btn-secondary" ></input>
<br><br>
<table class="table">
  <thead>
    <tr>
            <th scope="col">Nro Prestamo</th>
            <th scope="col">Nro Cliente</th>
            <th scope="col">Fecha de solicitud</th>
            <th scope="col">Importe solicitado</th>
            <th scope="col">Importe solicitado (con intereses)</th>
            <th scope="col">Nro de cuenta a depositar</th>
            <th scope="col">Plazo de pago (meses)</th>
            <th scope="col">Monto de pago (por mes)</th>
            <th scope="col">Cantidad de cuotas</th>
            <th scope="col">Estado</th>
    </tr>
  </thead>
  <tbody>
  <%  
				ArrayList<Prestamo> listaPrestamos = null;
				if(request.getAttribute("prestamos")!=null)
				{
					listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("prestamos");
				}
	    		if(listaPrestamos!=null){
					for(Prestamo c:listaPrestamos) 
					{
						%>
					<tr>  
						<td><%=c.getNro_prestamo()%></td>     
						<td><%=c.getNro_cliente().getNro_Cliente()%></td>   
						<td><%=c.getFecha()%></td>
						<td><%=c.getImp_solicitado()%></td> 
						<td><%=c.getImp_con_intereses() %></td>  
						<td><%=c.getNro_cuenta_deposito() %></td>
						<td><%=c.getPlazo_pago_meses() %></td>
						<td><%=c.getMonto_pago_por_mes() %></td>
						<td><%=c.getCant_cuotas() %></td>	
						<td><%=c.getEst_prestamo().getDescripcion()%></td>	
					</tr>
			<%  } 
			}
  %>
  </tbody>
</table>
<br><br>
<span>Movimientos desde: </span><input type="text" name="movIni" placeholder="dd/mm/aaaa"" pattern="\d{1,2}/\d{1,2}/\d{4}"  title="La fecha no es v&aacute;lida"/></input>
<span> Hasta: </span><input type="text" name="movFin" placeholder="dd/mm/aaaa"  pattern="\d{1,2}/\d{1,2}/\d{4}"  title="La fecha no es v&aacute;lida"/></input>
<span>Filtrar por: </span>
<select name="filtroMov">
				<option value="Todo">Todos</option>
				<option value="1">Altas de cuenta</option>
				<option value="2">Altas de prestamo</option>
				<option value="3">Pagos de prestamo</option>
				<option value="4">Transferencias</option>
</select> 
<br><br>
<input type="submit" name="btnFiltrarMov" value="Filtrar" class="btn btn-primary" ></input>
<input type="submit" name="btnMostrarMov" value="Mostrar todos" class="btn btn-secondary" ></input>
<br><br>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Nro movimiento</th>
      <th scope="col">Nro cuenta</th>
      <th scope="col">Fecha</th>
      <th scope="col">Tipo de movimiento</th>
      <th scope="col">Importe</th>
      <th scope="col">Detalle</th>
    </tr>
  </thead>
  <tbody>
  <%  
				ArrayList<Movimiento> listaMovimientos = null;
				if(request.getAttribute("movimientos")!=null)
				{
					listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("movimientos");
				}
	    		if(listaMovimientos!=null){
					for(Movimiento c:listaMovimientos) 
					{
						%>
					<tr>  
						<td><%=c.getNro_Movimiento()%></td>     
						<td><%=c.getNro_Cuenta()%></td>
						<td><%=c.getFecha()%></td> 
						<td><%=c.getTipo_Mov().getDescripcion() %></td>  
						<td><%=c.getImporte() %></td>
						<td><%=c.getDetalle() %></td>											
					</tr>
			<%  } 
			}
  %>
  </tbody>
</table>
</div>

</form>
   
</div>

</body>
</html>