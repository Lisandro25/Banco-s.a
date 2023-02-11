package entidades;

public class Localidad {
	
	///ATRIBUTOS
	private Provincia Cod_provincia;
    private int Cod_localidad;
    private String Descripcion;
    
    ///CONSTRUCTORES
    public Localidad(){}
    
	public Localidad(Provincia cod_provincia, int cod_localidad, String descripcion) {
		super();
		Cod_provincia = cod_provincia;
		Cod_localidad = cod_localidad;
		Descripcion = descripcion;
	}

	///GETTERS & SETTERS
	public Provincia getCod_provincia() {
		return Cod_provincia;
	}

	public void setCod_provincia(Provincia cod_provincia) {
		Cod_provincia = cod_provincia;
	}

	public int getCod_localidad() {
		return Cod_localidad;
	}

	public void setCod_localidad(int cod_localidad) {
		Cod_localidad = cod_localidad;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	///METODO TOSTRING()
	@Override
	public String toString() {
		return Descripcion;
	}
    
	
}
