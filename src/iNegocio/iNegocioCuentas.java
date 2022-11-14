package iNegocio;

import java.util.List;

import Entidades.Cuenta;

public interface iNegocioCuentas {
	List<Cuenta> traerLista();
	Boolean agregarCuenta(Cuenta c);
	Integer traerProxId();
	boolean eliminarCuenta(String id);
	int modificar(Cuenta c);
	Cuenta traerCuenta(String id);
	List<Cuenta> FiltrarPorUsuario(String id,Boolean actividad);
}
