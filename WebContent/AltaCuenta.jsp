<%@ page import="entidades.Genero"%>
<%@ page import="entidades.TipoCuenta"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Alta Cuentas</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  


<div class="parteDer">
   <h3 class="titulo"> Alta Cuenta </h3> 

<form method="post" action="ServletAdmin">

      <p>  Nro Cliente: <input type="number" required name="NroCliente"></input></p>
      <p>  Tipo de cuenta: 
      		<select required name="ddlCuentas">
		  		<% 
		  		   ArrayList <TipoCuenta> tcList = null;
		           if(session.getAttribute("TipoCuenta")!=null) tcList=(ArrayList<TipoCuenta>)session.getAttribute("TipoCuenta");
		           if(tcList!=null)
		           for(TipoCuenta tc : tcList){ %>
					<option value="<%=tc.getTipo_cuenta()%>"><%=tc.getDescripcion()%></option>
					<%}%>
      		</select></p>
      
	  <p>  Cbu: <input type="number"  required name="Cbu"></input></p>
	  <p>  Saldo inicial: $10.000</p>
	  <p>  <input type="submit" name="btnAltaCuenta" value="Alta Cuenta"></input></p>


</form>
   
</div>

</body>
</html>