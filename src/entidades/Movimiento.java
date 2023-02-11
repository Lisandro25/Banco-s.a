package entidades;

public class Movimiento {
	
	///ATRIBUTOS
		private int Nro_Movimiento;
	    private int Nro_Cuenta;
	    private String Fecha;
	    private TipoMovimiento Tipo_Mov;
	    private float Importe;
	    private String Detalle;
	    
	    ///CONSTRUCTORES
		public Movimiento() {}

		public Movimiento(int nro_Movimiento, int nro_Cuenta, String fecha, TipoMovimiento tipo_Mov, float importe,
				String detalle) {
			super();
			Nro_Movimiento = nro_Movimiento;
			Nro_Cuenta = nro_Cuenta;
			Fecha = fecha;
			Tipo_Mov = tipo_Mov;
			Importe = importe;
			Detalle = detalle;
		}

		///GETTERS & SETTERS
		public int getNro_Movimiento() {
			return Nro_Movimiento;
		}

		public void setNro_Movimiento(int nro_Movimiento) {
			Nro_Movimiento = nro_Movimiento;
		}

		public int getNro_Cuenta() {
			return Nro_Cuenta;
		}

		public void setNro_Cuenta(int nro_Cuenta) {
			Nro_Cuenta = nro_Cuenta;
		}

		public String getFecha() {
			return Fecha;
		}

		public void setFecha(String fecha) {
			Fecha = fecha;
		}

		public TipoMovimiento getTipo_Mov() {
			return Tipo_Mov;
		}

		public void setTipo_Mov(TipoMovimiento tipo_Mov) {
			Tipo_Mov = tipo_Mov;
		}

		public float getImporte() {
			return Importe;
		}

		public void setImporte(float importe) {
			Importe = importe;
		}

		public String getDetalle() {
			return Detalle;
		}

		public void setDetalle(String detalle) {
			Detalle = detalle;
		}

		///METODO TOSTRING()
		@Override
		public String toString() {
			return "Nro_Movimiento=" + Nro_Movimiento + ", Nro_Cuenta=" + Nro_Cuenta + ", Fecha=" + Fecha
					+ ", Tipo_Mov=" + Tipo_Mov + ", Importe=" + Importe + ", Detalle=" + Detalle + "";
		}		
		
		
		
}
