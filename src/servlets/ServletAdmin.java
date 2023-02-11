package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImp.CuentaDaoImp;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.EstadosPrestamo;
import entidades.Genero;
import entidades.Localidad;
import entidades.Movimiento;
import entidades.Nacionalidad;
import entidades.Prestamo;
import entidades.Provincia;
import entidades.TipoCuenta;
import entidades.Usuario;
import negocioImp.ClienteNegocioImp;
import negocioImp.CuentaNegocioImp;
import negocioImp.GeneroNegocioImp;
import negocioImp.LocalidadNegocioImp;
import negocioImp.MovimientoNegocioImp;
import negocioImp.NacionalidadNegocioImp;
import negocioImp.PrestamoNegocioImp;
import negocioImp.ProvinciaNegocioImp;
import negocioImp.UsuarioNegocioImp;
import Excep.DniInvalidoException;


@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAdmin() {
        super();
    }
    
    ClienteNegocioImp cneg = new ClienteNegocioImp();
	CuentaDaoImp cuneg = new CuentaDaoImp();
	CuentaNegocioImp neg = new CuentaNegocioImp();
	UsuarioNegocioImp uneg = new UsuarioNegocioImp();
	PrestamoNegocioImp pneg = new PrestamoNegocioImp();
	MovimientoNegocioImp mneg = new MovimientoNegocioImp();
	Cliente c = new Cliente();
	Usuario u = new Usuario();
	Genero g = new Genero();
	Nacionalidad n = new Nacionalidad();
	Provincia p = new Provincia();
	Localidad l = new Localidad();
	Prestamo pres = new Prestamo();
	EstadosPrestamo ep = new EstadosPrestamo();
	
	///FUNCION QUE CARGA LOS DATOS DE REPORTES ADMIN
	public void cargarReportes( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	    
		int dinero = mneg.dineroTotal();
		ArrayList<Prestamo> ListaPrestamos = (ArrayList<Prestamo>) pneg.readAll();
	    ArrayList<Movimiento> ListaMovimientos = (ArrayList<Movimiento>) mneg.readAll();
		request.setAttribute("total", dinero);
	    request.setAttribute("movimientos", ListaMovimientos);
	    request.setAttribute("prestamos", ListaPrestamos);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///LISTA REPORTES ADMIN
		if(request.getParameter("LReportes")!=null) {
			cargarReportes(request,response);
		    RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
		}
		
		///LISTA PRESTAMOS SOLICITADOS ADMIN
		if(request.getParameter("LPrestamos")!=null) {
		    ArrayList<Prestamo> ListaPrestamos = (ArrayList<Prestamo>) pneg.Solicitudes();
			request.setAttribute("prestamos", ListaPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
			rd.forward(request, response);
		}
		
		///ACEPTA SOLICITUD DE PRESTAMO ADMIN
        if(request.getParameter("btnAceptarSolicitud")!=null) {
			pres.setNro_prestamo(Integer.parseInt(request.getParameter("nroPrestamo").toString()));
		    if(pneg.aprobarPrestamo(pres)) request.setAttribute("aceptado", true);
			request.setAttribute("prestamos", pneg.Solicitudes());
			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
			rd.forward(request, response);
		}
        
        ///RECHAZA SOLICITUD DE PRESTAMO ADMIN
        if(request.getParameter("btnRechazarSolicitud")!=null) {
        	pres.setNro_prestamo(Integer.parseInt(request.getParameter("nroPrestamo").toString()));
		    if(pneg.rechazarPrestamo(pres)) request.setAttribute("rechazado", true);
			request.setAttribute("prestamos", pneg.Solicitudes());
			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
			rd.forward(request, response);
		}
        
        ///MUESTRA EL NUMERO DE CLIENTE PROXIMO A DAR DE ALTA
        if(request.getParameter("nuevoNroCli")!=null) {
     	    int maxId = cneg.obtenerProxId();
    		    request.setAttribute("nuevoNroCli", maxId);
 			RequestDispatcher rd = request.getRequestDispatcher("/AltaClientes.jsp");
 	        rd.forward(request, response);
 		} 
		
        
      //------------LISANDRO-----------//
        
		//AL HACER CLICK EN EL ITEM "Listar Cliente" EN EL MENU DEL ENCABEZADO SE CARGAN TODOS LOS CLIENTES Y 
        //REDIRIGE A LA VISTA DE LISTAR 
		if(request.getParameter("ParamListarCLI")!=null) {
	
		    ArrayList<Cliente> ListaClientes = cneg.MostrarTodos();
			
			request.setAttribute("ListaLISTAR_CLIENTE", ListaClientes);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
			
		//AL HACER CLICK EN EL ITEM "Modificar Cliente" EN EL MENU DEL ENCABEZADO SE CARGAN TODOS LOS CLIENTES Y 
        //REDIRIGE A LA VISTA DE MODIFICAR
		if(request.getParameter("ParamModifCLI")!=null) {
			
			ArrayList<Cliente> ListaClientes = cneg.MostrarTodos();
			request.setAttribute("ListaClientes", ListaClientes);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModifClientes.jsp");
			rd.forward(request, response);
		}
		
		
		// DEVUELVE UNA LISTA EN DONDE SE FILTRA A LOS CLIENTES POR GENERO
		if(request.getParameter("Genero")!=null) {
			int codigo = Integer.parseInt(request.getParameter("Genero"));
			ArrayList<Cliente> ListGeneros = cneg.CargarSegunCondicion("Genero",codigo);
			request.setAttribute("ListaLISTAR_CLIENTE", ListGeneros);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
		
		// DEVUELVE UNA LISTA EN DONDE SE FILTRA A LOS CLIENTES POR NACIONALIDAD
		if(request.getParameter("Nacionalidad")!=null) {
			int codigo = Integer.parseInt(request.getParameter("Nacionalidad"));
			ArrayList<Cliente> ListNacionalidad = cneg.CargarSegunCondicion("nacionalidad",codigo);
			request.setAttribute("ListaLISTAR_CLIENTE", ListNacionalidad);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
		
		
		// DEVUELVE UNA LISTA EN DONDE SE FILTRA A LOS CLIENTES POR PROVINCIA
		if(request.getParameter("Provincia")!=null) {
			int codigo = Integer.parseInt(request.getParameter("Provincia"));
			ArrayList<Cliente> ListProvincia = cneg.CargarSegunCondicion("provincia",codigo);
			request.setAttribute("ListaLISTAR_CLIENTE", ListProvincia);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
//--------------------------------------------------------------------------------------------------------------//
		
		
		
	    if(request.getParameter("ParamLCU")!=null) {
				
	        request.setAttribute("cuentas", cuneg.obtenerCuentas());
				
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");   
		    rd.forward(request, response);
		}
	    
	    if(request.getParameter("modCuenta")!=null) {
	    	
			request.setAttribute("ModCuentas", neg.obtenerCuentas());
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");
	        rd.forward(request, response);
		}
	    
    	if(request.getParameter("btnModificarCuenta")!=null) {
	    	
	    	ArrayList<Cuenta> lista = (ArrayList<Cuenta>) neg.obtenerCuentaPorNr_cuenta(request.getParameter("nroCuenta"));
	    	
	    	request.setAttribute("ModificarCuenta", lista);
	    	System.out.println("entro mod cuenta");
	    	RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");   
	        rd.forward(request, response);
	    } 	
    	
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		///FILTRA LOS PRESTAMOS EN REPORTES ADMIN
		if(request.getParameter("btnFiltrarPres")!=null) {
			ArrayList<Prestamo> ListaPrestamos = null;
			if(request.getParameter("presIni").toString().equals("") && request.getParameter("presFin").toString().equals("")) ListaPrestamos = (ArrayList<Prestamo>) pneg.prestamoXfecha("", "", request.getParameter("filtroPre").toString());
			else if(request.getParameter("presIni").toString().length()>0 && request.getParameter("presFin").toString().length()>0) {
				  String fechaIni[] = request.getParameter("presIni").toString().split("/");
				  String fecha1 = fechaIni[2]+"/"+fechaIni[1]+"/"+fechaIni[0];
				  String fechaFin[] = request.getParameter("presFin").toString().split("/");
				  String fecha2 = fechaFin[2]+"/"+fechaFin[1]+"/"+fechaFin[0];
			      ListaPrestamos = (ArrayList<Prestamo>) pneg.prestamoXfecha(fecha1, fecha2, request.getParameter("filtroPre").toString());
			}
			else {
			    ListaPrestamos = (ArrayList<Prestamo>) pneg.readAll();
				request.setAttribute("errorPrestamo", true);
			}
			int dinero = mneg.dineroTotal();
		    ArrayList<Movimiento> ListaMovimientos = (ArrayList<Movimiento>) mneg.readAll();
			request.setAttribute("prestamos", ListaPrestamos);
			request.setAttribute("total", dinero);
		    request.setAttribute("movimientos", ListaMovimientos);
			RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
		}
		
		///FILTRA LOS MOVIMIENTOS EN REPORTES ADMIN
		if(request.getParameter("btnFiltrarMov")!=null) {
			ArrayList<Movimiento> ListaMovimientos = null;
			if(request.getParameter("movIni").toString().equals("") && request.getParameter("movFin").toString().equals("")) ListaMovimientos = (ArrayList<Movimiento>) mneg.movimientoXfecha("", "", request.getParameter("filtroMov").toString());
			else if(request.getParameter("movIni").toString().length()>0 && request.getParameter("movFin").toString().length()>0) {
				String fechaIni[] = request.getParameter("movIni").toString().split("/");
				String fecha1 = fechaIni[2]+"/"+fechaIni[1]+"/"+fechaIni[0];
				String fechaFin[] = request.getParameter("movFin").toString().split("/");
				String fecha2 = fechaFin[2]+"/"+fechaFin[1]+"/"+fechaFin[0];
			    ListaMovimientos = (ArrayList<Movimiento>) mneg.movimientoXfecha(fecha1, fecha2, request.getParameter("filtroMov").toString());
			}
			else {
			    ListaMovimientos = (ArrayList<Movimiento>) mneg.readAll();
				request.setAttribute("errorMovimiento", true);
			}
			int dinero = mneg.dineroTotal();
			ArrayList<Prestamo> ListaPrestamos = (ArrayList<Prestamo>) pneg.readAll();
			request.setAttribute("movimientos", ListaMovimientos);
			request.setAttribute("total", dinero);
		    request.setAttribute("prestamos", ListaPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
		}
		
		///BOTON MOSTRAR TODOS LOS MOVIMIENTOS EN REPORTES ADMIN
		if(request.getParameter("btnMostrarMov")!=null) {
			cargarReportes(request,response);
		    RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
		}
		
		///BOTON MOSTRAR TODOS LOS PRESTAMOS EN REPORTES ADMIN
		if(request.getParameter("btnMostrarPres")!=null) {
			cargarReportes(request,response);
		    RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
		}
		
		///FILTRA LOS PRESTAMOS SOLICITADOS EN PRESTAMOS ADMIN
		if(request.getParameter("filtrarPrestamos")!=null) {
		    ArrayList<Prestamo> ListaPrestamos = (ArrayList<Prestamo>) pneg.obtenerPrestamosQueryCustom(request.getParameter("ddlFiltro").toString(), request.getParameter("txtFiltro").toString());
			request.setAttribute("prestamos", ListaPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
			rd.forward(request, response);
		}
		
		///CARGA LA LISTA DE PRESTAMOS SOLICITADOS EN PRESTAMOS ADMIN
		if(request.getParameter("LPrestamos")!=null) {
		    ArrayList<Prestamo> ListaPrestamos = (ArrayList<Prestamo>) pneg.Solicitudes();
			request.setAttribute("prestamos", ListaPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Prestamos.jsp");
			rd.forward(request, response);
		}
		
		///CONFIRMA EL ALTA DEL CLIENTE
            if(request.getParameter("AceptarAgregar")!=null) {
            boolean alta = true;
            
			if(request.getParameter("contra").toString().compareTo(request.getParameter("contra2").toString())!=0)
			{
				alta = false;
				request.setAttribute("errorContraseña", alta);
				int maxId = cneg.obtenerProxId();
	   		    request.setAttribute("nuevoNroCli", maxId);
				RequestDispatcher rd = request.getRequestDispatcher("/AltaClientes.jsp");   
			    rd.forward(request, response);
			    return;
			}
			
			// Si el dni es invalido, genero una excepcion del tipo DniInvalidoException
			if(request.getParameter("dni").toString()!=null) {
				try {
					if(!verificarDniInvalido(request.getParameter("dni").toString()))
					{
						c.setDni(request.getParameter("dni").toString());
					}
				}
				catch (DniInvalidoException e) 
				{
		   		    request.setAttribute("errorDNI", e.getMessage());
					RequestDispatcher rd = request.getRequestDispatcher("/AltaClientes.jsp");   
				    rd.forward(request, response);
				    return;
				}
				
			}

			
			u.setContrasenia(request.getParameter("contra").toString());
			u.setUsuario(request.getParameter("usuario").toString());
			
			if(uneg.existeNombreUsuario(u)) {
				alta = false;
				request.setAttribute("usuarioExistente", alta);
				int maxId = cneg.obtenerProxId();
	   		    request.setAttribute("nuevoNroCli", maxId);
				RequestDispatcher rd = request.getRequestDispatcher("/AltaClientes.jsp");   
			    rd.forward(request, response);
			    return;
			}

			g.setCod_genero(Integer.parseInt(request.getParameter("genero").toString()));
			n.setCod_nacionalidad(Integer.parseInt(request.getParameter("nacionalidad").toString()));
			p.setCod_provincia(Integer.parseInt(request.getParameter("provincia").toString()));
			l.setCod_localidad(Integer.parseInt(request.getParameter("localidad").toString()));
				
			c.setNombre(request.getParameter("nombre").toString());
			c.setApellido(request.getParameter("apellido").toString());
			c.setCuil(request.getParameter("cuil").toString());			
			c.setCod_Genero(g);
			c.setCod_nacionalidad(n);
			c.setFecha_nac(request.getParameter("fechaNacimiento").toString());
			c.setDireccion(request.getParameter("direccion").toString());
			c.setCod_provincia(p);
			c.setCod_localidad(l);
			c.setEmail(request.getParameter("email").toString());
			c.setTelefono(request.getParameter("telefono").toString());
			c.setUsuario(u);
			c.setEstado(true);
			
			if(cneg.insert(c) == false) {
				alta = false;
				request.setAttribute("error", alta);
				int maxId = cneg.obtenerProxId();
	   		    request.setAttribute("nuevoNroCli", maxId);
			}
			
			if(alta==true) {
				int maxId = cneg.obtenerProxId();
				Cuenta cu = new Cuenta();
		    	CuentaNegocioImp cn = new CuentaNegocioImp();
		    	cu.setNro_cliente(maxId);
		    	cu.setTipo_cuenta(new TipoCuenta(1,""));
		        cu.setCbu(cn.CbuAleatorio());
		        cu.setSaldo(10000);
		    	cu.setEstado(true);
		    	cn.insert(cu);
				request.setAttribute("exito", alta);
	   		    request.setAttribute("nuevoNroCli", maxId);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/AltaClientes.jsp");   
		    rd.forward(request, response);
		        
       }
       
       ///BOTON DE ELIMINAR CLIENTE
       if(request.getParameter("AceptarEliminar")!=null) {
			boolean baja = false;
			Cliente c = new Cliente();
			c.setDni(request.getParameter("UsuarioEliminado").toString());
			
			ClienteNegocioImp cn = new ClienteNegocioImp();
			baja = cn.delete(c);
			
			if(baja==true) request.setAttribute("exito", baja);
			else request.setAttribute("error", baja);
			RequestDispatcher rd = request.getRequestDispatcher("/BajaClientes.jsp");   
	        rd.forward(request, response);
		}
       
       
       
       
       //------------LISANDRO LINDO-----------//
       
       // SE CREA UNA LISTA QUE MUESTRA UN USUARIO EN ESPECIFICO
       if(request.getParameter("btnBuscarUser")!=null) {
			
			String User=request.getParameter("txtBuscarUsuario").toString();
			
			ArrayList<Cliente> ClienteSegunUser = cneg.LeerSegunUsuario(User);
			
			request.setAttribute("ListaLISTAR_CLIENTE",ClienteSegunUser);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
       
       
       
       if(request.getParameter("btnBuscar")!=null) {
			
			ArrayList<Cuenta> lista = (ArrayList<Cuenta>) cuneg.obtenerCuentaQueryCustom(request.getParameter("dllBusqueda").toString(), request.getParameter("txtFiltro").toString());
			System.out.println(request.getParameter("dllBusqueda").toString());
			request.removeAttribute("cuentas");
			request.setAttribute("cuentas", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");   
	        rd.forward(request, response);
		}
       
       
       //CARGA LA LISTA CON EL USUARIO DESEADO
       if(request.getParameter("btnBuscarUsuario")!=null) {
    	   String User=request.getParameter("txtUsuarioModificar").toString();
    	   ArrayList<Cliente> ClienteSegunUser = cneg.LeerSegunUsuario(User);
    	   request.setAttribute("CLIENTE",ClienteSegunUser);
    	   
    	   RequestDispatcher rd = request.getRequestDispatcher("/ModifClientes.jsp");
    	   rd.forward(request, response);
       }
       
       
       // CARGA UNA LISTA CON EL USUARIO SELECCIONADO PARA MODIFICAR
       // TAMBIEN CARGA LAS LISTAS DE GENERO, NAC, PROV, LOC PARA MOSTARLAS EN LOS DDL
       if(request.getParameter("btnModificarCliente")!=null) {
    	   
    	   String User1=request.getParameter("hiddenUsuario").toString();
    	   ArrayList<Cliente> ClienteUser = cneg.LeerSegunUsuario(User1);
    	   request.setAttribute("ClienteModificar",ClienteUser);
    	   
    	   GeneroNegocioImp gneg = new GeneroNegocioImp();
    	   ArrayList<Genero> listGeneros = (ArrayList<Genero>) gneg.readAll();
    	   request.setAttribute("generos", listGeneros);
    	   
    	   NacionalidadNegocioImp nneg = new NacionalidadNegocioImp();
    	   ArrayList<Nacionalidad> listNacionalidades = (ArrayList<Nacionalidad>) nneg.readAll();
    	   request.setAttribute("nacionalidades", listNacionalidades);
    	   
    	   ProvinciaNegocioImp pneg = new ProvinciaNegocioImp();
    	   ArrayList<Provincia> listProvincias = (ArrayList<Provincia>) pneg.readAll();
    	   request.setAttribute("provincias", listProvincias);
    	   
    	   LocalidadNegocioImp lneg = new LocalidadNegocioImp();
    	   ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) lneg.readAll();
    	   request.setAttribute("localidades", listaLocalidad);
    	   
    	   RequestDispatcher rd = request.getRequestDispatcher("/ModifClientes.jsp");
    	   rd.forward(request, response);
       }
       
       
       // SI SE CANCELA LA MODIFICACION O SI SE PRECIONA EL BOTON DE "Mostar todo" SE CARGA UNA LISTA CON TODOS LOS CLIENTES
       if(request.getParameter("btnModificarCancelar")!=null || request.getParameter("btnMostrarTodo")!=null) {
    	   ArrayList<Cliente> ListaClientes = cneg.MostrarTodos();
    	   request.setAttribute("ListaClientes", ListaClientes);
    	   
    	   RequestDispatcher rd = request.getRequestDispatcher("/ModifClientes.jsp");
    	   rd.forward(request, response);
       }
       
       // SI SE ACEPTA MODIFICAR EL CLIENTE, SE OBTIENE TODOS LOS DATOS (LOS QUE SE MODIFICAN Y LOS QUE NO)
       // SE LOS GUARDA EN UN OBJETO "Cliente" Y SE HACE UN UPDATE
       if(request.getParameter("btnModificarAceptar")!=null) {
    	   boolean ModCli = false;
    	   boolean ModUs = false;
    	   
    	   Cliente cliente = new Cliente();
    	   Usuario usuario = new Usuario();
    	   Genero genero = new Genero();
    	   Nacionalidad nac = new Nacionalidad();
    	   Provincia prov = new Provincia();
    	   Localidad loc = new Localidad();
    	   
    	   genero.setCod_genero(Integer.parseInt(request.getParameter("ddlGenero").toString()));
    	   nac.setCod_nacionalidad(Integer.parseInt(request.getParameter("ddlNacionalidad").toString()));
    	   prov.setCod_provincia(Integer.parseInt(request.getParameter("ddlProvincia").toString()));
    	   loc.setCod_localidad(Integer.parseInt(request.getParameter("ddlLocalidad").toString()));
    	   
    	   usuario.setUsuario(request.getParameter("txtUsuario").toString());
    	   
    	   cliente.setNro_Cliente(Integer.parseInt(request.getParameter("txtNroCliente").toString()));
    	   cliente.setNombre(request.getParameter("txtNombre").toString());
    	   cliente.setApellido(request.getParameter("txtApellido").toString());
    	   cliente.setDni(request.getParameter("txtDNI").toString());
    	   cliente.setCuil(request.getParameter("txtCUIL").toString());			
    	   cliente.setDireccion(request.getParameter("txtDireccion").toString());
    	   cliente.setTelefono(request.getParameter("txtTelefono").toString());
    	   cliente.setFecha_nac(request.getParameter("txtFechaNac").toString());
    	   cliente.setCod_Genero(genero);
    	   cliente.setCod_nacionalidad(nac);
    	   cliente.setCod_provincia(prov);
    	   cliente.setCod_localidad(loc);
    	   cliente.setEmail(request.getParameter("txtEmail").toString());
    	   cliente.setEstado(true);
    	   cliente.setUsuario(usuario);
    	   
    	   ModCli = cneg.update(cliente);
    	   
    	   UsuarioNegocioImp NegUser = new UsuarioNegocioImp();
    	   usuario.setContrasenia(request.getParameter("txtContraseña").toString());
    	   
    	   ModUs = NegUser.update(usuario);
    	   
    	   if(ModCli && ModUs) {
    		   request.setAttribute("ModifTrue", true);
    	   }
    	   
    	   ArrayList<Cliente> ListaClientes = cneg.MostrarTodos();
    	   request.setAttribute("ListaClientes", ListaClientes);
    	   
    	   RequestDispatcher rd = request.getRequestDispatcher("/ModifClientes.jsp");
    	   rd.forward(request, response);
       }
       
       
       
//------------------------------------------------------------------------------------------------------------------------//
       
       
	    
	    if(request.getParameter("btnModBuscar")!=null) {
			ArrayList<Cuenta> lista = (ArrayList<Cuenta>) neg.obtenerCuentaQueryCustom(request.getParameter("dllBusqueda").toString(), request.getParameter("txtFiltro").toString());
			
			request.removeAttribute("ModCuentas");
			request.setAttribute("ModCuentas", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");   
	        rd.forward(request, response);
		}
	    
	    if(request.getParameter("btnModificarCuenta")!=null) {
	    	
	    	ArrayList<Cuenta> lista = (ArrayList<Cuenta>) neg.obtenerCuentaPorNr_cuenta(request.getParameter("nroCuenta"));
	    	
	    	request.setAttribute("ModificarCuenta", lista);
	    	System.out.println("entro mod cuenta");
	    	RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");   
	        rd.forward(request, response);
	    }
	    
	    if(request.getParameter("AceptarModificar")!=null) {
	    	System.out.println(request.getParameter("ddlTipoCuenta").toString());
	    	System.out.println(request.getParameter("nroCuentaM").toString());
	    	
	    	Cuenta c = new Cuenta();
	    	c.setNro_cuenta(Integer.parseInt(request.getParameter("nroCuentaM")));
	    	c.setNro_cliente(Integer.parseInt(request.getParameter("nroClienteM")));
	    	c.setFecha_creacion(request.getParameter("fechaCreacionM"));
	    	c.setCbu(request.getParameter("cbuM"));
	    	c.setTipo_cuenta(new TipoCuenta(Integer.parseInt(request.getParameter("ddlTipoCuenta").toString()),"asd"));
	    	c.setSaldo(Float.valueOf(request.getParameter("saldoM")));
	    	
	    	if(neg.modificarCuenta(c)){
	    		request.setAttribute("infoModify", true);
	    		
	    		request.setAttribute("ModCuentas", neg.obtenerCuentas());
				
				RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");
		        rd.forward(request, response);
	    	}
	    	else {
	    		request.setAttribute("infoModify", false);
	    		
	    		ArrayList<Cuenta> lista = (ArrayList<Cuenta>) neg.obtenerCuentaPorNr_cuenta(request.getParameter("nroCuentaM"));
		    	
		    	request.setAttribute("ModificarCuenta", lista);
		    	
		    	RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");   
		        rd.forward(request, response);
	    	}
	    	
	    	
	    }
	    
	    if(request.getParameter("modCuenta")!=null) {
	    	
			request.setAttribute("ModCuentas", neg.obtenerCuentas());
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");
	        rd.forward(request, response);
		}
	    
	    if(request.getParameter("ParamLCU")!=null) {
			
	        request.setAttribute("cuentas", cuneg.obtenerCuentas());
				
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");   
		    rd.forward(request, response);
		}
	    
	    if(request.getParameter("RechazarModificar")!=null) {
	    	
	    	request.removeAttribute("ModCuentas");
			request.setAttribute("ModCuentas", neg.obtenerCuentas());
			
			RequestDispatcher rd = request.getRequestDispatcher("/ModifCuenta.jsp");
	        rd.forward(request, response);
	    }

       if(request.getParameter("btnAltaCuenta")!=null) {

    	   Cuenta c = new Cuenta();
    	   c.setNro_cliente(Integer.parseInt(request.getParameter("NroCliente").toString()));
    	   c.setTipo_cuenta(new TipoCuenta(Integer.parseInt(request.getParameter("ddlCuentas").toString()),""));
    	   c.setCbu(request.getParameter("Cbu").toString());
    	   c.setSaldo(10000);
    	   c.setEstado(true);
    	   
    	   CuentaNegocioImp cn = new CuentaNegocioImp();
    	   
    	   // Verifico que no haya llegado a la maxima cantidad de cuentas por cliente
    	   int totCuentasCliente = cn.totalCuentasPorCliente(c.getNro_cliente());
    	   if(totCuentasCliente>=3) {
				request.setAttribute("cuenta", c);
  			    request.setAttribute("resultadoAlta", "errorCantidadCuentas");
  	    	    RequestDispatcher rd = request.getRequestDispatcher("MostrarCuenta.jsp");   
  	    	    rd.forward(request, response);
    	   }
			
    	   // Realizo el alta de cuenta
    	   int nroCuenta = cn.insert(c);
    	   request.setAttribute("NRO", nroCuenta);

    	   if(nroCuenta>0) {
				c.setNro_cuenta(nroCuenta);
				request.setAttribute("cuenta", c);
   			    request.setAttribute("resultadoAlta", "ok");
    	   }
    	   else {
				request.setAttribute("cliente", c);
   			    request.setAttribute("resultadoAlta", "error");
    	   }
    	   RequestDispatcher rd = request.getRequestDispatcher("MostrarCuenta.jsp");   
    	   rd.forward(request, response);
       }


		
		
	    if(request.getParameter("btnBuscarCuenta")!=null) {

    	   Cuenta c = new Cuenta();
    	   c.setNro_cuenta(Integer.parseInt(request.getParameter("NroCuentaAEliminar").toString()));
    	   
    	   // Obtengo cuenta
    	   CuentaNegocioImp cn = new CuentaNegocioImp();
    	   c = cn.get(c);
    	   
		   request.setAttribute("cuenta", c);
    	   if(c!=null) {
    		   request.setAttribute("cuentaEncontrada", "true");
    	   }
    	   else
    	   {
    		   request.setAttribute("cuentaEncontrada", "false");
    	   }
    	   
    	   RequestDispatcher rd = request.getRequestDispatcher("BajaCuenta.jsp");   
    	   rd.forward(request, response);
	    }
	    

	    if(request.getParameter("btnEliminarCuenta")!=null) {
    	   Cuenta c = new Cuenta();
    	   String nroCuenta = request.getParameter("hiddenNroCuenta");
    	   c.setNro_cuenta(Integer.parseInt(nroCuenta));
    	   
    	   // Elimino cuenta
    	   CuentaNegocioImp cn = new CuentaNegocioImp();
    	   boolean cuentaEliminada = cn.delete(c);
    	   
    	   if(cuentaEliminada) 
    	   {
    		   request.setAttribute("cuentaEliminada", "true");
    	   }
    	   else 
    	   {
    		   request.setAttribute("cuentaEliminada", "false");
    	   }

    	   RequestDispatcher rd = request.getRequestDispatcher("BajaCuenta.jsp");   
    	   rd.forward(request, response);
		}
	}
	
	public String CbuAleatorio() {
		int[] numeros= new int [21];
		String cbu="";
		for (int x=0;x<21;x++) {
			  numeros[x] = (int) (Math.random()*10)+1;
	   }
	    for (int x=0;x<numeros.length;x++) {
		  cbu += numeros[x];
	   }
    return cbu;
	}
	
	public static boolean verificarDniInvalido(String dni) throws DniInvalidoException 
	{
		Boolean DniInvalido = false;
		
		for(int i=0; i< dni.length() ; i++)
		{
		   if(!((dni.charAt(i) >= '0') && (dni.charAt(i) <= '9')))
		   {
			   DniInvalido=true;
			   DniInvalidoException exc  = new DniInvalidoException();
			   throw exc;			   
		   }
		}

		return DniInvalido;
	}
	
}
