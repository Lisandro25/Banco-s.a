package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import negocioImp.CuentaNegocioImp;
import negocioImp.PrestamoNegocioImp;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCliente() {
        super();
    }

    PrestamoNegocioImp  Pneg = new PrestamoNegocioImp();
    CuentaNegocioImp CuentaNeg = new CuentaNegocioImp();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//----------LISANDRO--------------//
		
		//CUANDO SE DA CLICK AL "Solicitar prestamo" QUE SE ENCUENTRA EN EL MENU DEL ENCABEZADO, SE OBTIENE LA FECHA
		// Y EL PROXIMO NUMERO DE PRESTAMO Y ES ENVIADO A LA PANTALLA DE SOLICITAR EL PRESTAMO
		//TAMBIEN SE CREA UNA LISTA QUE CONTIENE LOS NUMEROS DE CUENTAS DEL CLIENTE LOGUEADO, PARA PODER SOLICITAR
		//QUE EL DINERO SEA DEPOSITADO EN ALGUNA DE SUS CUENTAS
		if(request.getParameter("SolicitarPrestamo")!=null) {
			CargarFechayProxPrestamo(request, response);
			
			
            String Nombre = request.getParameter("SolicitarPrestamo").toString();
            ArrayList<Cuenta> ListaCuentas = new ArrayList<Cuenta>();
            ListaCuentas = CuentaNeg.getCuentasXCliente(Nombre);
			request.setAttribute("Cuentas", ListaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			rd.forward(request, response);
			
		}
		
		
		// AL CANCELAR EL PEDIDO DEL PRESTAMO, SE VOLVERA A LA PANTALLA DE 'Solicitar" Y SE CARGARA EL DDL CON LAS CUENTAS
		if(request.getParameter("btnCancelarPrestamo")!=null) {
			CargarFechayProxPrestamo(request, response);
			
			String Nombre = request.getParameter("NombreUsuario").toString();
            ArrayList<Cuenta> ListaCuentas = new ArrayList<Cuenta>();
            ListaCuentas = CuentaNeg.getCuentasXCliente(Nombre);
			request.setAttribute("Cuentas", ListaCuentas);
			
			request.setAttribute("SolicitudCancelada", false);
			
			RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		// AL PRECIONAR EL BOTON DE SIMULACION DEL PRESTAMO, SE COMIENZA A OBTENER EL IMPORTE SOLICITADO, IMPORTE TOTAL, ETC
		// Y TODO ESTO ES ENVIADO A LA VISTA DE "Simulacion de prestamo"
		if(request.getParameter("btnSimularPrestamo")!=null) {
			float interes = (float) 1.5;
			float ImpSolicitado = Float.parseFloat(request.getParameter("txtImporte").toString());
			request.setAttribute("ImporteSolicitado", ImpSolicitado);
			
			float ImpTotal = ImpSolicitado*interes;
			request.setAttribute("ImporteTotal", ImpTotal);
			
			float InteresTotal = (float) (ImpSolicitado*0.5);
			request.setAttribute("InteresTotal", InteresTotal);
			
			int CuentaDestino = Integer.parseInt(request.getParameter("ddlCuentas").toString());
			request.setAttribute("CuentaDestino", CuentaDestino);
			
					
			int NroPrestamo = Integer.parseInt(request.getParameter("NroPrestamo").toString());
			request.setAttribute("NroPrestamo", NroPrestamo);
			
            
            int CantCuotas = Integer.parseInt(request.getParameter("ddlCuotas").toString());
            request.setAttribute("CantidadCuotas", CantCuotas);
            
            float PagarXmes = ImpTotal/CantCuotas;
            request.setAttribute("PagarXmes", PagarXmes);
            
            RequestDispatcher rd = request.getRequestDispatcher("/SimulacionPrestamo.jsp");
			rd.forward(request, response);
		}
		
		
		// SI SE CONFIRMA EL PRESTAMO,SE CREA UN OBJETO PRESTAMO Y SE LO RELLENA PARA LUEGO INSERTARLO A LA BD
		if(request.getParameter("btnConfirmarPrestamo")!=null) {
			boolean PrestamoEnviado = false;
			Prestamo P = new Prestamo();
			Cliente C = new Cliente();
			C.setNro_Cliente(Integer.parseInt(request.getParameter("NroCliente").toString()));
			
			P.setNro_cliente(C);
			P.setImp_solicitado(Float.parseFloat(request.getParameter("ImporteSolicitado").toString()));
			P.setImp_con_intereses(Float.parseFloat(request.getParameter("importeTotal").toString()));
			P.setNro_cuenta_deposito(Integer.parseInt(request.getParameter("CuentaDestino").toString()));
			P.setPlazo_pago_meses(Integer.parseInt(request.getParameter("CantidadCuotas").toString()));
			P.setCant_cuotas(Integer.parseInt(request.getParameter("CantidadCuotas").toString()));
			
			if(Pneg.insert(P)) PrestamoEnviado = true;
			
			request.setAttribute("PrestamoEnviado", PrestamoEnviado);
			
			CargarFechayProxPrestamo(request, response);
			
			
			String Nombre = request.getParameter("NombreUsuario").toString();
            ArrayList<Cuenta> ListaCuentas = new ArrayList<Cuenta>();
            ListaCuentas = CuentaNeg.getCuentasXCliente(Nombre);
			request.setAttribute("Cuentas", ListaCuentas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");   
	        rd.forward(request, response);
			
		}
		
		if(request.getParameter("jspAbonarPrestamo")!=null) {
			HttpSession session = request.getSession();
			Cliente cli = new Cliente();
			
			cli = (Cliente)session.getAttribute("datosCliente");
			
    		ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) Pneg.GetListaPagarCuotas(cli.getNro_Cliente());
    		request.setAttribute("listaPrestamosPagar", listaPrestamos);
    		
    		RequestDispatcher rd = request.getRequestDispatcher("AbonarPrestamo.jsp");   
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnPagarPrestamo")!=null) {
			
			int resultado = Pneg.pagarPrestamoCuota(Integer.parseInt(request.getParameter("nroPrestamo").toString()),Integer.parseInt(request.getParameter("cantCuota").toString()));
			
			System.out.println(resultado + " - " + Integer.parseInt(request.getParameter("nroPrestamo").toString()) + " " + Integer.parseInt(request.getParameter("cantCuota").toString()));
			if( resultado == 1) {
				request.setAttribute("cuotaPagada", true);
			}else {
				request.setAttribute("cuotaPagada", false);
			}
			
			HttpSession session = request.getSession();
			Cliente cli = new Cliente();
			
			cli = (Cliente)session.getAttribute("datosCliente");
			
    		ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) Pneg.GetListaPagarCuotas(cli.getNro_Cliente());
    		request.setAttribute("listaPrestamosPagar", listaPrestamos);

			RequestDispatcher rd = request.getRequestDispatcher("AbonarPrestamo.jsp");   
	        rd.forward(request, response);
			
		}
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}
	
	// METODO PARA CARGAR LA FECHA ACTUAL Y EL PROXIMO NUMERO DE PRESTAMO
	public void CargarFechayProxPrestamo( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int ProxNroPrestamo = Pneg.ObtenerProxNro_Prestamo();
		request.setAttribute("ProxNroPrestamo", ProxNroPrestamo);
		
		
		Calendar calendario = new GregorianCalendar();
		int dia, mes, anio;
		
		dia =calendario.get(Calendar.DAY_OF_MONTH);
		mes = calendario.get(Calendar.MONTH)+1;
		anio = calendario.get(Calendar.YEAR);
		
		String FechaActual = String.valueOf(dia) +"/"+ String.valueOf(mes)+"/"+String.valueOf(anio);
		request.setAttribute("FechaActual", FechaActual);
	}	

}
