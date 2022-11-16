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

import org.apache.tomcat.util.http.parser.Cookie;

import Entidades.Cuenta;
import Entidades.Localidad;
import Entidades.Movimiento;
import Entidades.Nacionalidad;
import Entidades.Usuario;
import Negocio.NegocioClientes;
import Negocio.NegocioCuentas;
import Negocio.NegocioLocalidad;
import Negocio.NegocioMovimientos;
import Negocio.NegocioNacionalidad;
import Negocio.UsuarioNegocio;
import iNegocio.IUsuarioNegocio;
import iNegocio.iNegocioCuentas;
import iNegocio.iNegocioMovimientos;

@WebServlet("/ServeletClientes")
public class ServeletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServeletClientes() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("TraerListadoClientes")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
			List<Usuario> lista=negCli.traerLista();
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("proximoId", proximoId);
			request.setAttribute("listaClientes", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("paginar")!=null) {
			int pagina=Integer.valueOf(request.getParameter("paginar").toString());
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("proximoId", proximoId);
			request.setAttribute("listaClientes", lista);
			request.setAttribute("proximaPagina", pagina);
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
				
			iNegocioCuentas negCu = new NegocioCuentas();
			int idUsuario = usuarioIngresado.getIdUsuario();
			
			HttpSession miSession= request.getSession(true);
			miSession.setAttribute("usuarioIngresado", usuarioIngresado);
			
			if(usuarioIngresado.getIdUsuario()!= 0) {
				
				if(usuarioIngresado.isEsAdmin()) {
					//request.setAttribute("usuarioIngresado",usuarioIngresado);
					RequestDispatcher rd=request.getRequestDispatcher("/Admin/Informe_Admin.jsp");  
					rd.forward(request, response);	
				} 
				if(!usuarioIngresado.isEsAdmin()){
					List<Cuenta> lista = negCu.traerCuentasUsuario(idUsuario);
					request.setAttribute("listaCuentas", lista);
					//request.setAttribute("usuarioIngresado",usuarioIngresado);
					RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
					rd.forward(request, response);	
				} 
				
			}else {
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
				rd.forward(request, response);	
				}
		}
	
		if(request.getParameter("btnVerHistorial")!=null) {
			iNegocioMovimientos negMo = new NegocioMovimientos();
			int idCuenta = Integer.parseInt(request.getParameter("btnVerHistorial"));
			List<Movimiento> lista = negMo.traerLista(idCuenta);
			request.setAttribute("listaMovimientos", lista);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
			rd.forward(request, response);	
			System.out.println(idCuenta);
		}
	
		if(request.getParameter("btnEliminar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
			boolean filasAfectadas= negCli.eliminarCliente(request.getParameter("idModificar"));
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			request.setAttribute("listaClientes", lista);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("filasAfectadasBorrar", filasAfectadas);
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("proximoId", proximoId);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
			Usuario traerCliente = negCli.traerCliente(request.getParameter("idModificar"));
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			request.setAttribute("listaClientes", lista);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("clienteEditar", traerCliente);
			request.setAttribute("proximoId", proximoId);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditarUsuario")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
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
			request.setAttribute("filasAfectadasEditar", filasAfectadas);
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("listaClientes", lista);
			request.setAttribute("proximoId", proximoId);
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnCrearUsuario")!=null) {
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
			int filasAfectadas = negCli.agregar(u);
			int proximoId = negCli.traerProxId();
			request.setAttribute("filasAfectadasCrear", filasAfectadas);
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("listaClientes", lista);
			request.setAttribute("proximoId", proximoId);
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("filtrar")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			int proximoId = negCli.traerProxId();
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			String textoFiltro=null;
			String filtro = null;
			try {
				textoFiltro = cookies[2].getValue();
				filtro= cookies[1].getValue();
			}catch(Exception e) {
				e.getStackTrace();
				filtro = "todos";
				textoFiltro="";
			}
			String filtroAplicar = "";
			switch(filtro) {
				case "todos":
					filtroAplicar = "";
					break;
				case "filtrarPorId":
					filtroAplicar = "WHERE IdUsuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorUsuario":
					filtroAplicar = "WHERE Usuario LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorNombre":
					filtroAplicar = "WHERE Nombre LIKE '%"+textoFiltro+"%'";
					break;
				case "filtrarPorApellido":
					filtroAplicar = "WHERE Apellido LIKE '%"+textoFiltro+"%'";
					break;
				default:
					filtroAplicar = "";
					break;
			}
			List<Usuario> lista=negCli.traerListaConFiltro(filtroAplicar);
			NegocioNacionalidad negNac = new NegocioNacionalidad();
			List<Nacionalidad> listaNac=negNac.traerLista();
			request.setAttribute("listaNacionalidades", listaNac);
			NegocioLocalidad negLoc = new NegocioLocalidad();
			List<Localidad> listaLoc=negLoc.traerLista();
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("proximoId", proximoId);
			request.setAttribute("listaClientes", lista);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
	}

}
