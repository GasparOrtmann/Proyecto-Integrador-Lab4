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
import Entidades.Usuario;
import Negocio.NegocioCuentas;
import Negocio.NegocioMovimientos;
import iNegocio.iNegocioCuentas;
import iNegocio.iNegocioMovimientos;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientosLocales")
public class ServletMovimientosLocales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMovimientosLocales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iNegocioCuentas negCu = new NegocioCuentas();
		//iNegocioTipoCuenta negTC = new NegocioTipoCuenta();
			System.out.println("En servlet");
			if(request.getParameter("TraerCuentasUsuario")!= null) {
				HttpSession session = request.getSession();
				System.out.println("traer cuentas = 1");
				if(session.getAttribute("usuarioIngresado")!=null)
				{
					Usuario u = (Usuario)session.getAttribute("usuarioIngresado");
					List<Cuenta> lista=negCu.FiltrarPorUsuario(String.valueOf(u.getIdUsuario()), true);
					request.setAttribute("listaCuentas", lista);
					RequestDispatcher rd =  request.getRequestDispatcher("/Cliente/Transferencia_Local.jsp");  
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd =  request.getRequestDispatcher("/Cliente/Transferencia_Local.jsp");  
					rd.forward(request, response);
				}
				
		
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnTransferir")!= null)
		{
			iNegocioCuentas negCu = new NegocioCuentas();
			iNegocioMovimientos neg = new NegocioMovimientos();
			HttpSession session = request.getSession();
			
			if(session.getAttribute("usuarioIngresado")!=null)
			{
				Usuario u = (Usuario)session.getAttribute("usuarioIngresado");
				List<Cuenta> lista=negCu.FiltrarPorUsuario(String.valueOf(u.getIdUsuario()), true);
				request.setAttribute("listaCuentas", lista);
			}
			if(request.getParameter("txtMonto")!="" && request.getParameter("txtMonto").matches("[0-9 ]+")) 
			{
			String origen = request.getParameter("ddlOrigen");
			String destino = request.getParameter("ddlDestino");
			String monto = request.getParameter("txtMonto");
		
			System.out.println("o: "+origen+" d: "+destino+" $:"+monto);
			
			if(neg.generarTransferncia(origen,destino,Float.valueOf(monto))==true)
			{
				request.setAttribute("TransferenciaOk", true);
			}
			RequestDispatcher rd =  request.getRequestDispatcher("/Cliente/Transferencia_Local.jsp");  
			rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd =  request.getRequestDispatcher("/Cliente/Transferencia_Local.jsp");  
				rd.forward(request, response);
			}
		}
	}

}
