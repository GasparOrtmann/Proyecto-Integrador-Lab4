package iDao;

import java.util.List;

import Entidades.Movimiento;

public interface iDaoMovimientos {

	List<Movimiento> traerLista(int idCuenta);

	String traerTipoMovimiento(int Id);

	List<Movimiento> traerHistorial(int idUsuario);
	
	int cantTransacciones();
	
	int movimientoPositivo(int idCuenta,float monto);
	int movimientoNegativo(int idCuenta,float monto);
	int generarTransferncia(int cuentaOrigen,int cuentaDestino,float monto);
	int proxId();
}
