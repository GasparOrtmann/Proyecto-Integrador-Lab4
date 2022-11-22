package Servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Cuenta;
import Entidades.Movimiento;
import Entidades.Usuario;
import Negocio.NegocioClientes;
import Negocio.NegocioCuentas;
import Negocio.NegocioMovimientos;
import Negocio.NegocioPrestamo;
import iNegocio.iNegocioCuentas;
import iNegocio.iNegocioMovimientos;

@WebServlet("/ServeletFuncionalidades")
public class ServeletFuncionalidades extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServeletFuncionalidades() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnHome")!=null){
			iNegocioMovimientos negMo = new NegocioMovimientos();
			iNegocioCuentas negCu = new NegocioCuentas();
			NegocioClientes negCli = new NegocioClientes();
			HttpSession miSession = request.getSession(); 
			Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
			List<Cuenta> lstCuentasUsuario= negCu.traerCuentasUsuario(usuarioLogueado.getIdUsuario());
			miSession.setAttribute("lstCuentasUsuario", lstCuentasUsuario);
			int idUsuario = usuarioLogueado.getIdUsuario();
			List<Cuenta> lista = negCu.traerCuentasUsuario(idUsuario);
			request.setAttribute("listaCuentas", lista);
			List<Movimiento> listaHistorial = negMo.traerHistorial(idUsuario);
			request.setAttribute("listaHistorial", listaHistorial);
			int cantMovimientosUsuario = negMo.cantMovimientosUsuario(idUsuario);
			request.setAttribute("cantMovimientosUsuario", cantMovimientosUsuario);
			int cantCuentas = negCu.cantCuentasUsuario(idUsuario);
			request.setAttribute("cantCuentas", cantCuentas);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnCerrarSesion")!=null){
			HttpSession miSession = request.getSession(); 
			miSession.setAttribute("usuarioIngresado", null);
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnAdmin")!=null){
			NegocioClientes negCli = new NegocioClientes();
			NegocioMovimientos negMo = new NegocioMovimientos();
			NegocioPrestamo negPre = new NegocioPrestamo();

			int cantClientes = negCli.cantClientes();
			int cantTransacciones = negMo.cantTransacciones();
			int cantPrestamos = negPre.cantPrestamos();
			int[] prestamosSegunEstado = negPre.prestamosSegunEstado();
			
			request.setAttribute("prestamosSegunEstado", prestamosSegunEstado);
			request.setAttribute("cantClientes", cantClientes);
			request.setAttribute("cantTransacciones", cantTransacciones);
			request.setAttribute("cantPrestamos", cantPrestamos);
			RequestDispatcher rd = request.getRequestDispatcher("/Admin/Informe_Admin.jsp");
			rd.forward(request, response);
		}
	}

}
