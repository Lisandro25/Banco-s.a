package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProvinciaDao;
import entidades.Provincia;

public class ProvinciaDaoImp implements ProvinciaDao {

	private static final String readall = "SELECT * FROM provincias";

	@Override
	public List<Provincia> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Provincia> list = new ArrayList<Provincia>();
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

	private Provincia get(ResultSet resultSet) throws SQLException
	{
		int Cod_provincia = resultSet.getInt("Cod_provincia");
		String descripcion = resultSet.getString("Descripcion");
		return new Provincia(Cod_provincia, descripcion);
	}
	
}
