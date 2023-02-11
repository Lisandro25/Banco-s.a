package entidades;

public class TipoUsuario {

	///ATRIBUTOS
	private int Tipo_us;
    private String Descripcion;
    
    ///CONSTRUCTORES
    public TipoUsuario(){}
    
	public TipoUsuario(int tipo_us, String descripcion) {
		super();
		Tipo_us = tipo_us;
		Descripcion = descripcion;
	}

	///GETTERS & SETTERS
	public int getTipo_us() {
		return Tipo_us;
	}

	public void setTipo_us(int tipo_us) {
		Tipo_us = tipo_us;
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
		return "" + Tipo_us + " - " + Descripcion + "";
	}
    
}
