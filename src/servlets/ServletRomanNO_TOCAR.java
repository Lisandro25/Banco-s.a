package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.Movimiento;
import negocioImp.CuentaNegocioImp;
import negocioImp.MovimientoNegocioImp;

/**
 * Servlet implementation class ServletRomanNO_TOCAR
 */
@WebServlet("/ServletRomanNO_TOCAR")
public class ServletRomanNO_TOCAR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRomanNO_TOCAR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MovimientoNegocioImp movNeg = new MovimientoNegocioImp();

		if(request.getParameter("RNcuenta")!=null) {
			
			Cuenta aux = new Cuenta();
			aux.setNro_cuenta(Integer.parseInt(request.getParameter("RNcuenta").toString()));
			CuentaNegocioImp negc = new CuentaNegocioImp();
			Cuenta c = negc.get(aux);
			request.setAttribute("InfoCuenta", c);
			
			request.setAttribute("tablaMovimientos", movNeg.getMovCuenta(aux));

			RequestDispatcher rd = request.getRequestDispatcher("Cuenta1.jsp");   
			rd.forward(request, response);   
		}
		
		if(request.getParameter("aceptarTransferencia")!=null) {

			try {
				String cuentaOrigen = request.getParameter("txtNro_cuenta").toString();
				String cbuDestino = request.getParameter("txtCbu").toString();
				float importe = Integer.parseInt(request.getParameter("txtImporte"));
				
				System.out.println(importe);
				
				if(movNeg.ejecutarTransferencia(cuentaOrigen,cbuDestino,importe) && importe > 0) request.setAttribute("transferencia", true);
				else request.setAttribute("transferencia", false);
				
				Cuenta aux = new Cuenta();
				aux.setNro_cuenta(Integer.parseInt(request.getParameter("txtNro_cuenta").toString()));
				CuentaNegocioImp negc = new CuentaNegocioImp();
				Cuenta c = negc.get(aux);
				request.setAttribute("InfoCuenta", c);
				
				request.setAttribute("tablaMovimientos", movNeg.getMovCuenta(aux));
				
			}catch(Exception c) {
				
			}

			RequestDispatcher rd = request.getRequestDispatcher("Cuenta1.jsp");   
			rd.forward(request, response);   
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
