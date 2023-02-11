package negocioImp;

import java.util.List;

import dao.MovimientoDao;
import daoImp.CuentaDaoImp;
import daoImp.MovimientoDaoImp;
import entidades.Cuenta;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImp implements MovimientoNegocio{

	MovimientoDao mdao = new MovimientoDaoImp();

	@Override
	public List<Movimiento> readAll() {
		return mdao.readAll();
	}

	@Override
	public int dineroTotal() {
		return mdao.dineroTotal();
	}

	@Override
	public List<Movimiento> movimientoXfecha(String fecha1, String fecha2, String filtro) {
		return mdao.movimientoXfecha(fecha1, fecha2, filtro);
	}

	@Override
	public List<Movimiento> getMovCuenta(Cuenta c) {
		return mdao.getMovCuenta(c);
	}

	public boolean ejecutarTransferencia(String origen,String destinoCBU,float importe) {
		CuentaDaoImp cuentaDao = new CuentaDaoImp();
		boolean transaccion = false;
		try {
			Cuenta cDestino = cuentaDao.getCuentaXcbu(destinoCBU);
			String destino = Integer.toString(cDestino.getNro_cuenta());

			if(cDestino!=null) {
				transaccion = mdao.ejecutarTransferencia(origen, destino, importe);
				System.out.println(transaccion);
			}
			
		}catch(Exception e) {
			
		}finally {
			
		}
		return transaccion;
	}

}
