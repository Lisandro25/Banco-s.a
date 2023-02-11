package entidades;

public class TipoMovimiento {
	
	///ATRIBUTOS
		public int Tipo_mov;
	    public String Descripcion;
	    
	    ///CONSTRUCTORES
	    public TipoMovimiento(){}
	    
		public TipoMovimiento(int tipo_movimiento, String descripcion) {
			super();
			Tipo_mov = tipo_movimiento;
			Descripcion = descripcion;
		}

		///GETTERS & SETTERS
		public int getTipo_movimiento() {
			return Tipo_mov;
		}

		public void setTipo_movimiento(int tipo_movimiento) {
			Tipo_mov = tipo_movimiento;
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
			return "" + Tipo_mov + " - " + Descripcion + "";
		}
}
