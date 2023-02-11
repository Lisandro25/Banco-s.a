<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidades.Cuenta"%>
    <%@page import="entidades.TipoCuenta"%>
    <%@page import="entidades.Movimiento"%>
    <%@page import="entidades.TipoMovimiento"%>
    <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Cuenta 1</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  


<form method="get" action="ServletRomanNO_TOCAR">
<div class="container-sm border">
	<div class="row mt-3">
		<div class="col m-5 d-flex flex-column">
		<%if(request.getAttribute("InfoCuenta")!=null){
			Cuenta c = new Cuenta();
			c = (Cuenta) request.getAttribute("InfoCuenta");%>
			
			<input name="txtNro_cuenta" style="display: none;" readonly type="text" class="form-control" value="<%=c.getNro_cuenta()%>" aria-label="Username" aria-describedby="basic-addon1">
			
			<p class="h3 text-center mb-4">Informacion de la cuenta</p>
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-addon1">CBU</span>
			  </div>
			  <input readonly type="text" class="form-control" value="<%=c.getCbu()%>" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-addon1">Saldo</span>
			  </div>
			  <input readonly type="number" name="saldoActual" class="form-control" value="<%=c.getSaldo() %>" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-addon1">Tipo de cuenta</span>
			  </div>
			  <input readonly type="text" class="form-control" value="<%=c.getTipo_cuenta().getDescripcion() %>" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-addon1">Fecha de creacion</span>
			  </div>
			  <input readonly type="text" class="form-control" value="<%=c.getFecha_creacion() %>" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			
			<div class="accordion" id="accordionExample">
			  <div class="accordion-item">
			    <h2 class="accordion-header" id="headingTwo">
			      <button class="btn btn-primary btn btn-block" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			        Transferir dinero
			      </button>
			    </h2>
			    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
			      <div class="accordion-body m-3">
			      	<div class="input-group-sm mb-3">
					  <input name="txtCbu" type="number" class="form-control" placeholder="CBU" required>
					</div>
			        <div class="input-group input-group-sm mb-3">
					  <div class="input-group-prepend">
					    <span class="input-group-text">$</span>
					  </div>
					  <input name="txtImporte" type="number" min="0" class="form-control" placeholder="Importe" required>
					  <div class="input-group-append">
					    <span class="input-group-text">.00</span>
					  </div>
					</div>
					<button type="button" class="form-control" data-toggle="modal" data-target="#exampleModal">
					  Aceptar
					</button>
					
			      </div>
			    </div>
			  </div>
			</div>
			<%if(request.getAttribute("transferencia")!=null){
				if(request.getAttribute("transferencia").equals(true)){%>
					<p class="alert alert-success" role="alert">Transferencia realizada correctamente!</p>
				<%}else{%>
					<p class="alert alert-danger" role="alert">Hubo un error en la transferencia, intente nuevamente!</p>
				<%}%>
			
			<%}%>
		<%}%>
		</div>
		<div class="col m-5">
			<p class="h3 text-center mb-4">Movimientos</p>
			<%if(request.getAttribute("tablaMovimientos")!=null){
				%>
			<table id="mytable" class="table table-sm">
				<thead class="table-light">
					<tr>
						<th>Movimiento</th>
						<th>Importe</th>
						<th>Fecha</th>
						<th>Detalle</th>
					</tr>
				</thead>
				<tbody>
		    	<%  
					ArrayList<Movimiento> cuentaMov = null;
					
					if(request.getAttribute("tablaMovimientos")!=null)
					{
						cuentaMov = (ArrayList<Movimiento>) request.getAttribute("tablaMovimientos");
					}
		    		if(cuentaMov!=null){
						for(Movimiento m:cuentaMov) 
						{
							%>
						<tr>
							<td>  
								<%=m.getTipo_Mov().getDescripcion()%>
							</td>
							<td>  
								<%=m.getImporte()%>
							</td>
							<td>  
								<%=m.getFecha()%>
							</td>
							<td>  
								<%=m.getDetalle()%>
							</td>
						</tr>
					<%  } 
					}
					%>
		    		
		    	</tbody>
		    </table>
			<%}%>
		</div>
	</div>
</div>

<!-- Modal / POPUP BOOSTRAP-->
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
        ¿Estas seguro de transferir?
      </div>
      <div class="modal-footer">
        <input type="submit" class="btn btn-secondary" data-dismiss="modal" name="RechazarModificar" 
        value="Rechazar">
        <input name="aceptarTransferencia" type="submit" class="btn btn-primary" value="Aceptar">
      </div>
    </div>
  </div>
</div>
	
</form>


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