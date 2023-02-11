package negocio;

import java.util.ArrayList;

import entidades.Cliente;

public interface ClienteNegocio {

	public boolean insert(Cliente cli);
	public boolean delete(Cliente cli);
	public int obtenerProxId();
	
    public ArrayList<Cliente> MostrarTodos();
	public ArrayList<Cliente> LeerSegunUsuario(String User);
    public boolean update(Cliente cli);
    public Cliente getClientePorUsuario(Cliente c);

	public ArrayList<Cliente> CargarSegunCondicion(String condicion, int Codigo);
}
