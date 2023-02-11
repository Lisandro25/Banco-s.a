package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import entidades.Cliente;
import entidades.EstadosPrestamo;
import entidades.Prestamo;

public class PrestamoDaoImp implements PrestamoDao{

	private static final String aceptar = "{CALL spAprobarPrestamo(?)}";
	private static final String rechazar = "{CALL spRechazarPrestamo(?)}";
	private static final String solicitudes = "SELECT Nro_prestamo, fecha_dmy as fecha, Imp_con_intereses, Imp_solicitado, Nro_cuenta_deposito, Plazo_pago_meses, Monto_pago_por_mes, Cant_cuotas, Descripcion, Est_prestamo,Nro_cliente FROM vistaSolicitudes";
	private static final String readAll = "SELECT Nro_prestamo, fecha_dmy as fecha, Imp_con_intereses, Imp_solicitado, Nro_cuenta_deposito, Plazo_pago_meses, Monto_pago_por_mes, Cant_cuotas, Descripcion, Est_prestamo,Nro_cliente FROM vistaPrestamos"; 
	private static final String insert = "{CALL spAltaPrestamo(?,?,?,?,?,?)}";
	private static final String prestamosPorCliente = 
			" SELECT Nro_prestamo, fecha, Imp_con_intereses, Imp_solicitado, Nro_cuenta_deposito, Plazo_pago_meses, Monto_pago_por_mes, Cant_cuotas, estadosPrestamos.Descripcion, estadosPrestamos.Est_prestamo,Nro_cliente, DATE_FORMAT(fecha,'%d/%m/%Y') AS fecha_dmy" + 
			" FROM prestamos " + 
			" INNER JOIN estadosPrestamos ON prestamos.Est_prestamo = estadosPrestamos.Est_prestamo " + 
			" WHERE Nro_Cliente = ?";
	private static final String listaAbonarPrestamos = 
					" SELECT Nro_prestamo, fecha, Imp_con_intereses, Imp_solicitado, Nro_cuenta_deposito, Plazo_pago_meses, Monto_pago_por_mes, Cant_cuotas, estadosPrestamos.Descripcion, estadosPrestamos.Est_prestamo,Nro_cliente, DATE_FORMAT(fecha,'%d/%m/%Y') AS fecha_dmy" + 
					" FROM prestamos " + 
					" INNER JOIN estadosPrestamos ON prestamos.Est_prestamo = estadosPrestamos.Est_prestamo " + 
					" WHERE Nro_Cliente = ? AND prestamos.Est_prestamo = 1 AND Cant_cuotas > 0";
	private static final String pagarPrestamo = "{call spPagarCuotaPrestamo(?,?)}";
	
