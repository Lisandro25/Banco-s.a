package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import negocioImp.CuentaNegocioImp;

/**
 * Servlet implementation class servletListarCuenta
 */
@WebServlet("/servletListarCuenta")
public class servletListarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CuentaNegocioImp neg = new CuentaNegocioImp();
		//CuentaNegocioImp neg = new CuentaNegocioImp();
		
		if(request.getParameter("Param")!=null) {
			
			request.setAttribute("cuentas", neg.obtenerCuentas());
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");   
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnBuscar")!=null) {
			
			ArrayList<Cuenta> lista = (ArrayList<Cuenta>) neg.obtenerCuentaQueryCustom(request.getParameter("dllBusqueda").toString(), request.getParameter("txtFiltro").toString());
			System.out.println(request.getParameter("dllBusqueda").toString());
			request.removeAttribute("cuentas");
			request.setAttribute("cuentas", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
