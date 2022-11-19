package Servelet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegocioClientes;
import Negocio.NegocioMovimientos;
import Negocio.NegocioPrestamo;

@WebServlet("/ServeletDashboard")
public class ServeletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServeletDashboard() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("TraerInformacionDashboard") != null) {
			NegocioClientes negCli = new NegocioClientes();
			NegocioMovimientos negMo = new NegocioMovimientos();
			NegocioPrestamo negPre = new NegocioPrestamo();

			int cantClientes = negCli.cantClientes();
			int cantTransacciones = negMo.cantTransacciones();
			int cantPrestamos = negPre.cantPrestamos();

			request.setAttribute("cantClientes", cantClientes);
			request.setAttribute("cantTransacciones", cantTransacciones);
			request.setAttribute("cantPrestamos", cantPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/Informe_Admin.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnCalcular") != null) {
			String fechaInicio=request.getParameter("txtFechaInicio");
			String fechaFin=request.getParameter("txtFechaFin");
			String fechaInicioFormateada = fechaInicio.substring(8,10)+"/"+fechaInicio.substring(5,7)+"/"+fechaInicio.substring(0,4);
			String fechaFinFormateada = fechaFin.substring(8,10)+"/"+fechaFin.substring(5,7)+"/"+fechaFin.substring(0,4);
			
			NegocioClientes negCli = new NegocioClientes();
			NegocioMovimientos negMo = new NegocioMovimientos();
			NegocioPrestamo negPre = new NegocioPrestamo();
			
			int cantClientes = negCli.cantClientes();
			int cantTransacciones = negMo.cantTransacciones();
			int cantPrestamos = negPre.cantPrestamos();
			float gananciaPorInteres = negPre.gananciaPorInteres(fechaInicioFormateada,fechaFinFormateada);
			float montoPrestado = negPre.montoPrestado(fechaInicioFormateada,fechaFinFormateada);
			
			request.setAttribute("gananciaPorInteres", gananciaPorInteres);
			request.setAttribute("montoPrestado", montoPrestado);
			request.setAttribute("cantClientes", cantClientes);
			request.setAttribute("cantTransacciones", cantTransacciones);
			request.setAttribute("cantPrestamos", cantPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/Informe_Admin.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("btnCalcularPrestamo") != null) {
			NegocioClientes negCli = new NegocioClientes();
			NegocioMovimientos negMo = new NegocioMovimientos();
			NegocioPrestamo negPre = new NegocioPrestamo();

			int cantClientes = negCli.cantClientes();
			int cantTransacciones = negMo.cantTransacciones();
			int cantPrestamos = negPre.cantPrestamos();

			request.setAttribute("cantClientes", cantClientes);
			request.setAttribute("cantTransacciones", cantTransacciones);
			request.setAttribute("cantPrestamos", cantPrestamos);
			if(request.getParameter("txtAnioPrestamo")!=""){
				int anioSeleccionado = Integer.valueOf(request.getParameter("txtAnioPrestamo"));
				int[] prestamosCedidos = negPre.prestamosCedidos(anioSeleccionado);
				request.setAttribute("prestamosCedidos", prestamosCedidos);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/Admin/Informe_Admin.jsp");
			rd.forward(request, response);
		}
	}

}