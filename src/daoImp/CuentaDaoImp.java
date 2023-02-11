package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CuentaDao;
import entidades.Cuenta;
import entidades.TipoCuenta;

public class CuentaDaoImp implements CuentaDao {

	private static final String agregar = "{CALL spAltaCuenta(?,?,?,?,?)}";

	private static final String modificar = "update cuentas SET Nro_cliente = ?, Fecha_creacion = ?, Tipo_cuenta = ?, Cbu = ?, Saldo = ? WHERE Nro_cuenta = ?";
	
	private static final String delete = "UPDATE cuentas SET Estado = 0  WHERE Nro_cuenta = ?";
	
	private static final String consultar = "SELECT a.Nro_cuenta, a.Nro_cliente, a.Fecha_creacion, a.Tipo_cuenta, b.Descripcion, a.Cbu, a.Saldo " +
											"from cuentas as a inner join tiposcuentas as b on a.Tipo_cuenta = b.Tipo_cuenta " + 
											"where Estado = 1 and Nro_cuenta = ?";
	
	private static final String totalCuentasPorCliente = "SELECT COUNT(*) as total FROM cuentas " +
														 "where Estado = 1 and Nro_cliente = ?";
	

	
	@Override
	public ArrayList<Cuenta> obtenerCuentas() {
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		
		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery("SELECT a.Nro_cuenta, a.Nro_cliente, a.Fecha_creacion, a.Tipo_cuenta, b.Descripcion, a.Cbu, a.Saldo from cuentas as a inner join tiposcuentas as b on a.Tipo_cuenta = b.Tipo_cuenta where Estado = 1 ");

			// Cargo lista
			while(rs.next()){
				Cuenta cuenta = new Cuenta();
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setNro_cliente(rs.getInt("Nro_cliente"));
				cuenta.setFecha_creacion(rs.getString("Fecha_creacion"));
				cuenta.setTipo_cuenta(new TipoCuenta(rs.getInt("tipo_cuenta"),rs.getString("b.Descripcion")));
				cuenta.setCbu(rs.getString("Cbu"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
				lista.add(cuenta);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	public ArrayList<Cuenta> obtenerCuentaQueryCustom(String consulta, String filtro) {
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		String Query = "";
		
		if(consulta.equals("todo")) {
			Query = "SELECT a.Nro_cuenta, a.Nro_cliente, a.Fecha_creacion, a.Tipo_cuenta, b.Descripcion, a.Cbu, a.Saldo " + 
					"from cuentas as a inner join tiposcuentas as b on a.Tipo_cuenta = b.Tipo_cuenta " + 
					"WHERE Estado = 1 AND " + 
					"(" +
					"a.Nro_cuenta LIKE '%" + filtro + "%' or " + 
					"a.Nro_cliente LIKE '%" + filtro + "%' or " + 
					"a.Fecha_creacion LIKE '%" + filtro + "%' or " + 
					"a.Tipo_cuenta LIKE '%" + filtro + "%' or " + 
					"b.Descripcion LIKE '%" + filtro + "%' or " + 
					"a.Cbu LIKE '%" + filtro + "%' or " + 
					"a.Saldo LIKE '%" + filtro + "%'" +
					")";
		}
		else {
			Query = "SELECT a.Nro_cuenta, a.Nro_cliente, a.Fecha_creacion, a.Tipo_cuenta, b.Descripcion, a.Cbu, a.Saldo from cuentas as a inner join tiposcuentas as b on a.Tipo_cuenta = b.Tipo_cuenta WHERE " + consulta +" LIKE '%" + filtro + "%'";
		}
		
		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery(Query);

			// Cargo lista
			while(rs.next()){
				Cuenta cuenta = new Cuenta();
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setNro_cliente(rs.getInt("Nro_cliente"));
				cuenta.setFecha_creacion(rs.getString("Fecha_creacion"));
				cuenta.setTipo_cuenta(new TipoCuenta(rs.getInt("tipo_cuenta"),rs.getString("b.Descripcion")));
				cuenta.setCbu(rs.getString("Cbu"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
				lista.add(cuenta);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentaPorNr_cuenta(String numero) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		
		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery("SELECT a.Nro_cuenta, a.Nro_cliente, a.Fecha_creacion, a.Tipo_cuenta, b.Descripcion, a.Cbu, a.Saldo from cuentas as a inner join tiposcuentas as b on a.Tipo_cuenta = b.Tipo_cuenta where Nro_cuenta = " + numero);

			// Cargo lista
			while(rs.next()){
				Cuenta cuenta = new Cuenta();
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setNro_cliente(rs.getInt("Nro_cliente"));
				cuenta.setFecha_creacion(rs.getString("Fecha_creacion"));
				cuenta.setTipo_cuenta(new TipoCuenta(rs.getInt("tipo_cuenta"),rs.getString("b.Descripcion")));
				cuenta.setCbu(rs.getString("Cbu"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
				lista.add(cuenta);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}

	@Override
	public boolean modificarCuenta(Cuenta c) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(modificar);
			statement.setInt(1, c.getNro_cliente());
			statement.setString(2, c.getFecha_creacion());
			statement.setInt(3, c.getTipo_cuenta().getTipo_cuenta());
			statement.setString(4, c.getCbu());
			statement.setFloat(5, c.getSaldo());
			statement.setInt(6, c.getNro_cuenta());	
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
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
		System.out.println(isInsertExitoso);
		return isInsertExitoso;
	}
	
	@Override
	public int insert(Cuenta cu) {
		int nroCuenta = -1;

        Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			PreparedStatement statement = conexion.prepareStatement(agregar);
			statement.setInt(1, cu.getNro_cliente());
			statement.setInt(2, cu.getTipo_cuenta().getTipo_cuenta());
			statement.setString(3, cu.getCbu());
			statement.setFloat(4, cu.getSaldo());
			statement.setBoolean(5, cu.getEstado());

			// statement.execute();

			// En lugar de execute uso executeQuery para obtener el resulset 
			// que me devuelve el nroCuenta asignado o -1 en caso que falle
			ResultSet rs1 = statement.executeQuery();

			// Si devolvio algun mensaje de error entonces retorno falso
		    while(rs1.next()) {
		    	nroCuenta = rs1.getInt("NRO");
		    }			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return nroCuenta;
	}

	@Override
	public boolean delete(Cuenta cu) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, cu.getNro_cuenta());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public boolean update(Cuenta cu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cuenta get(Cuenta cu) {
		Cuenta cuenta = new Cuenta();
				
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(consultar);
			statement.setInt(1, cu.getNro_cuenta());
			
			ResultSet rs = null;
			
			rs = statement.executeQuery();
			
			// Cargo lista
			if(rs.next()){
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setNro_cliente(rs.getInt("Nro_cliente"));
				cuenta.setFecha_creacion(rs.getString("Fecha_creacion"));
				cuenta.setTipo_cuenta(new TipoCuenta(rs.getInt("tipo_cuenta"),rs.getString("b.Descripcion")));
				cuenta.setCbu(rs.getString("Cbu"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
			}
			else cuenta = null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally{
		
		}
		return cuenta;
	}

	@Override
	public int totalCuentasPorCliente(int nroCliente) {
		int total = 0;
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(totalCuentasPorCliente);
			statement.setInt(1, nroCliente);
			
			ResultSet rs = null;
			
			rs = statement.executeQuery();
			
			// Cargo lista
			if(rs.next()){
				total = rs.getInt("total");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally{
		
		}
		return total;
	}

	@Override
	public ArrayList<Cuenta> getCuentasXCliente(String nombreUsu) {
Connection conexion = Conexion.getConexion().getSQLConexion();
		
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		String Query ="SELECT Nro_Cuenta from cuentas as a inner join clientes as b \r\n" + 
				"on a.Nro_cliente = b.Nro_Cliente where b.Usuario = '" + nombreUsu + "'";
		
		try{
			ResultSet rs = null;

			Statement st = conexion.createStatement();
			rs = st.executeQuery(Query);

			// Cargo lista
			while(rs.next()){
				Cuenta cuenta = new Cuenta();
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setCbu("1");
				cuenta.setFecha_creacion("1");
				cuenta.setNro_cliente(0);
				cuenta.setSaldo(1);
				cuenta.setTipo_cuenta(new TipoCuenta(1,"asd"));
				lista.add(cuenta);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}

	@Override
	public Cuenta getCuentaXcbu(String cbu) {
		Cuenta cuenta = new Cuenta();
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement("SELECT * from vistaCuentasCBU Where Cbu = " + cbu + " AND Estado = 1");
			
			ResultSet rs = null;
			
			rs = statement.executeQuery();
			
			// Cargo lista
			if(rs.next()){
				cuenta.setNro_cuenta(rs.getInt("Nro_cuenta"));
				cuenta.setNro_cliente(rs.getInt("Nro_cliente"));
				cuenta.setFecha_creacion(rs.getString("Fecha_creacion"));
				cuenta.setTipo_cuenta(new TipoCuenta(rs.getInt("tipo_cuenta"),rs.getString("b.Descripcion")));
				cuenta.setCbu(rs.getString("Cbu"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
			}
			else cuenta = null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}finally{
		
		}
		return cuenta;
	}

	

	@Override
	public int NroClienteSegunNombreCliente(String NombreClie) {
		int NroCliente=0;
		PreparedStatement Statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			Statement = conexion.prepareStatement("SELECT Nro_Cliente FROM clientes where usuario = '" +NombreClie+ "'"); 
			resultSet = Statement.executeQuery();
			resultSet.next();
			NroCliente = resultSet.getInt(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return NroCliente;
	}

}
