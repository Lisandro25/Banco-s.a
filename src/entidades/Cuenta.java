package entidades;

public class Cuenta {

	private int Nro_cuenta;
	private int Nro_cliente;
	private String Fecha_creacion;
	private TipoCuenta Tipo_cuenta;
	private String Cbu;
	private float Saldo;
	private boolean Estado;
	
	public Cuenta () {
	}
	
	public Cuenta(int nro_cuenta, int nro_cliente, String fecha_creacion, TipoCuenta tipo_cuenta, String cbu, float saldo, boolean estado) {
		super();
		nro_cuenta = Nro_cuenta;
		Nro_cliente = nro_cliente;
		Fecha_creacion = fecha_creacion;
		Tipo_cuenta = tipo_cuenta;
		Cbu = cbu;
		Saldo = saldo;
		Estado = estado;
	}
	public String getCbu() {
		return Cbu;
	}
	public void setCbu(String cbu) {
		Cbu = cbu;
	}
	public int getNro_cuenta() {
		return Nro_cuenta;
	}
	public void setNro_cuenta(int nro_cuenta) {
		Nro_cuenta = nro_cuenta;
	}
	public int getNro_cliente() {
		return Nro_cliente;
	}
	public void setNro_cliente(int nro_cliente) {
		Nro_cliente = nro_cliente;
	}
	public String getFecha_creacion() {
		return Fecha_creacion;
	}
	public void setFecha_creacion(String fecha) {
		Fecha_creacion = fecha;
	}
	public TipoCuenta getTipo_cuenta() {
		return Tipo_cuenta;
	}
	public void setTipo_cuenta(TipoCuenta tipo_cuenta) {
		Tipo_cuenta = tipo_cuenta;
	}
	public float getSaldo() {
		return Saldo;
	}
	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	public boolean isEstado() {
		return Estado;
	}
	public boolean getEstado() {
		return Estado;
	}
	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	public String toString() {
		return Nro_cuenta + " " + Nro_cliente + " " + Fecha_creacion + " " + Tipo_cuenta + " " + Cbu + " " + Saldo;
	}
}
