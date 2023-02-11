package entidades;

public class Nacionalidad {
	
	///ATRIBUTOS
	private int Cod_nacionalidad;
    private String Descripcion;
    
    ///CONSTRUCTORES
    public Nacionalidad(){}
    
	public Nacionalidad(int cod_nacionalidad, String descripcion) {
		super();
		Cod_nacionalidad = cod_nacionalidad;
		Descripcion = descripcion;
	}

	///GETTERS & SETTERS
	public int getCod_nacionalidad() {
		return Cod_nacionalidad;
	}

	public void setCod_nacionalidad(int cod_nacionalidad) {
		Cod_nacionalidad = cod_nacionalidad;
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
