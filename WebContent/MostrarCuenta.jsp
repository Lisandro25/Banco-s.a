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
<title>Información de Cuenta</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  


<div class="parteDer">
   <h3 class="titulo"> Información de Cuenta </h3> 

<form method="post" action="#">

		  		<% 
		  		Cuenta cu = (Cuenta) request.getAttribute("cuenta");
		  		
		  		if(request.getAttribute("resultadoAlta")!=null) 
				{ 
		  			if(request.getAttribute("resultadoAlta")=="ok") 	
		  			{
						%>
						<p>  Nro Cuenta:  <%=cu.getNro_cuenta()%> </p>	
					    <p>  Nro Cliente: <%=cu.getNro_cliente()%> </p>
					    <p>  Tipo de cuenta: <%=cu.getTipo_cuenta().getTipo_cuenta()%> </p>
						<p>  Cbu: <%=cu.getCbu()%></p>
						<p>  Saldo inicial: $<%=cu.getSaldo()%></p>
					
						<h3>Alta exitosa</h3>
											
						<%
		  			}; 
		  			if(request.getAttribute("resultadoAlta")=="error") 	
		  			{
						%>
						<h3>Error en alta</h3>
						<%
		  			};
		  			if(request.getAttribute("resultadoAlta")=="errorCantidadCuentas") 	
		  			{
						%>
						<h3>No se puede realizar el alta porque el cliente ya tiene el máximo de cuentas permitido (3 por cliente)</h3>
						<%
		  			};
		  		}
		  		%>

	  

</form>
   
</div>

</body>
</html>