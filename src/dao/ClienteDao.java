package dao;

import java.util.ArrayList;

import entidades.Cliente;

public interface ClienteDao {
	
	public boolean insert(Cliente cli);
	public boolean delete(Cliente cli);
	public int obtenerProxId();
	public boolean existeDni(Cliente cli);
	public boolean existeCliente(Cliente cli);
	
	public ArrayList<Cliente> readAll();
	public ArrayList<Cliente> LeerSegunUsuario(String User);
	public boolean update(Cliente cli);
	public Cliente getClientePorUsuario(Cliente c);

	public ArrayList<Cliente> CargarSegunCondicion(String condicion, int Codigo);
}
