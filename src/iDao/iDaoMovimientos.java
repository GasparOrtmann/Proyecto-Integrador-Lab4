package iDao;

import java.util.List;

import Entidades.Movimiento;

public interface iDaoMovimientos {

	List<Movimiento> traerLista(int idCuenta);

	String traerTipoMovimiento(int Id);

	List<Movimiento> traerHistorial(int idUsuario);
	
	int cantTransacciones();
	
	int movimientoPositivo(String cbuOrigen, String cbuDestino,float monto);
	int movimientoNegativo(String cbuOrigen, String cbuDestino,float monto);
	int generarTransferncia(String cbuOrigen, String cbuDestino,float monto);
	int sumarSaldo(String cbu,float monto);
	int restarSaldo(String cbu,float monto);
	int proxId();
	String buscarNombre(String cbu);

	int cantMovimientosUsuario(int idUsuario);

	int cantMovimientosCuenta(int idCuenta);
	Boolean agregarMovimiento(Movimiento m);
}
