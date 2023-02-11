<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Prestamos pendientes </title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  



<div class="parteDer">
   <h3 class="titulo"> Prestamos pendientes </h3> 

<form method="post" action="ServletHTML">
<br>

<table border="1">
     <tr>
         <th> N° de Prestamo </th>
         <th> Fecha de Solicitud </th>
         <th> Importe Solicitado </th>
         <th> Nº de cuenta a depositar </th>
         <th> Cantidad de Cuotas </th>
     </tr>
     
     <tr>
         <td> 1 </td>
         <td> 3/11/2022 </td>
         <td> 15.000 </td>
         <td> 1241431 </td>
         <td> 9 </td>
     </tr>

</table>

</form>
   
</div>

</body>
</html>