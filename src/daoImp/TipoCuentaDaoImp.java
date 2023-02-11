package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoCuentaDao;
import entidades.TipoCuenta;

public class TipoCuentaDaoImp implements TipoCuentaDao {

	private static final String readall = "SELECT * FROM tiposCuentas";

	@Override
	public List<TipoCuenta> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoCuenta> list = new ArrayList<TipoCuenta>();
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

	private TipoCuenta get(ResultSet resultSet) throws SQLException
	{
		int Tipo_cuenta = resultSet.getInt("Tipo_cuenta");
		String descripcion = resultSet.getString("Descripcion");
		return new TipoCuenta(Tipo_cuenta, descripcion);
	}
}
