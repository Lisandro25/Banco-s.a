package entidades;

public class TipoCuenta {

	///ATRIBUTOS
	public int Tipo_cuenta;
    public String Descripcion;
    
    ///CONSTRUCTORES
    public TipoCuenta(){}
    
	public TipoCuenta(int tipo_cuenta, String descripcion) {
		super();
		Tipo_cuenta = tipo_cuenta;
		Descripcion = descripcion;
	}

	///GETTERS & SETTERS
	public int getTipo_cuenta() {
		return Tipo_cuenta;
	}

	public void setTipo_cuenta(int tipo_cuenta) {
		Tipo_cuenta = tipo_cuenta;
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
		return "" + Tipo_cuenta + " - " + Descripcion + "";
	}
    
}
