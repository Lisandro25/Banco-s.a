package entidades;

public class Provincia {
	
	///ATRIBUTOS
	    private int Cod_provincia;
	    private String Descripcion;
	    
	///CONSTRUCTORES
	    public Provincia(){}
	    
		public Provincia(int cod_provincia, String descripcion) {
			super();
			Cod_provincia = cod_provincia;
			Descripcion = descripcion;
		}

	///GETTERS & SETTERS
		public int getCod_provincia() {
			return Cod_provincia;
		}

		public void setCod_provincia(int cod_provincia) {
			Cod_provincia = cod_provincia;
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
