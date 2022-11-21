package iDao;

import java.util.List;

import Entidades.Movimiento;

public interface iDaoMovimientos {

	List<Movimiento> traerLista(int idCuenta);

	String traerTipoMovimiento(int Id);

	List<Movimiento> traerHistorial(int idUsuario);
	
	int cantTransacciones();
	
	int movimientoPositivo(int cuentaOrigen, int cuentaDestino,float monto);
	int movimientoNegativo(int cuentaOrigen, int cuentaDestino,float monto);
	int generarTransferncia(int cuentaOrigen,int cuentaDestino,float monto);
	int sumarSaldo(int idCuenta,float monto);
	int restarSaldo(int idCuenta,float monto);
	int proxId();
	String buscarNombre(int id);

	int cantMovimientosUsuario(int idUsuario);

	int cantMovimientosCuenta(int idCuenta);
	Boolean agregarMovimiento(Movimiento m);
}
