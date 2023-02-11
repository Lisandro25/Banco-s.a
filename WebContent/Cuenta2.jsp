<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\Plantilla.css"></jsp:include>
</style>
<title>Cuenta 2</title>
</head>
<body>

<jsp:include page="Encabezado.jsp" />  



<div class="parteDer">
   <h3 class="titulo"> Cuenta 2 </h3> 

<form method="post" action="TransferirDinero.jsp">

<b> Numero de cuenta </b> : 456 <br>
<b> Tipo de cuenta </b> : Caja de ahorro <br>
<b> Dinero Disponible </b>: $ 11.909,023 <br>
<b> CBU </b>: 01126009850664234212001450 <br>
<b> Alias </b> : SILLA.COCO.MANO <br><br>

<b> <big> ¿Desea realizar una transferencia? </big> </b><br>
<input type="submit" name="btnTransferir" value="Transferir Dinero" />
<br><br>

<h3 class="titulo"> Movimientos </h3><br><br> 

<table border ="1" >
<tr>
    <th> N° de Movimiento</th>
    <th> Fecha </th>
    <th> Tipo de movimiento </th>
    <th> Importe </th>
    <th> Descripcion </th>
</tr>

<tr> 
    <td> 2 </td>
    <td> 3/9/2022 </td>
    <td> Transferencia </td>
    <td> $30.000 </td>
    <td> Se realizo la tranferencia de 30mil pesos a la cuenta nº 12312412 </td>
</tr>
</table>


</form>
   
</div>

</body>
</html>