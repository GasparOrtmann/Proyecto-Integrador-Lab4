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
import Entidades.Nacionalidad;
import Entidades.TipoCuenta;
import Entidades.Usuario;
import Negocio.NegocioClientes;
import Negocio.NegocioCuentas;
import Negocio.NegocioTipoCuenta;
import iNegocio.iNegocioCuentas;
import iNegocio.iNegocioTipoCuenta;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCuentas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iNegocioCuentas negCu = new NegocioCuentas();
		iNegocioTipoCuenta negTC = new NegocioTipoCuenta();
		
		if(request.getParameter("TraerListadoCuentas")!=null) {
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			List<TipoCuenta> listaTiposCuenta = negTC.traerTiposCuentas();
			request.setAttribute("listaTiposCuentas", listaTiposCuenta);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iNegocioCuentas negCu = new NegocioCuentas();
		iNegocioTipoCuenta negTC = new NegocioTipoCuenta();
		
		if(request.getParameter("btnAgregar")!=null) {			
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
			Cuenta c = new Cuenta(IdC, IdU, new TipoCuenta(IdTC), CBU, saldo, fechaAlta, estado);		
			negCu.agregarCuenta(c);
			
			Integer idActual = negCu.traerProxId();
			request.setAttribute("idActual", idActual);
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			List<TipoCuenta> listaTipos=negTC.traerTiposCuentas();
			request.setAttribute("listaTiposCuentas", listaTipos);
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
		
		if(request.getParameter("btnEditar")!=null) {
			Cuenta traerCuenta = negCu.traerCuenta(request.getParameter("getIdCuenta"));
			request.setAttribute("cuentaEditar", traerCuenta);
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			List<TipoCuenta> listaTipos=negTC.traerTiposCuentas();
			request.setAttribute("listaTiposCuentas", listaTipos);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnEditarCuenta")!=null) {
			int IdC = Integer.parseInt(request.getParameter("txtCodCuenta"));
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
			System.out.println(IdC+ IdU+ IdTC+ CBU+ saldo+ fechaAlta+ estado);
			Cuenta c = new Cuenta(IdC, IdU, new TipoCuenta(IdTC), CBU, saldo, fechaAlta, estado);		
			int filasModificadas = negCu.modificar(c);
			System.out.println("filas modificadas = "+filasModificadas);
			request.setAttribute("filasModificadasEditar", filasModificadas);
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			List<TipoCuenta> listaTipos=negTC.traerTiposCuentas();
			request.setAttribute("listaTiposCuentas", listaTipos);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
		
		// ---- FILTROS ---
		
		if(request.getParameter("Filtrar")!= null)
		{
			if(request.getParameter("txtFUsuario")!="") 
			{
				List<Cuenta> lista = negCu.FiltrarPorUsuario(request.getParameter("txtFUsuario"));
				request.setAttribute("listaCuentas", lista);
				
				RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("listaCuentas",negCu.traerLista());
				RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
				rd.forward(request, response);
			}

		}
	}

}
