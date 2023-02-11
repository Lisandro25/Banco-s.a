package negocioImp;

import java.util.List;

import dao.LocalidadDao;
import daoImp.LocalidadDaoImp;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImp implements LocalidadNegocio{

	LocalidadDao ldao = new LocalidadDaoImp();
	
	@Override
	public List<Localidad> readAll() {
		// TODO Auto-generated method stub
		return ldao.readAll();
	}

}
