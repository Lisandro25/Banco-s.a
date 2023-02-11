package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.NacionalidadDao;
import entidades.Nacionalidad;

public class NacionalidadDaoImp implements NacionalidadDao{

	private static final String readall = "SELECT * FROM nacionalidad";

	@Override
	public List<Nacionalidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Nacionalidad> list = new ArrayList<Nacionalidad>();
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

	private Nacionalidad get(ResultSet resultSet) throws SQLException
	{
		int Cod_nacionalidad = resultSet.getInt("Cod_nacionalidad");
		String descripcion = resultSet.getString("Descripcion");
		return new Nacionalidad(Cod_nacionalidad, descripcion);
	}
}
