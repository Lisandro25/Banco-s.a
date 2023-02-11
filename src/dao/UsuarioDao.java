package dao;

import entidades.Usuario;

public interface UsuarioDao {

	public boolean existeNombreUsuario(Usuario usu);
	public boolean existeUsuario(Usuario usu);
	public boolean esAdmin(Usuario usu);
	
	public boolean update(Usuario usu);
}
