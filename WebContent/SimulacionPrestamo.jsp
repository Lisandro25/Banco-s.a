<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Simulacion de Prestamo</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

 

<div class="container">
<br>
   <h3 class="titulo"> Simulacion de prestamo </h3> 

<form method="get" action="ServletCliente">

       <%
       float importeTotal = -1;
       if(request.getAttribute("ImporteTotal")!=null){
    	   importeTotal = Float.parseFloat(request.getAttribute("ImporteTotal").toString());
       }
       
       int CantidadCuotas = -1;
       if(request.getAttribute("CantidadCuotas")!=null){
    	   CantidadCuotas = Integer.parseInt(request.getAttribute("CantidadCuotas").toString());
       }
       
       float PagarXmes = -1;
       if(request.getAttribute("PagarXmes")!=null){
    	   PagarXmes = Float.parseFloat(request.getAttribute("PagarXmes").toString());
       }
       
       float ImporteSolicitado = -1;
       if(request.getAttribute("ImporteSolicitado")!=null){
    	   ImporteSolicitado = Float.parseFloat(request.getAttribute("ImporteSolicitado").toString());
       }
       
       float InteresTotal = -1;
       if(request.getAttribute("InteresTotal")!=null){
    	   InteresTotal = Float.parseFloat(request.getAttribute("InteresTotal").toString());
       }

       int CuentaDestino = -1;
       if(request.getAttribute("CuentaDestino")!=null){
    	   CuentaDestino = Integer.parseInt(request.getAttribute("CuentaDestino").toString());
       }
       
       int NroPrestamo = -1;
       if(request.getAttribute("NroPrestamo")!=null){
    	   NroPrestamo = Integer.parseInt(request.getAttribute("NroPrestamo").toString());
    	   
   	   }
       
       %>
<br><br>

	
	<table id="myTable" class="table table-sm">
	 <thead>
	       <tr>
	            <th class="th">Nro Prestamo </th>
                <th class="th">Nro Cliente</th>
                <th class="th">Usuario</th>
	            <th class="th">Cuenta Destino</th>
	            <th class="th">Importe Solicitado</th>
	            <th class="th">Interes</th>
	            <th class="th">Importe Total c/ Int.</th>
	            <th class="th">Cantidad Cuotas</th>
	            <th class="th">Monto a pagar X Mes</th>
          </tr>
        </thead>
        
<tbody>
        
    <tr class="tr">  
	     <td class="td" ><%=NroPrestamo %> </td>    
	     <td class="td" ><%=session.getAttribute("Nrocliente") %> <input type="hidden" name="NroCliente" value="<%=session.getAttribute("Nrocliente")%>"> </td>
	     <td class="td" ><%=session.getAttribute("NombreUsuario") %> <input type="hidden" name="NombreUsuario" value="<%=session.getAttribute("NombreUsuario")%>"> </td>
	     <td class="td" ><%=CuentaDestino %> <input type="hidden" name="CuentaDestino" value="<%=CuentaDestino %>"> </td> 
	     <td class="td" > <b>$</b> <%=ImporteSolicitado%> <input type="hidden" name="ImporteSolicitado" value="<%=ImporteSolicitado%>"> </td> 
	     <td class="td" > <b>$</b> <%=InteresTotal %> </td> 
	     <td class="td" > <b>$</b> <%= importeTotal %> <input type="hidden" name="importeTotal" value="<%= importeTotal %>"> </td> 
	     <td class="td" ><%= CantidadCuotas %> <input type="hidden" name="CantidadCuotas" value="<%= CantidadCuotas %>"> </td> 
	     <td class="td" > <b>$</b> <%= PagarXmes %> </td>   
	     
	     
	     
	     
	</tr>
	
</tbody>
</table>
	     <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">Aceptar</button>
	    <input class="btn btn-outline-danger" type="submit" name="btnCancelarPrestamo" value="Cancelar"> 
	
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
	        ¿Estas seguro de pedir el Prestamo?
	      </div>
	      <div class="modal-footer">
	        <input type="submit" class="btn btn-secondary" data-dismiss="modal" name="Cancelar" 
	        value="Cancelar">
	        <input type="submit" name="btnConfirmarPrestamo"  value="Aceptar" class="btn btn-primary">
	      </div>
	    </div>
	  </div>
	</div>
	
    
</form>
 
</div>

</body>
</html>