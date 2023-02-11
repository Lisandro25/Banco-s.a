package dao;

import java.util.ArrayList;
import java.util.List;

import entidades.Prestamo;

public interface PrestamoDao {

	public boolean aprobarPrestamo(Prestamo p);
	public boolean rechazarPrestamo(Prestamo p);
	public List<Prestamo> readAll();
	public List<Prestamo> prestamoXfecha(String fecha1, String fecha2, String filtro);
	public List<Prestamo> Solicitudes();
	public ArrayList<Prestamo> obtenerPrestamosQueryCustom(String consulta, String filtro);
	
	public int ObtenerProxNro_Prestamo();
	public boolean insert(Prestamo prestamo);
	public ArrayList<Prestamo> GetPorCliente(int Nro_Cliente);
	
	public int pagarPrestamoCuota(int Nro_cliente,int Nro_prestamo);
	ArrayList<Prestamo> GetListaPagarCuotas(int Nro_Cliente);
}
