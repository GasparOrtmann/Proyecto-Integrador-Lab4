package Servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Usuario;
import Negocio.IUsuarioNegocio;
import Negocio.NegocioClientes;
import Negocio.UsuarioNegocio;

@WebServlet("/ServeletClientes")
public class ServeletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServeletClientes() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("TraerListadoClientes")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			List<Usuario> lista=negCli.traerLista();
			request.setAttribute("listaClientes", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	if(request.getParameter("btnIniciarSesion")!=null) {
			
			String usuario = request.getParameter("txtUsuario");
			String contrasenia  = request.getParameter("txtContrasenia");
			
			IUsuarioNegocio uneg = new UsuarioNegocio();
			Usuario usuarioIngresado = uneg.traerUsuario(usuario, contrasenia);
			
			if(usuarioIngresado.isEsAdmin()) {
				request.setAttribute("usuarioIngresado",usuarioIngresado);
				RequestDispatcher rd=request.getRequestDispatcher("/Admin/Informe_Admin.jsp");  
				rd.forward(request, response);	
			} else {
				request.setAttribute("usuarioIngresado",usuarioIngresado);
				RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
				rd.forward(request, response);	
				
			}

		}
		
		
		if(request.getParameter("btnEliminar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			negCli.eliminarCliente(request.getParameter("codUsuarioCambios"));
			List<Usuario> lista=negCli.traerLista();
			request.setAttribute("listaClientes", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			Usuario traerCliente = negCli.traerCliente(request.getParameter("codUsuarioCambios"));
			List<Usuario> lista=negCli.traerLista();
			request.setAttribute("listaClientes", lista);
			request.setAttribute("clienteEditar", traerCliente);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
	}

}
