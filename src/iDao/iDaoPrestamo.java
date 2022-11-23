package iDao;

import java.util.List;

import Entidades.Prestamo;
import Entidades.Usuario;

public interface iDaoPrestamo {

	Boolean agregarPrestamo(Prestamo prestamo);
	int  traerProxId();
	List<Prestamo> traerListaPrestamos();
	Boolean autorizarPrestamo(int idPrestamo, String fechaAlta);
	Boolean rechazarPrestamo(int idPrestamo, String fechaAlta);
	List<Prestamo> traerListaMisPrestamos(Usuario u);
	
	
	int cantPrestamos();
	float gananciaPorInteres(String fechaInicioFormateada,String fechaFinFormateada);
	float montoPrestado(String fechaInicioFormateada,String fechaFinFormateada);
	int[] prestamosCedidos(int anioSeleccionado);
	int[] prestamosSegunEstado();
}
