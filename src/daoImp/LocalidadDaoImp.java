package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import entidades.Localidad;
import entidades.Provincia;

public class LocalidadDaoImp implements LocalidadDao {

	private static final String readall = "SELECT localidades.Cod_provincia, provincias.Descripcion, localidades.Cod_localidad, localidades.Descripcion FROM localidades INNER JOIN provincias ON localidades.Cod_provincia = provincias.Cod_provincia";
	
	@Override
	public List<Localidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> list = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
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
	
	private Localidad get(ResultSet resultSet) throws SQLException
	{
		Localidad loc = new Localidad();
		Provincia prov = new Provincia();
		loc.setCod_localidad(resultSet.getInt("localidades.Cod_localidad"));
		prov.setDescripcion(resultSet.getString("provincias.Descripcion"));
		loc.setCod_provincia(prov);
		loc.setDescripcion(resultSet.getString("localidades.Descripcion"));
		return loc;
	}

}
