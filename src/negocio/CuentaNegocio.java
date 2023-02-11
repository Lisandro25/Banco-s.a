package negocio;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaNegocio {

	public int insert(Cuenta cu);
	public boolean delete(Cuenta cu);	
	public boolean update(Cuenta cu);
	public Cuenta get(Cuenta cu);
	
	public ArrayList<Cuenta> obtenerCuentas();
	
	public ArrayList<Cuenta> obtenerCuentaQueryCustom(String consulta, String filtro);
	
	public ArrayList<Cuenta> obtenerCuentaPorNr_cuenta(String numero);
	
	public boolean modificarCuenta(Cuenta c);
	
	public int totalCuentasPorCliente(int nroCliente);
	
	public ArrayList<Cuenta> getCuentasXCliente(String NomUsuario);
	
	public int NroClienteSegunNombreCliente(String NombreClie);
}

