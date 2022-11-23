package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import Entidades.Cuota;
import iDao.iDaoCuota;

public class DaoCuota implements iDaoCuota {

	@Override
	public Boolean pagarCuota(Cuota cuota, String fechaPago) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_pagarCuota(?,?,?)");

			cst.setInt(1,cuota.getPrestamo().getIdPrestamo());
			cst.setInt(2,cuota.getNroCuota());
			cst.setString(3, fechaPago);

			cst.executeUpdate();
			filaAfectada=cst.getUpdateCount();
			cn.commit();
			System.out.println(filaAfectada);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
		}
	
		return true;
	}
}


