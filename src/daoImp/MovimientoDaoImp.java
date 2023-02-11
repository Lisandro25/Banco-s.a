package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;

public class MovimientoDaoImp implements MovimientoDao{

	private static final String readAll = "SELECT Nro_Movimiento,Nro_cuenta,fecha_dmy as fecha,Tipo_mov,Descripcion,Importe,Detalle FROM vistaMovimientos";
	
	@Override
	public List<Movimiento> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> list = new ArrayList<Movimiento>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				list.add(get(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int dineroTotal() {
		Integer dinero = 0;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		
		try
		{
			statement = conexion.prepareStatement("SELECT SUM(Saldo) From cuentas WHERE Estado = 1");
			ResultSet resultado = statement.executeQuery();
			resultado.next();
			dinero = resultado.getInt(1);
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
		return dinero;
	}
	
	private Movimiento get(ResultSet resultSet) throws SQLException {
		Movimiento m = new Movimiento();
		TipoMovimiento tm = new TipoMovimiento();
		
		m.setNro_Movimiento(resultSet.getInt("Nro_Movimiento"));
		m.setNro_Cuenta(resultSet.getInt("Nro_cuenta"));
		m.setFecha(resultSet.getString("Fecha"));
		tm.setDescripcion(resultSet.getString("Descripcion"));
		m.setTipo_Mov(tm);
		m.setImporte(resultSet.getFloat("Importe"));
		m.setDetalle(resultSet.getString("Detalle"));

		return m;
	}

	@Override
	public List<Movimiento> movimientoXfecha(String fecha1, String fecha2, String filtro) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		String Query = "";
		
		if(filtro.toString()!= "Todo") {
			if(fecha1.equals("") && fecha2.equals("")) Query = readAll +" WHERE Tipo_mov = " + filtro + "";
			else Query = readAll +" WHERE Tipo_mov = " + filtro + " AND fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"'";
		}
		if(filtro.equals("Todo")){ 
			if(fecha1.equals("") && fecha2.equals("")) Query = readAll;
			else Query = readAll+" WHERE fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
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
	public List<Movimiento> getMovCuenta(Cuenta c) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		String Query = "SELECT * from vistaMovimientos where Nro_Cuenta = " + c.getNro_cuenta();
		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery(Query);

			// Cargo lista
			while(rs.next()){
				Movimiento m = new Movimiento();
				TipoMovimiento tm = new TipoMovimiento();
				
				m.setNro_Movimiento(rs.getInt("Nro_Movimiento"));
				m.setNro_Cuenta(rs.getInt("Nro_cuenta"));
				m.setFecha(rs.getString("fecha_dmy"));
				tm.setDescripcion(rs.getString("Descripcion"));
				m.setTipo_Mov(tm);
				m.setImporte(rs.getFloat("Importe"));
				m.setDetalle(rs.getString("Detalle"));
				lista.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}

	@Override
	public boolean ejecutarTransferencia(String origen, String destino, float importe) {
		boolean rt = true;

        Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			PreparedStatement statement = conexion.prepareStatement("{CALL spEjecutarTransferencia(?,?,?)}");
			statement.setInt(1, Integer.parseInt(origen));
			statement.setInt(2, Integer.parseInt(destino));
			statement.setFloat(3, importe);
			// statement.execute();

			// En lugar de execute uso executeQuery para obtener el resulset 
			// que me devuelve el nroCuenta asignado o -1 en caso que falle
			ResultSet rs1 = statement.executeQuery();

			// Si devolvio algun mensaje de error entonces retorno falso
		    while(rs1.next()) {
		    	if(rs1.getInt("NRO") == -1 || rs1.getInt("NRO") ==  0) {
		    		rt = false;
		    	}
		    }			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rt;
	}


}
