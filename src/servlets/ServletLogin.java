package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Genero;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.TipoCuenta;
import entidades.Prestamo;
import negocioImp.UsuarioNegocioImp;
import negocioImp.ClienteNegocioImp;
import negocioImp.CuentaNegocioImp;
import negocioImp.GeneroNegocioImp;
import negocioImp.LocalidadNegocioImp;
import negocioImp.NacionalidadNegocioImp;
import negocioImp.ProvinciaNegocioImp;
import negocioImp.TipoCuentaNegocioImp;
import negocioImp.PrestamoNegocioImp;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	TipoCuentaNegocioImp tcneg = new TipoCuentaNegocioImp();
	GeneroNegocioImp gneg = new GeneroNegocioImp();
	NacionalidadNegocioImp nneg = new NacionalidadNegocioImp();
	ProvinciaNegocioImp provneg = new ProvinciaNegocioImp();
	LocalidadNegocioImp lneg = new LocalidadNegocioImp();
	ClienteNegocioImp cneg = new ClienteNegocioImp();
	CuentaNegocioImp cuentaNeg = new CuentaNegocioImp();
	PrestamoNegocioImp presneg = new PrestamoNegocioImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnLogin")!=null) {
			String usuario = request.getParameter("txtUsuario");
			String contrasena = request.getParameter("txtPassword");
			
			HttpSession session = request.getSession();

			Usuario usu = new Usuario();
			usu.setUsuario(usuario);
			usu.setContrasenia(contrasena);
			
			UsuarioNegocioImp neg = new UsuarioNegocioImp();
			
			if(neg.existeUsuario(usu)) {
				// cargo datos 
				cargarDatos(request,response);
				
				// guardo el usuario y su tipo, y establezco su pagina por defecto 
				session.setAttribute("nombreUsurio", usu.getUsuario());
				if(neg.esAdmin(usu)) {
					session.setAttribute("usuarioAdmin", true);

					RequestDispatcher rd = request.getRequestDispatcher("AltaClientes.jsp"); 
					rd.forward(request, response);   
				}
				// Usuario tipo cliente
				else {
					session.setAttribute("usuarioAdmin", false);

					cargarDatosCliente(request,response);
					
					RequestDispatcher rd = request.getRequestDispatcher("MisDatos.jsp");   
					rd.forward(request, response); 
				}
			}
			else {
				request.setAttribute("error", true);
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");   
				rd.forward(request, response);   
			}
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// Cargo en variables de tipo session las los datos para mostrar en las pantallas
	public void cargarDatos( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();

		int maxId = cneg.obtenerProxId();
		session.setAttribute("ncli", maxId);
		
		ArrayList<TipoCuenta> listTipoCuenta = (ArrayList<TipoCuenta>) tcneg.readAll();
		session.setAttribute("TipoCuenta", listTipoCuenta);
		
		int NroCliente = cuentaNeg.NroClienteSegunNombreCliente(request.getParameter("txtUsuario").toString());
		session.setAttribute("Nrocliente", NroCliente);
		
		
		String Nombre= request.getParameter("txtUsuario").toString(); 
		session.setAttribute("NombreUsuario", Nombre);
		
		
		
	    
	    ArrayList<Genero> listGeneros = (ArrayList<Genero>) gneg.readAll();
		session.setAttribute("generos", listGeneros);

		ArrayList<Nacionalidad> listNacionalidades = (ArrayList<Nacionalidad>) nneg.readAll();
		session.setAttribute("nacionalidades", listNacionalidades);
		
		ArrayList<Provincia> listProvincias = (ArrayList<Provincia>) provneg.readAll();
		session.setAttribute("provincias", listProvincias);
		
		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) lneg.readAll();
		session.setAttribute("localidades", listaLocalidad);
		
		ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) cuentaNeg.getCuentasXCliente(request.getParameter("txtUsuario").toString());
		session.setAttribute("CuentasEnCuenta", listaCuentas);
		
		Cliente aux = new Cliente();
		Usuario u = new Usuario();
		u.setUsuario(request.getParameter("txtUsuario"));
		aux.setUsuario(u);
		Cliente c = cneg.getClientePorUsuario(aux);
		session.setAttribute("datosCliente", c);
		
	}	
	
	// Cargo en variables de tipo session las los datos para mostrar en las pantallas
	public void cargarDatosCliente( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
	    
		// Cargo mis prestamos
		Cliente cli = new Cliente();
		cli = (Cliente)session.getAttribute("datosCliente");
        if(cli!=null)
        {
    		ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) presneg.GetPorCliente(cli.getNro_Cliente());
    		session.setAttribute("misprestamos", listaPrestamos);
    		
    		System.out.println(listaPrestamos.toString());
    		
        }
		
	}	
	
}
