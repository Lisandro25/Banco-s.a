<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Prestamos rechazados</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  



<div class="parteDer">
   <h3 class="titulo"> Prestamos rechazados </h3> 

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
         <td> 3 </td>
         <td> 5/12/2022 </td>
         <td> 12.000 </td>
         <td> 12414 </td>
         <td> 12 </td>
     </tr>

</table>

</form>
   
</div>

</body>
</html>