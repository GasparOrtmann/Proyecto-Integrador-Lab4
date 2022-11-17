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


@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletPrestamos() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession miSession = request.getSession(); 
		
		if(request.getParameter("TraerCuentasUsuario")!=null) {
			
			//HttpSession miSession = request.getSession(); 
			
			if(miSession.getAttribute("usuarioIngresado")!=null) {
			
				NegocioCuentas ncta = new NegocioCuentas();
				Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
				List<Cuenta> lstCuentasUsuario= ncta.traerCuentasUsuario(usuarioLogueado.getIdUsuario());
				miSession.setAttribute("lstCuentasUsuario", lstCuentasUsuario);
				RequestDispatcher rd=request.getRequestDispatcher("/Cliente/SolicitarPrestamo.jsp");  
				rd.forward(request, response);
			}
			
		}
		
		if(request.getParameter("btnSimularPrestamo")!=null) {
			
			String montoSolicitado = request.getParameter("txtMontoPrestamo");
			String cantCuotas = request.getParameter("ddlCuotas");
			String idCuentaAcreditacion = request.getParameter("ddlCuentas");
			miSession.setAttribute("montoSolicitado", montoSolicitado);
			miSession.setAttribute("cantCuotas", cantCuotas);
			miSession.setAttribute("idCuentaAcreditacion", idCuentaAcreditacion);
			
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/SolicitarPrestamo.jsp");  
			rd.forward(request, response);
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