	@Override
    public boolean aprobarPrestamo(Prestamo p) {
			boolean aprobado = false;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			try
			{
				PreparedStatement statement = conexion.prepareStatement(aceptar);
				statement.setInt(1, p.getNro_prestamo());
				// En lugar de execute uso executeQuery para obtener el resulset que me devuelve en caso que falle
				// statement.execute();
				ResultSet rs1 = statement.executeQuery();
			    while(rs1.next()) {
			    	aprobado = true;
			    }			
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return aprobado;
    }
    
	@Override
    public boolean rechazarPrestamo(Prestamo p) {
			boolean rechazado = false;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			try
			{
				PreparedStatement statement = conexion.prepareStatement(rechazar);
				statement.setInt(1, p.getNro_prestamo());
				// En lugar de execute uso executeQuery para obtener el resulset que me devuelve en caso que falle
				// statement.execute();
				ResultSet rs1 = statement.executeQuery();
			    while(rs1.next()) {
			    	rechazado = true;
			    }			
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return rechazado;
    }
	
	@Override
	public List<Prestamo> Solicitudes() {
		PreparedStatement Statement;
		ResultSet resultSet;
		ArrayList<Prestamo> ListaPrestamos = new ArrayList<Prestamo>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			Statement = conexion.getSQLConexion().prepareStatement(solicitudes);
			resultSet = Statement.executeQuery();
			while(resultSet.next()) {
				ListaPrestamos.add(get(resultSet));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ListaPrestamos;
	}
	
	@Override
	public List<Prestamo> readAll() {
		PreparedStatement Statement;
		ResultSet resultSet;
		ArrayList<Prestamo> ListaPrestamos = new ArrayList<Prestamo>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			Statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = Statement.executeQuery();
			while(resultSet.next()) {
				ListaPrestamos.add(get(resultSet));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ListaPrestamos;
	}
	
   @Override
   public ArrayList<Prestamo> obtenerPrestamosQueryCustom(String consulta, String filtro) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		String Query = "";
		
		if(filtro.length()!=0) Query = solicitudes+" WHERE " + consulta + " = '" + filtro + "'";
		else Query = solicitudes; 

		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery(Query);

			// Cargo lista
			while(rs.next()){
				lista.add(get(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}

	private Prestamo get(ResultSet resultSet) throws SQLException
	{
		Cliente c = new Cliente();
		EstadosPrestamo ep = new EstadosPrestamo();
		Prestamo p = new Prestamo();
	    
	    p.setNro_prestamo(resultSet.getInt("Nro_prestamo"));
		c.setNro_Cliente(resultSet.getInt("Nro_cliente"));
		p.setNro_cliente(c);
		p.setFecha(resultSet.getString("Fecha"));
		p.setImp_con_intereses(resultSet.getFloat("Imp_con_intereses"));
		p.setImp_solicitado(resultSet.getFloat("Imp_solicitado"));
		p.setNro_cuenta_deposito(resultSet.getInt("Nro_cuenta_deposito"));
	    p.setPlazo_pago_meses(resultSet.getInt("Plazo_pago_meses"));
		p.setMonto_pago_por_mes(resultSet.getFloat("Monto_pago_por_mes"));
	    p.setCant_cuotas(resultSet.getInt("Cant_cuotas"));
	    ep.setDescripcion(resultSet.getString("Descripcion"));
	    p.setEst_prestamo(ep);
	    
	    return p;
	}

	@Override
	public List<Prestamo> prestamoXfecha(String fecha1, String fecha2, String filtro) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		String Query = "";
		
		if(filtro.toString()!= "Todo") {
			if(fecha1.equals("") && fecha2.equals("")) Query = readAll+" WHERE Est_prestamo = " + filtro + "";
			else Query = readAll+" WHERE Est_prestamo = " + filtro + " AND fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"'";
		}
		if(filtro.equals("Todo")) {
			if(fecha1.equals("") && fecha2.equals("")) Query = readAll;
			else Query = readAll+" WHERE fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"'";
		}

		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery(Query);

			// Cargo lista
			while(rs.next()){
				lista.add(get(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}

	@Override
	public int ObtenerProxNro_Prestamo() {
		int MaxNroPrestamo = -1;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		
		try {
			statement = conexion.prepareStatement("SELECT MAX(Nro_prestamo) FROM prestamos");
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			MaxNroPrestamo = resultSet.getInt(1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return MaxNroPrestamo+1;
	}

	
	@Override
	public boolean insert(Prestamo prestamo) {
		boolean insertExitoso = false;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			PreparedStatement statement = conexion.prepareStatement(insert);
			statement.setInt(1, prestamo.getNro_cliente().getNro_Cliente());
			statement.setFloat(2, prestamo.getImp_solicitado());
			statement.setFloat(3, prestamo.getImp_con_intereses());
			statement.setInt(4,prestamo.getNro_cuenta_deposito());
			statement.setInt(5, prestamo.getPlazo_pago_meses());
			statement.setInt(6, prestamo.getCant_cuotas());
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				insertExitoso = true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return insertExitoso;
	}

	@Override
	public ArrayList<Prestamo> GetPorCliente(int Nro_Cliente) {
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		PreparedStatement statement;
		ResultSet rs;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(prestamosPorCliente);
			statement.setInt(1,Nro_Cliente);
			rs = statement.executeQuery();

			// Cargo lista
			while(rs.next()){
				lista.add(get(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	@Override
	public ArrayList<Prestamo> GetListaPagarCuotas(int Nro_Cliente) {
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		PreparedStatement statement;
		ResultSet rs;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(listaAbonarPrestamos);
			statement.setInt(1,Nro_Cliente);
			rs = statement.executeQuery();

			// Cargo lista
			while(rs.next()){
				lista.add(get(rs));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}

	@Override
	public int pagarPrestamoCuota(int Nro_cliente, int Nro_prestamo) {
		int NRO = -1;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			PreparedStatement statement = conexion.prepareStatement(pagarPrestamo);
			statement.setInt(1, Nro_cliente);
			statement.setInt(2, Nro_prestamo);

			ResultSet rs1 = statement.executeQuery();
		    while(rs1.next()) {
		    	rs1.getInt("NRO");
		    }			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return NRO;
	}

}





















