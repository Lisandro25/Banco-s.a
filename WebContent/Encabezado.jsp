<head>
	<%@page import="entidades.Cuenta"%>
	<%@page import="java.util.ArrayList"%>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>	
	
	
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.21.1/dist/bootstrap-table.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/bootstrap-table@1.21.1/dist/bootstrap-table.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>
</head>

<div class="w-100">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <a class="navbar-brand" href="#">Banquito Fiel</a>
	  <ul class="navbar-nav w-100">
	  	<% String usu = (String) session.getAttribute("TipoUsurio");
	  	   if(session.getAttribute("usuarioAdmin").equals(true)) {
		%>
		   <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Clientes</a>
		      <div class="dropdown-menu">
		        <a class="dropdown-item" href="ServletAdmin?nuevoNroCli=1">Alta cliente</a>
		        <a class="dropdown-item" href="BajaClientes.jsp">Baja cliente</a>

		        <a class="dropdown-item" href="ServletAdmin?ParamModifCLI=1">Modificar cliente</a>
		        <a class="dropdown-item" href="ServletAdmin?ParamListarCLI=1">Listado clientes</a>

		       
		      </div>
		    </li>
		    <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Cuentas</a>
		      <div class="dropdown-menu">
		        <a class="dropdown-item" href="AltaCuenta.jsp">Alta cuenta</a>
		        <a class="dropdown-item" href="BajaCuenta.jsp">Baja cuenta</a>
		        <a class="dropdown-item" href="ServletAdmin?modCuenta=1">Modificacion cuenta</a>
		        <a class="dropdown-item" href="ServletAdmin?ParamLCU=1">Listado cuentas</a>
		      </div>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="ServletAdmin?LPrestamos=1">Prestamos</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="ServletAdmin?LReportes=1">Informes y/o Reportes</a>
		    </li>
		<% }
		   else {
		%>
		   <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Cuentas</a>
		      <div class="dropdown-menu">
		      	<%  
					ArrayList<Cuenta> lista = null;
					if(session.getAttribute("CuentasEnCuenta")!=null)
					{
						lista = (ArrayList<Cuenta>) session.getAttribute("CuentasEnCuenta");
					}
		    		if(lista!=null){
		    			int nro = 0;
						for(Cuenta c:lista) 
						{ nro++;%>
						<a class="dropdown-item" href="ServletRomanNO_TOCAR?RNcuenta=<%=c.getNro_cuenta()%>">Cuenta <%=nro%></a>
					  <%}
					 }%>
		      </div>
		    </li>
		    <li class="nav-item dropdown">
		      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Prestamos</a>
		      <div class="dropdown-menu">
		        <a class="dropdown-item" href="ServletCliente?SolicitarPrestamo=<%=session.getAttribute("nombreUsurio")%>">Solicitar prestamo</a>
		        <a class="dropdown-item" href="ServletCliente?jspAbonarPrestamo=1">Abonar Prestamo</a>
		        <a class="dropdown-item" href="MisPrestamos.jsp">Mis Prestamos</a>
		      </div>
		    </li>
		    <li class="nav-item">
		    	<a class="nav-link" href="MisDatos.jsp">Datos personales</a>
		    </li>
		<% }
		%>
			<li class="mt-0 mr-0 mb-0 ml-auto d-flex align-items-center" style="gap: 1rem;">
				<span style="color: white;">Usuario <%=session.getAttribute("nombreUsurio")%></span>
				<a href="Login.jsp" class="btn btn-danger"> Cerrar sesion</a>
			</li>
	  </ul>
	</nav>
</div>
