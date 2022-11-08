package iDao;

import java.util.List;

import Entidades.Cuenta;

public interface iDaoCuentas {
	List<Cuenta> traerLista();
	Boolean agregarCuenta(Cuenta c);
	Integer traerProxId();
	boolean eliminarCuenta(String id);
}
