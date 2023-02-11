package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDao;
import entidades.Genero;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Usuario;
import entidades.Cliente;

public class ClienteDaoImp implements ClienteDao{

	private static final String insert = "{CALL spAltaCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String delete = "{CALL spBajaCliente(?)}";
	
	private static final String readAll = "SELECT clientes.Nro_Cliente, clientes.Nombre, clientes.Apellido, clientes.Dni,"
			+ " clientes.Cuil, clientes.Direccion, clientes.Telefono, clientes.Fecha_nac AS Fecha_Nacimiento, "
			+ "generos.Descripcion AS Genero, nacionalidad.Descripcion AS Nacionalidad, provincias.Descripcion "
			+ "AS Provincia, localidades.Descripcion AS Localidad, clientes.Email, usuarios.Usuario, usuarios.Contraseña "
			+ "FROM clientes INNER JOIN generos ON clientes.Cod_Genero = generos.Cod_genero INNER JOIN nacionalidad"
			+ " ON clientes.Cod_nacionalidad = nacionalidad.Cod_nacionalidad INNER JOIN provincias ON "
			+ "clientes.Cod_provincia = provincias.Cod_provincia INNER JOIN localidades "
			+ "ON clientes.Cod_provincia = localidades.Cod_provincia AND clientes.Cod_localidad = localidades.Cod_localidad"
			+ " INNER JOIN usuarios ON clientes.Usuario = usuarios.Usuario WHERE clientes.Estado=1";
	
	
	private static final String update = "UPDATE clientes SET Nombre=?, Apellido=?, Dni=?, Cuil=?, Direccion=?, Telefono=?, Fecha_nac=?, Cod_Genero=?, Cod_nacionalidad=?, Cod_provincia=?, Cod_localidad=?, Email=? WHERE Nro_Cliente = ?";
	
	
	
	@Override
	public boolean insert(Cliente cli) {
        String QUERY = insert;

		boolean isInsertExitoso = false;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			PreparedStatement statement = conexion.prepareStatement(QUERY);
			statement.setString(1, cli.getDni());
			statement.setString(2, cli.getCuil());
			statement.setString(3, cli.getNombre());
			statement.setString(4, cli.getApellido());
			statement.setInt(5, cli.getCod_Genero().getCod_genero());
			statement.setInt(6, cli.getCod_nacionalidad().getCod_nacionalidad());	
			statement.setString(7, cli.getFecha_nac());
			statement.setString(8, cli.getDireccion());
			statement.setInt(9, cli.getCod_localidad().getCod_localidad());
			statement.setInt(10, cli.getCod_provincia().getCod_provincia());
			statement.setString(11, cli.getEmail());
			statement.setString(12, cli.getTelefono());
			statement.setString(13, cli.getUsuario().getUsuario());
			statement.setString(14, cli.getUsuario().getContraseña());
			statement.setBoolean(15, cli.getEstado());

			// En lugar de execute uso executeQuery para obtener el resulset que me devuelve en caso que falle
			// statement.execute();

		    isInsertExitoso = true;
			ResultSet rs1 = statement.executeQuery();
			
			// Si devolvio algun mensaje de error entonces retorno falso
		    while(rs1.next()) {
		    	isInsertExitoso = false;
		    	/*
		        System.out.print("Level: "+rs1.getString("Level")+", ");
		        System.out.print("Code: "+rs1.getString("Code")+", ");
		        System.out.print("Message: "+rs1.getString("Message"));
		        System.out.println();
		        */
		    }			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isInsertExitoso;
	}
	
	@Override
	public boolean delete(Cliente cli) {
		boolean elimino = false;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			PreparedStatement statement = conexion.prepareStatement(delete);
			statement.setString(1, cli.getDni());
			// En lugar de execute uso executeQuery para obtener el resulset que me devuelve en caso que falle
			// statement.execute();
			ResultSet rs1 = statement.executeQuery();
		    while(rs1.next()) {
		    	elimino = true;
		    }			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return elimino;
	}
	
