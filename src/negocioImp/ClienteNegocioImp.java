package negocioImp;

import java.util.ArrayList;

import dao.ClienteDao;
import daoImp.ClienteDaoImp;
import entidades.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImp implements ClienteNegocio{

	ClienteDao cdao = new ClienteDaoImp();
	
	@Override
	public boolean insert(Cliente cli) {
		if(cdao.existeCliente(cli)==true) return false;
		else return cdao.insert(cli);
	}

	@Override
	public boolean delete(Cliente cli) {
		if(cdao.existeDni(cli)==false) return false;
		else return cdao.delete(cli);
	}
	
	public int obtenerProxId() {
		return cdao.obtenerProxId();
	}

	@Override
	public ArrayList<Cliente> MostrarTodos() {
		return cdao.readAll();
	}

	@Override
	public ArrayList<Cliente> LeerSegunUsuario(String User) {
		return cdao.LeerSegunUsuario(User);
	}

	@Override
	public boolean update(Cliente cli) {
		return cdao.update(cli);
	}

	

	@Override
	public ArrayList<Cliente> CargarSegunCondicion(String condicion, int Codigo) {
		return cdao.CargarSegunCondicion(condicion, Codigo);
	}

	@Override
	public Cliente getClientePorUsuario(Cliente c) {
		return cdao.getClientePorUsuario(c);
	}

}
