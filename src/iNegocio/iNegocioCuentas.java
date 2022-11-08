package iNegocio;

import java.util.List;

import Entidades.Cuenta;

public interface iNegocioCuentas {
	List<Cuenta> traerLista();
	Boolean agregarCuenta(Cuenta c);
	Integer traerProxId();
	Boolean eliminarCuenta(String id);
}
