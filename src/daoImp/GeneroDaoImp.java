package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GeneroDao;
import entidades.Genero;

public class GeneroDaoImp implements GeneroDao {

	private static final String readall = "SELECT * FROM generos";
	
	@Override
	public List<Genero> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Genero> list = new ArrayList<Genero>();
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
	
	private Genero get(ResultSet resultSet) throws SQLException
	{
		int Cod_genero = resultSet.getInt("Cod_genero");
		String descripcion = resultSet.getString("Descripcion");
		return new Genero(Cod_genero, descripcion);
	}

}
