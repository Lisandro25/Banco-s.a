package negocio;

import entidades.Cuenta;
import entidades.TipoCuenta;
import negocioImp.ClienteNegocioImp;
import negocioImp.CuentaNegocioImp;

public class prueba {

	public static void main(String[] args) {
		ClienteNegocioImp cneg = new ClienteNegocioImp();
		int maxId = cneg.obtenerProxId();
		Cuenta cu = new Cuenta();
    	CuentaNegocioImp cn = new CuentaNegocioImp();
    	cu.setNro_cliente(maxId);
    	cu.setTipo_cuenta(new TipoCuenta(1,""));
        cu.setCbu(cn.CbuAleatorio());
        cu.setSaldo(10000);
    	cu.setEstado(true);
    	cn.insert(cu);
    	System.out.print("Nro " +cu.getNro_cliente());
    	System.out.print("Tipo " +cu.getTipo_cuenta().getTipo_cuenta());
    	System.out.print("Cbu " +cu.getCbu());
    	System.out.print("Saldo " +cu.getSaldo());
    	System.out.print("Estado " +cu.getEstado());

	}

}
