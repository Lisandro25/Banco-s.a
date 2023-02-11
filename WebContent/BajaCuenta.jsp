<%@ page import="entidades.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Baja Cuentas</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  

<div class="parteDer">
   <h3 class="titulo"> Baja Cuenta </h3> 

<form method="post" action="ServletAdmin">

  		<% 
  		Cuenta cu = (Cuenta) request.getAttribute("cuenta");
  		
  		// Si la cuenta fue buscada, muestro su detalle y el boton eliminar
  		if(request.getAttribute("cuentaEncontrada")=="true") 
		{ 
			%>
			<input type="hidden" id="hiddenNroCuenta" name="hiddenNroCuenta" value="<%=cu.getNro_cuenta()%>">
			<p>  Nro Cuenta:  <%=cu.getNro_cuenta()%> </p>	
		    <p>  Nro Cliente: <%=cu.getNro_cliente()%> </p>
		    <p>  Tipo de cuenta: <%=cu.getTipo_cuenta().getDescripcion()%> </p>
			<p>  Cbu: <%=cu.getCbu()%></p>
			<p>  Fecha Alta: <%=cu.getFecha_creacion()%></p>
			<p>  Saldo inicial: $<%=cu.getSaldo()%></p>
			<br>
   				<input type="submit" name="btnEliminarCuenta" value="Eliminar Cuenta"></input></p>
			<%		
		}
  		// sino muestro el boton buscar
  		else
  		{
  			%>
			<p>  Ingrese el numero de cuenta que desea eliminar: 
			<input type="number" required name="NroCuentaAEliminar"></input>
			<input type="submit" name="btnBuscarCuenta" value="Buscar Cuenta"></input></p>
			<%		  			
  		}
  		
  		// Muestro un cartel si se bueco una cuenta y no se encontro
  		if(request.getAttribute("cuentaEncontrada")=="false") 
		{ 
			%>
			<h3>Cuenta no encontrada o dada de baja</h3>
			<%
		}			  		

  		// Muestro un cartel si se realizo la baja exitosamente
  		if(request.getAttribute("cuentaEliminada")=="true") 
		{ 
			%>
				<h3>Cuenta dada de baja exitosamente</h3>
			<%		  			
		}
  		%>
		
		<%
  		if(request.getAttribute("cuentaEliminada")=="false") 
		{ 
			%>
				<h3>error: no se pudo dar de baja la cuenta</h3>
			<%		  			
		}
  		%>

</form>
   
</div>

</body>
</html>