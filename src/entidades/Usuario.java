package entidades;

public class Usuario {

	///ATRIBUTOS
	public String Usuario;
    public TipoUsuario Tipo_Us;
    public String Contrasenia;
    public Boolean Estado;
    
    ///CONSTRUCTORES
    public Usuario(){}
    
	public Usuario(String usuario, TipoUsuario tipo_Us, String contraseña, Boolean estado) {
		super();
		Usuario = usuario;
		Tipo_Us = tipo_Us;
		Contrasenia = contraseña;
		Estado = estado;
	}

	///GETTERS & SETTERS
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public TipoUsuario getTipo_Us() {
		return Tipo_Us;
	}

	public void setTipo_Us(TipoUsuario tipo_Us) {
		Tipo_Us = tipo_Us;
	}

	public String getContraseña() {
		return Contrasenia;
	}

	public void setContrasenia(String contraseña) {
		Contrasenia = contraseña;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}

	///METODO TOSTRING()
	@Override
	public String toString() {
		return Usuario;
	} 
}
