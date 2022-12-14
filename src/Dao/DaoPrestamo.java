package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Cuota;
import Entidades.Prestamo;
import Entidades.Usuario;
import iDao.iDaoPrestamo;

public class DaoPrestamo implements iDaoPrestamo{

	
	@Override
	public Boolean autorizarPrestamo(int idPrestamo, String fechaAlta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_autorizarPrestamo(?,?)");
			cst.setInt(1,idPrestamo);
			cst.setString(2,fechaAlta);

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
	
	@Override
	public Boolean rechazarPrestamo(int idPrestamo, String fechaAlta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_rechazarPrestamo(?,?)");
			
			cst.setInt(1,idPrestamo);
			cst.setString(2,fechaAlta);

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

	@Override
	public Boolean agregarPrestamo(Prestamo prestamo) {
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			int filaAfectada=0;
			CallableStatement cst = cn.prepareCall("CALL SP_agregarPrestamo(?,?,?,?,?,?,?)");
			
			cst.setInt(1,prestamo.getIdPrestamo());
			cst.setInt(2,prestamo.getIdUsuario());
			cst.setFloat(3,prestamo.getMontoPrestamo());
			cst.setFloat(4,prestamo.getImporteCuotaFija());
			cst.setFloat(5,prestamo.getCantidadCuotas());
			cst.setInt(6, prestamo.getIdCuenta());
			cst.setString(7,prestamo.getFechaAlta());

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
	
	@Override	
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
	
	@Override
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
				int idCta=rs.getInt(9);
				String fechaAlta=rs.getString(10);
				String estado=rs.getString(11);

				lstPrestamos.add(new Prestamo(idP, idU, montoP, montoPAdeu, cuotaFija, cantCuotas, cuotDeuda,cuotPagas,idCta,fechaAlta,estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPrestamos;
	}
	
	@Override
	public List<Prestamo> traerListaMisPrestamos(Usuario u) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		List<Prestamo> lstMisPrestamos = new ArrayList<Prestamo>();
		
		String query = "SELECT * FROM prestamos WHERE IdUsuario="+u.getIdUsuario();
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
				int idCta=rs.getInt(9);
				String fechaAlta=rs.getString(10);
				String estado=rs.getString(11);

				lstMisPrestamos.add(new Prestamo(idP, idU, montoP, montoPAdeu, cuotaFija, cantCuotas, cuotDeuda,cuotPagas,idCta,fechaAlta,estado));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstMisPrestamos;
	}

	@Override
	public List<Cuota> traerListaCuotas(Usuario u) {
		
		Connection cnn = Conexion.getConexion().getSQLConexion();
		
		List<Cuota> lstCuotas = new ArrayList<Cuota>();
		
		String query = "SELECT NroCuota,c.IdPrestamo,FechaPago,FechaVto FROM prestamos p\r\n" + 
				"JOIN cuotas c ON p.IdPrestamo=c.IdPrestamo\r\n" + 
				"WHERE p.IdUsuario ="+u.getIdUsuario();
		
		PreparedStatement pst;
		
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Prestamo p = new Prestamo();
				int nroCuota = rs.getInt(1);
				int idPrestamo =rs.getInt(2);
				String fechaPago=rs.getString(3);
				String fechaVto=rs.getString(4);

				p.setIdPrestamo(idPrestamo);
				lstCuotas.add(new Cuota(nroCuota,p,fechaPago,fechaVto));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstCuotas;
	}

	
	
	@Override
	public int cantPrestamos() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT COUNT(*) as cantPrestamos FROM prestamos WHERE Estado='Aprobado'";
		Integer cantPrestamos = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			cantPrestamos = rs.getInt("cantPrestamos");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return cantPrestamos;
	}

	@Override
	public float gananciaPorInteres(String fechaInicioFormateada, String fechaFinFormateada) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT SUM(MontoTotalAdeudado-MontoPrestamo) as gananciaPorInteres FROM prestamos WHERE str_to_date(FechaAlta,'%d / %m / %Y')>str_to_date('"+fechaInicioFormateada+"','%d / %m / %Y') AND str_to_date(FechaAlta,'%d / %m / %Y')<str_to_date('"+fechaFinFormateada+"','%d / %m / %Y') AND Estado='Aprobado';";
		Integer gananciaPorInteres = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			gananciaPorInteres = rs.getInt("gananciaPorInteres");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return gananciaPorInteres;
	}

	@Override
	public float montoPrestado(String fechaInicioFormateada, String fechaFinFormateada) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT SUM(MontoPrestamo) as montoPrestado FROM prestamos WHERE str_to_date(FechaAlta,'%d / %m / %Y')>str_to_date('"+fechaInicioFormateada+"','%d / %m / %Y') AND str_to_date(FechaAlta,'%d / %m / %Y')<str_to_date('"+fechaFinFormateada+"','%d / %m / %Y') AND Estado='Aprobado';";
		Integer montoPrestado = 0;

		PreparedStatement pst;
		try {
			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			montoPrestado = rs.getInt("montoPrestado");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return montoPrestado;
	}

	@Override
	public int[] prestamosCedidos(int anioSeleccionado) {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		int[] prestamosCedidos = new int[12];
		String query = "SELECT COUNT(*) AS MESES FROM prestamos WHERE substr(FechaAlta,4,2)=\"01\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ " UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"02\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "  UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"03\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "  UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"04\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "  UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"05\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "  UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"06\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "  UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"07\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "   UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"08\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "   UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"09\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "   UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"10\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "   UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"11\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "   UNION ALL\r\n"
				+ " SELECT COUNT(*) FROM prestamos WHERE substr(FechaAlta,4,2)=\"12\" AND  substr(FechaAlta,7,7)= '"+anioSeleccionado+"' AND Estado='Aprobado'\r\n"
				+ "";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			int vuelta=0;
			while (rs.next()) {
				int cant = rs.getInt(1);
				prestamosCedidos[vuelta]=cant;
				vuelta++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamosCedidos;
	}

	@Override
	public int[] prestamosSegunEstado() {
		Connection cnn = Conexion.getConexion().getSQLConexion();
		int[] prestamosSegunEstado = new int[3];
		String query = "SELECT COUNT(*) AS PRESTAMOS FROM prestamos WHERE Estado=\"Aprobado\"\r\n"
				+ " UNION ALL\r\n"
				+ "SELECT COUNT(*) AS PRESTAMOS FROM prestamos WHERE Estado=\"Rechazado\"\r\n"
				+ " UNION ALL\r\n"
				+ "SELECT COUNT(*) AS PRESTAMOS FROM prestamos WHERE Estado=\"Pendiente\"";
		PreparedStatement pst;
		try {

			pst = cnn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			int vuelta=0;
			while (rs.next()) {
				int cant = rs.getInt(1);
				prestamosSegunEstado[vuelta]=cant;
				vuelta++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamosSegunEstado;
	}




	

}
