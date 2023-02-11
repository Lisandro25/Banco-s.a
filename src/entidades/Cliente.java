package entidades;


public class Cliente {
	
	///ATRIBUTOS
	private int Nro_Cliente;
	private String Dni;
	private String Cuil;
    private String Nombre;
    private String Apellido;
    private Genero Cod_Genero;
    private Nacionalidad Cod_nacionalidad;
    private String Fecha_nac;
    private String Direccion;
    private Localidad Cod_localidad;
    private Provincia Cod_provincia;
    private String Email;
    private String Telefono;
    private Usuario Usuario;
    private Boolean Estado;
    
    ///CONSTRUCTORES
	public Cliente() {}

	public Cliente(int nro_Cliente, String dni, String cuil, String nombre, String apellido, Genero cod_Genero, Nacionalidad cod_nacionalidad, String fecha_nac, String direccion, Localidad cod_localidad, Provincia cod_provincia, String email, String telefono, Usuario usuario, Boolean estado) {
		super();
		Nro_Cliente = nro_Cliente;
		Dni = dni;
		Cuil = cuil;
		Nombre = nombre;
		Apellido = apellido;
		Cod_Genero = cod_Genero;
		Cod_nacionalidad = cod_nacionalidad;
		Fecha_nac = fecha_nac;
		Direccion = direccion;
		Cod_localidad = cod_localidad;
		Cod_provincia = cod_provincia;
		Email = email;
		Telefono = telefono;
		Usuario = usuario;
		Estado = estado;
	}

	///GETTERS & SETTERS
	public int getNro_Cliente() {
		return Nro_Cliente;
	}

	public void setNro_Cliente(int nro_Cliente) {
		Nro_Cliente = nro_Cliente;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getCuil() {
		return Cuil;
	}

	public void setCuil(String cuil) {
		Cuil = cuil;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public Genero getCod_Genero() {
		return Cod_Genero;
	}

	public void setCod_Genero(Genero cod_Genero) {
		Cod_Genero = cod_Genero;
	}

	public Nacionalidad getCod_nacionalidad() {
		return Cod_nacionalidad;
	}

	public void setCod_nacionalidad(Nacionalidad cod_nacionalidad) {
		Cod_nacionalidad = cod_nacionalidad;
	}

	public String getFecha_nac() {
		return Fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		Fecha_nac = fecha_nac;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public Localidad getCod_localidad() {
		return Cod_localidad;
	}

	public void setCod_localidad(Localidad cod_localidad) {
		Cod_localidad = cod_localidad;
	}

	public Provincia getCod_provincia() {
		return Cod_provincia;
	}

	public void setCod_provincia(Provincia cod_provincia) {
		Cod_provincia = cod_provincia;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public Usuario getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
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
		return "Nro_Cliente=" + Nro_Cliente + ", Dni=" + Dni + ", Cuil=" + Cuil + ", Nombre=" + Nombre
				+ ", Apellido=" + Apellido + ", Genero=" + Cod_Genero + ", Nacionalidad=" + Cod_nacionalidad
				+ ", Fecha Nacimiento=" + Fecha_nac + ", Direccion=" + Direccion + ", Localidad=" + Cod_localidad
				+ ", Provincia=" + Cod_provincia + ", Email=" + Email + ", Telefono=" + Telefono + ", Usuario=" + Usuario + ", Estado="
				+ Estado;
	}
    
}
