package iDao;

import java.util.List;

import Entidades.Cuenta;
import Entidades.Usuario;

public interface iDaoCuentas {
	List<Cuenta> traerLista();
	Boolean agregarCuenta(Cuenta c);
	Integer traerProxId();
	boolean eliminarCuenta(String id);
	int modificar(Cuenta c);
	Cuenta traerCuenta(String id);
	
	List<Cuenta> filtrarPorUsuario(String id,Boolean actividad);
}
