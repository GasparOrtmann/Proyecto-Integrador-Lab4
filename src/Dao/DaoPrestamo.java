package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuenta;
import Entidades.Prestamo;
import Entidades.TipoCuenta;
import iDao.iDaoPrestamo;

public class DaoPrestamo implements iDaoPrestamo{

	/*	 USE `bdbanco`;
	DROP procedure IF EXISTS `SP_agregarPrestamo`;
	
	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_agregarPrestamo` (IN pId int, IN pIdUsu int, IN monto float,IN cuotaValor float, IN cantCuota int, IN fechaAlta varchar(10))
	BEGIN
	INSERT INTO Prestamos (IdPrestamo,IdUsuario,MontoPrestado,MontoTotalAdeudado,ImporteCuotaFija,CantidadCuotas,CuotasAdeudadas,CuotasPagas,FechaAlta,Estado) 
					values (pId,pIdUsu,monto,null,cuotaValor,cantCuota,null,null,fechaAlta,'Pendiente');
	END$$
	
	DELIMITER ;*/
	
	public Boolean agregarPrestamo(Prestamo prestamo) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_agregarPrestamo(?,?,?,?,?,?)");
			
			cst.setInt(1,prestamo.getIdPrestamo());
			cst.setInt(2,prestamo.getIdUsuario());
			cst.setFloat(3,prestamo.getMontoPrestamo());
			cst.setFloat(4,prestamo.getImporteCuotaFija());
			cst.setFloat(5,prestamo.getCantidadCuotas());
			cst.setString(6,prestamo.getFechaAlta());

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
		
	public int  traerProxId() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		String query = "Select MAX(IdPrestamo) AS id from prestamos";
		int id=0;
		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			 id = rs.getInt("id");
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id+1;
	}	
		
	public List<Prestamo> traerListaPrestamos() {
		
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		List<Prestamo> lstPrestamos = new ArrayList<Prestamo>();
		
		String query = "SELECT * FROM prestamos";
		PreparedStatement pst;
		
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				
				int idP = rs.getInt(1);
				int idU = rs.getInt(2);
				float montoP= rs.getFloat(3);
				float montoPAdeu= rs.getFloat(4);
				float cuotaFija=rs.getFloat(5);
				int cantCuotas = rs.getInt(6);
				int cuotDeuda=rs.getInt(7);
				int cuotPagas=rs.getInt(8);
				String fechaAlta=rs.getString(9);
				String estado=rs.getString(10);

				lstPrestamos.add(new Prestamo(idP, idU, montoP, montoPAdeu, cuotaFija, cantCuotas, cuotDeuda,cuotPagas,fechaAlta,estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPrestamos;
	}
	
}
