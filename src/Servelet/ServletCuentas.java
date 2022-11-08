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
import Negocio.NegocioCuentas;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCuentas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("TraerListadoCuentas")!=null) {
			NegocioCuentas negCu = new NegocioCuentas();
			List<Cuenta> lista=negCu.traerLista();
			request.setAttribute("listaCuentas", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Cuentas_Admin.jsp");  
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