	public int obtenerProxId() {
		Integer maxId = -1;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		
		try
		{
			statement = conexion.prepareStatement("SELECT MAX(Nro_Cliente) FROM clientes");
			ResultSet resultado = statement.executeQuery();
			resultado.next();
			maxId = resultado.getInt(1);
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
		return maxId+1;
	}
	
	@Override
	public boolean existeDni(Cliente cli) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Conexion conexion = Conexion.getConexion();
		boolean existeCliente = false;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM clientes WHERE Dni = ?");
			statement.setString(1, cli.getDni());
			resultSet = statement.executeQuery();
			if(resultSet.next()) existeCliente = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return existeCliente;
	}
	
	@Override
	public boolean existeCliente(Cliente cli) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Conexion conexion = Conexion.getConexion();
		boolean existeCliente = false;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("SELECT * FROM clientes WHERE Dni = ? OR Cuil = ? OR Email = ?");
			statement.setString(1, cli.getDni());
			statement.setString(2, cli.getCuil());
			statement.setString(3, cli.getEmail());
			resultSet = statement.executeQuery();
			if(resultSet.next()) existeCliente = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return existeCliente;
	}

	
	
	@Override
	public ArrayList<Cliente> readAll() {
		PreparedStatement Statement;
		ResultSet resultSet;
		ArrayList<Cliente> ListaClientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			Statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = Statement.executeQuery();
			while(resultSet.next()) {
				Genero G = new Genero();
				Nacionalidad Nac = new Nacionalidad();
				Provincia Prov = new Provincia();
				Localidad Loc = new Localidad();
				Usuario User = new Usuario();
				
				int Nro_Cliente = resultSet.getInt(1);
				String Nombre = resultSet.getString(2);
				String Apellido = resultSet.getString(3);
				String Dni = resultSet.getString(4);
				String Cuil = resultSet.getString(5);
				String Direccion = resultSet.getString(6);
				String Telefono = resultSet.getString(7);
				String FechaNac = resultSet.getString(8);
				G.setDescripcion(resultSet.getString(9));
				Nac.setDescripcion(resultSet.getString(10));
				Prov.setDescripcion(resultSet.getString(11));
				Loc.setDescripcion(resultSet.getString(12));
				String Mail = resultSet.getString(13);
				User.setUsuario(resultSet.getString(14));
				User.setContrasenia(resultSet.getString(15));
				
				Cliente cliente = new Cliente(Nro_Cliente,Dni,Cuil,Nombre,Apellido,G,Nac,FechaNac,Direccion,Loc,Prov,Mail,Telefono,User,true);
			    ListaClientes.add(cliente);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ListaClientes;
	}

	@Override
	public ArrayList<Cliente> LeerSegunUsuario(String Users) {
		PreparedStatement Statement;
		ResultSet resultSet;
		ArrayList<Cliente> ListaClientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			Statement = conexion.getSQLConexion().prepareStatement("SELECT clientes.Nro_Cliente, clientes.Nombre, clientes.Apellido, clientes.Dni,"
					+ " clientes.Cuil, clientes.Direccion, clientes.Telefono, clientes.Fecha_nac AS Fecha_Nacimiento, "
					+ "generos.Descripcion AS Genero, nacionalidad.Descripcion AS Nacionalidad, provincias.Descripcion "
					+ "AS Provincia, localidades.Descripcion AS Localidad, clientes.Email, usuarios.Usuario, usuarios.Contraseña "
					+ "FROM clientes INNER JOIN generos ON clientes.Cod_Genero = generos.Cod_genero INNER JOIN nacionalidad"
					+ " ON clientes.Cod_nacionalidad = nacionalidad.Cod_nacionalidad INNER JOIN provincias ON "
					+ "clientes.Cod_provincia = provincias.Cod_provincia INNER JOIN localidades "
					+ "ON clientes.Cod_provincia = localidades.Cod_provincia AND clientes.Cod_localidad = localidades.Cod_localidad"
					+ " INNER JOIN usuarios ON clientes.Usuario = usuarios.Usuario "
					+ "WHERE clientes.Estado=1 AND usuarios.Usuario LIKE '%"+Users+"%'");
			resultSet = Statement.executeQuery();
			while(resultSet.next()) {
				Genero G = new Genero();
				Nacionalidad Nac = new Nacionalidad();
				Provincia Prov = new Provincia();
				Localidad Loc = new Localidad();
				Usuario User = new Usuario();
				
				int Nro_Cliente = resultSet.getInt(1);
				String Nombre = resultSet.getString(2);
				String Apellido = resultSet.getString(3);
				String Dni = resultSet.getString(4);
				String Cuil = resultSet.getString(5);
				String Direccion = resultSet.getString(6);
				String Telefono = resultSet.getString(7);
				String FechaNac = resultSet.getString(8);
				G.setDescripcion(resultSet.getString(9));
				Nac.setDescripcion(resultSet.getString(10));
				Prov.setDescripcion(resultSet.getString(11));
				Loc.setDescripcion(resultSet.getString(12));
				String Mail = resultSet.getString(13);
				User.setUsuario(resultSet.getString(14));
				User.setContrasenia(resultSet.getString(15));
				
				Cliente cliente = new Cliente(Nro_Cliente,Dni,Cuil,Nombre,Apellido,G,Nac,FechaNac,Direccion,Loc,Prov,Mail,Telefono,User,true);
			    ListaClientes.add(cliente);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ListaClientes;
	}

	
	
  
	@Override
	public boolean update(Cliente cli) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean UpDateExitoso = false;
		
		try {
			statement = conexion.prepareStatement(update);
			statement.setString(1, cli.getNombre());
			statement.setString(2, cli.getApellido());
			statement.setString(3, cli.getDni());
			statement.setString(4, cli.getCuil());
			statement.setString(5, cli.getDireccion());
			statement.setString(6, cli.getTelefono());
			statement.setString(7, cli.getFecha_nac());
            statement.setInt(8, cli.getCod_Genero().getCod_genero());
			statement.setInt(9, cli.getCod_nacionalidad().getCod_nacionalidad());	
			statement.setInt(10, cli.getCod_provincia().getCod_provincia());
			statement.setInt(11, cli.getCod_localidad().getCod_localidad());
			statement.setString(12, cli.getEmail());
			statement.setInt(13, cli.getNro_Cliente());
			
			if(statement.executeUpdate()>0){
				
				conexion.commit();
				UpDateExitoso = true;
			}
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		
		return UpDateExitoso;
	}

	

	@Override
	public ArrayList<Cliente> CargarSegunCondicion(String condicion, int Codigo) {
		PreparedStatement Statement;
		ResultSet resultSet;
		ArrayList<Cliente> ListaClientes = new ArrayList<Cliente>();
		Conexion conexion = Conexion.getConexion();
		
		try {
			Statement = conexion.getSQLConexion().prepareStatement("SELECT clientes.Nro_Cliente, clientes.Nombre, clientes.Apellido, clientes.Dni,"
					+ " clientes.Cuil, clientes.Direccion, clientes.Telefono, clientes.Fecha_nac AS Fecha_Nacimiento, "
					+ "generos.Descripcion AS Genero, nacionalidad.Descripcion AS Nacionalidad, provincias.Descripcion "
					+ "AS Provincia, localidades.Descripcion AS Localidad, clientes.Email, usuarios.Usuario, usuarios.Contraseña "
					+ "FROM clientes INNER JOIN generos ON clientes.Cod_Genero = generos.Cod_genero INNER JOIN nacionalidad"
					+ " ON clientes.Cod_nacionalidad = nacionalidad.Cod_nacionalidad INNER JOIN provincias ON "
					+ "clientes.Cod_provincia = provincias.Cod_provincia INNER JOIN localidades "
					+ "ON clientes.Cod_provincia = localidades.Cod_provincia AND clientes.Cod_localidad = localidades.Cod_localidad"
					+ " INNER JOIN usuarios ON clientes.Usuario = usuarios.Usuario "
					+ "WHERE clientes.Estado=1 AND clientes.Cod_"+condicion+" = " + Codigo);
			resultSet = Statement.executeQuery();
			while(resultSet.next()) {
				Genero G = new Genero();
				Nacionalidad Nac = new Nacionalidad();
				Provincia Prov = new Provincia();
				Localidad Loc = new Localidad();
				Usuario User = new Usuario();
				
				int Nro_Cliente = resultSet.getInt(1);
				String Nombre = resultSet.getString(2);
				String Apellido = resultSet.getString(3);
				String Dni = resultSet.getString(4);
				String Cuil = resultSet.getString(5);
				String Direccion = resultSet.getString(6);
				String Telefono = resultSet.getString(7);
				String FechaNac = resultSet.getString(8);
				G.setDescripcion(resultSet.getString(9));
				Nac.setDescripcion(resultSet.getString(10));
				Prov.setDescripcion(resultSet.getString(11));
				Loc.setDescripcion(resultSet.getString(12));
				String Mail = resultSet.getString(13);
				User.setUsuario(resultSet.getString(14));
				User.setContrasenia(resultSet.getString(15));
				
				Cliente cliente = new Cliente(Nro_Cliente,Dni,Cuil,Nombre,Apellido,G,Nac,FechaNac,Direccion,Loc,Prov,Mail,Telefono,User,true);
			    ListaClientes.add(cliente);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ListaClientes;
	}

	@Override
	public Cliente getClientePorUsuario(Cliente c) {
		Cliente cliente = null;

		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT clientes.Nro_Cliente, clientes.Nombre, clientes.Apellido, clientes.Dni,"
					+ " clientes.Cuil, clientes.Direccion, clientes.Telefono, clientes.Fecha_nac AS Fecha_Nacimiento, "
					+ "generos.Descripcion AS Genero, nacionalidad.Descripcion AS Nacionalidad, provincias.Descripcion "
					+ "AS Provincia, localidades.Descripcion AS Localidad, clientes.Email, usuarios.Usuario, usuarios.Contraseña "
					+ "FROM clientes INNER JOIN generos ON clientes.Cod_Genero = generos.Cod_genero INNER JOIN nacionalidad"
					+ " ON clientes.Cod_nacionalidad = nacionalidad.Cod_nacionalidad INNER JOIN provincias ON "
					+ "clientes.Cod_provincia = provincias.Cod_provincia INNER JOIN localidades "
					+ "ON clientes.Cod_provincia = localidades.Cod_provincia AND clientes.Cod_localidad = localidades.Cod_localidad"
					+ " INNER JOIN usuarios ON clientes.Usuario = usuarios.Usuario "
					+ "WHERE clientes.Estado=1 AND clientes.Usuario = '" + c.getUsuario().getUsuario() + "'");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Genero G = new Genero();
				Nacionalidad Nac = new Nacionalidad();
				Provincia Prov = new Provincia();
				Localidad Loc = new Localidad();
				Usuario User = new Usuario();
				
				int Nro_Cliente = resultSet.getInt(1);
				String Nombre = resultSet.getString(2);
				String Apellido = resultSet.getString(3);
				String Dni = resultSet.getString(4);
				String Cuil = resultSet.getString(5);
				String Direccion = resultSet.getString(6);
				String Telefono = resultSet.getString(7);
				String FechaNac = resultSet.getString(8);
				G.setDescripcion(resultSet.getString(9));
				Nac.setDescripcion(resultSet.getString(10));
				Prov.setDescripcion(resultSet.getString(11));
				Loc.setDescripcion(resultSet.getString(12));
				String Mail = resultSet.getString(13);
				User.setUsuario(resultSet.getString(14));
				User.setContrasenia(resultSet.getString(15));
				
				cliente = new Cliente(Nro_Cliente,Dni,Cuil,Nombre,Apellido,G,Nac,FechaNac,Direccion,Loc,Prov,Mail,Telefono,User,true);
			}
			
		}catch(SQLException e){
			
		}
		
		return cliente;
	}
}
	





