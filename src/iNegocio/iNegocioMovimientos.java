package iNegocio;

import java.util.List;

import Entidades.Movimiento;

public interface iNegocioMovimientos {
	List<Movimiento> traerLista(int idCuenta);
	String traerTipoMovimiento(int Id);
	List<Movimiento> traerHistorial(int idUsuario);
	int cantTransacciones();
	Boolean generarTransferncia(int cuentaOrigen,int cuentaDestino,float monto);
	int cantMovimientosUsuario(int idUsuario);
	int cantMovimientosCuenta(int idCuenta);
	int proxId();
	Boolean agregarMovimiento(Movimiento m);
}
