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
<title>Abonar Prestamo</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  


<div>
   <h3 class="titulo"> Abonar prestamo </h3> 

		<%if(request.getAttribute("cuotaPagadassssss")!=null){
			if(request.getAttribute("cuotaPagada").equals(true)){%>
 			<p class="alert alert-success" role="alert">Cuota Pagada!</p>
 		<%} else {%>
 		<p class="alert alert-danger" role="alert">La cuota no se pudo pagar, intente nuevamente!</p>
 		<%}
 		}%>

	<table class="table table-sm">
		    <thead>
			    <tr>
			    	<th>Nro Prestamo</th>
		            <th>FechaSolicitud</th>
		            <th>Importe</th>
		            <th>ImpConInt</th>
		            <th>MontoPorMes</th>
		            <th>Cuotas</th>
		            <th></th>
		        </tr>
		    </thead>
		    <tbody>
	
		    	<%  
		    		ArrayList<Prestamo> listaPrestamos = null;	
		    						
					if(request.getAttribute("listaPrestamosPagar")!=null)
					{
						listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamosPagar");
					}
		    		if(listaPrestamos!=null){
						for(Prestamo p:listaPrestamos) 
						{
							%>
						<tr>  
							<form method="get" action="ServletCliente">
							<td><input class="form-control" type="text" readonly name="nroPrestamo" 
							value="<%=p.getNro_prestamo()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getFecha()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getImp_solicitado()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getImp_con_intereses()%>"></td>   
							<td><input class="form-control" type="text" readonly name="nroClienteM" 
							value="<%=p.getMonto_pago_por_mes()%>"></td>   
							<td><input class="form-control" type="text" readonly name="cantCuota" 
							value="<%=p.getCant_cuotas()%>">
							<input type="hidden" 
								name="nroCliente" value="<%=p.getNro_cliente().getNro_Cliente()%>">
							<input type="hidden" 
							name="nroCuenta" value="<%=p.getNro_cuenta_deposito()%>">
							</td>   
							<td>
							<input type="submit" name="btnPagarPrestamo" value="Pagar" class="btn btn-outline-success">
							</td>   

							<!-- Modal
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
							        ¿Estas seguro de pagar esta cuota?
							      </div>
							      <div class="modal-footer">
							        <input type="submit" class="btn btn-secondary" data-dismiss="modal" name="RechazarModificar" 
							        value="Rechazar">
							        
							      </div>
							    </div>
							  </div>
							</div>-->
							
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