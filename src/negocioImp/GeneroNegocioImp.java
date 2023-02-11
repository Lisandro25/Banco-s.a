package negocioImp;

import java.util.List;

import dao.GeneroDao;
import daoImp.GeneroDaoImp;
import entidades.Genero;
import negocio.GeneroNegocio;

public class GeneroNegocioImp implements GeneroNegocio{

	GeneroDao gdao = new GeneroDaoImp();
	
	@Override
	public List<Genero> readAll() {
		return gdao.readAll();
	}

}
