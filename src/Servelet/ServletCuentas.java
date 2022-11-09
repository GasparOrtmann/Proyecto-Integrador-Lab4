package Servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Cuenta;
import Entidades.Usuario;
import Negocio.NegocioClientes;
import Negocio.NegocioCuentas;
import iNegocio.iNegocioCuentas;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCuentas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iNegocioCuentas negCu = new NegocioCuentas();
		
		if(request.getParameter("TraerListadoCuentas")!=null) {
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iNegocioCuentas negCu = new NegocioCuentas();
		
		if(request.getParameter("btnAceptar")!=null) {			
			int IdC = negCu.traerProxId();
			int IdU = Integer.parseInt(request.getParameter("txtCodUsuario"));
			int IdTC = Integer.parseInt(request.getParameter("ddlTipoCuenta"));
			String CBU = request.getParameter("txtCBU");
			float saldo = Float.parseFloat(request.getParameter("txtSaldo"));
			String fechaAlta =  request.getParameter("inputFecha");
			boolean estado = false;
			if(request.getParameter("chkEstado") != null) {
				estado = true;
			}else {
				estado = false;
			}
			Cuenta c = new Cuenta(IdC, IdU, IdTC, CBU, saldo, fechaAlta, estado);		
			negCu.agregarCuenta(c);
			
			Integer idActual = negCu.traerProxId();
			request.setAttribute("idActual", idActual);
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);	
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			negCu.eliminarCuenta(request.getParameter("getIdCuenta"));
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
	}

}
