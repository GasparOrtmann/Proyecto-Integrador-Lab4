package Servelet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Usuario;
import Negocio.IUsuarioNegocio;
import Negocio.NegocioClientes;
import Negocio.NegocioLocalidad;
import Negocio.NegocioNacionalidad;
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
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
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
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			Usuario traerCliente = negCli.traerCliente(request.getParameter("codUsuarioCambios"));
			List<Usuario> lista=negCli.traerLista();
			request.setAttribute("listaClientes", lista);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("clienteEditar", traerCliente);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditarUsuario")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			boolean estadoEsAdmin = false;
			boolean estadoEstado = false;
			if(request.getParameter("txtAdmin")!=null) {
				estadoEsAdmin=true;
			}else{
				estadoEsAdmin=false;
			}
			if(request.getParameter("txtEstado")!=null) {
				estadoEstado=true;
			}else{
				estadoEstado=false;
			}
			
			int id = Integer.valueOf(request.getParameter("txtId"));
			String dni = request.getParameter("txtDni");
			String cuil = request.getParameter("txtCuil");
			String usuario = request.getParameter("txtUsuario");
			String contrasenia = request.getParameter("txtContrasenia");
			String nombre = request.getParameter("txtNombre");
			String apellido = request.getParameter("txtApellido");
			String sexo = request.getParameter("txtSexo");
			String fechaNac = request.getParameter("txtFechaNac");
			int nacionalidad = Integer.valueOf(request.getParameter("txtNacionalidad"));
			int localidad = Integer.valueOf(request.getParameter("txtLocalidad"));
			String email = request.getParameter("txtEmail");
			boolean estado = estadoEstado;
			boolean admin = estadoEsAdmin;
			String calle = request.getParameter("txtCalle");
			int altura = Integer.valueOf(request.getParameter("txtAltura"));
			int cantCuentas = Integer.valueOf(request.getParameter("txtCantCuentas"));
			
			Usuario u = new Usuario(id,admin,cuil,dni,fechaNac,usuario,contrasenia,nombre,apellido,sexo,new Localidad(localidad),calle,altura,new Nacionalidad(nacionalidad),email, cantCuentas,estado);		
			int filasAfectadas = negCli.modificar(u);
			request.setAttribute("idActual", filasAfectadas);
			List<Usuario> lista=negCli.traerLista();
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("listaClientes", lista);
			
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
	}

}
