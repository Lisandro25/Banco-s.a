<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Transferir Dinero</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  



<div class="parteDer">
   <h3 class="titulo"> Transferir Dinero</h3> 

<form method="post" action="ServletHTML">

Fecha : <br>
Cuenta Destino : <input type="number" name="txtCuentaDestino" /> <br>
Detalle : <input type="text" name="txtDetalle" /><br>
Importe : <input type="number" name="txtImporte" /><br> <br>
<input type="submit" name="btnEnviar" value="Enviar Dinero">

</form>
   
</div>

</body>
</html>