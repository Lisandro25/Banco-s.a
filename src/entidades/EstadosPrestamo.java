package entidades;

public class EstadosPrestamo {
	
	    ///ATRIBUTOS
		private int Est_prestamo;
	    private String Descripcion;
	    
	    ///CONSTRUCTORES
	    public EstadosPrestamo() {}
	    
		public EstadosPrestamo(int est_prestamo, String descripcion) {
			super();
			Est_prestamo = est_prestamo;
			Descripcion = descripcion;
		}

		///GETTERS & SETTERS
		public int getEst_prestamo() {
			return Est_prestamo;
		}

		public void setEst_prestamo(int est_prestamo) {
			Est_prestamo = est_prestamo;
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
			return "Est_prestamo=" + Est_prestamo + ", Descripcion=" + Descripcion + "";
		}
	    
	    
	    
}
