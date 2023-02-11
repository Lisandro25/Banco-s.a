package entidades;

public class Prestamo {

	///ATRIBUTOS
	private int Nro_prestamo;
	private Cliente Nro_cliente;
	private String Fecha;
	private float Imp_con_intereses;
	private float Imp_solicitado;
	private int Nro_cuenta_deposito;
	private int Plazo_pago_meses;
	private float Monto_pago_por_mes;
	private int Cant_cuotas;
	private EstadosPrestamo Est_prestamo;
	
	///CONSTRUCTORES
    public Prestamo() {}
	
	public Prestamo(int nro_prestamo, Cliente nro_cliente, String fecha, float imp_con_intereses, float imp_solicitado,
			int nro_cuenta_deposito, int plazo_pago_meses, float monto_pago_por_mes, int cant_cuotas,
			EstadosPrestamo est_prestamo) {
		super();
		Nro_prestamo = nro_prestamo;
		Nro_cliente = nro_cliente;
		Fecha = fecha;
		Imp_con_intereses = imp_con_intereses;
		Imp_solicitado = imp_solicitado;
		Nro_cuenta_deposito = nro_cuenta_deposito;
		Plazo_pago_meses = plazo_pago_meses;
		Monto_pago_por_mes = monto_pago_por_mes;
		Cant_cuotas = cant_cuotas;
		Est_prestamo = est_prestamo;
	}

	///GETTERS & SETTERS
	public int getNro_prestamo() {
		return Nro_prestamo;
	}

	public void setNro_prestamo(int nro_prestamo) {
		Nro_prestamo = nro_prestamo;
	}

	public Cliente getNro_cliente() {
		return Nro_cliente;
	}

	public void setNro_cliente(Cliente nro_cliente) {
		Nro_cliente = nro_cliente;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public float getImp_con_intereses() {
		return Imp_con_intereses;
	}

	public void setImp_con_intereses(float imp_con_intereses) {
		Imp_con_intereses = imp_con_intereses;
	}

	public float getImp_solicitado() {
		return Imp_solicitado;
	}

	public void setImp_solicitado(float imp_solicitado) {
		Imp_solicitado = imp_solicitado;
	}

	public int getNro_cuenta_deposito() {
		return Nro_cuenta_deposito;
	}

	public void setNro_cuenta_deposito(int nro_cuenta_deposito) {
		Nro_cuenta_deposito = nro_cuenta_deposito;
	}

	public int getPlazo_pago_meses() {
		return Plazo_pago_meses;
	}

	public void setPlazo_pago_meses(int plazo_pago_meses) {
		Plazo_pago_meses = plazo_pago_meses;
	}

	public float getMonto_pago_por_mes() {
		return Monto_pago_por_mes;
	}

	public void setMonto_pago_por_mes(float monto_pago_por_mes) {
		Monto_pago_por_mes = monto_pago_por_mes;
	}

	public int getCant_cuotas() {
		return Cant_cuotas;
	}

	public void setCant_cuotas(int cant_cuotas) {
		Cant_cuotas = cant_cuotas;
	}

	public EstadosPrestamo getEst_prestamo() {
		return Est_prestamo;
	}

	public void setEst_prestamo(EstadosPrestamo est_prestamo) {
		Est_prestamo = est_prestamo;
	}

	///METODO TOSTRING()
	@Override
	public String toString() {
		return "Nro_prestamo=" + Nro_prestamo + ", Nro_cliente=" + Nro_cliente + ", Fecha=" + Fecha
				+ ", Imp_con_intereses=" + Imp_con_intereses + ", Imp_solicitado=" + Imp_solicitado
				+ ", Nro_cuenta_deposito=" + Nro_cuenta_deposito + ", Plazo_pago_meses=" + Plazo_pago_meses
				+ ", Monto_pago_por_mes=" + Monto_pago_por_mes + ", Cant_cuotas=" + Cant_cuotas + ", Est_prestamo="
				+ Est_prestamo + "";
	}
		
	
	
	
}
