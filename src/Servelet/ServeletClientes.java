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
import Entidades.Telefono;
import Entidades.Usuario;
import Negocio.NegocioClientes;
import Negocio.NegocioCuentas;
import Negocio.NegocioLocalidad;
import Negocio.NegocioMovimientos;
import Negocio.NegocioNacionalidad;
import Negocio.NegocioTelefono;
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
		
		HttpSession miSession = request.getSession(); 
		
		if(request.getParameter("TraerCuentasUsuario")!=null) {
			
			//HttpSession miSession = request.getSession(); 
			
			if(miSession.getAttribute("usuarioIngresado")!=null) {
			
				NegocioCuentas ncta = new NegocioCuentas();
				NegocioMovimientos negMo = new NegocioMovimientos();
				Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
				List<Cuenta> lstCuentasUsuario= ncta.traerCuentasUsuario(usuarioLogueado.getIdUsuario());
				miSession.setAttribute("lstCuentasUsuario", lstCuentasUsuario);
				int idUsuario = usuarioLogueado.getIdUsuario();
				List<Cuenta> lista = ncta.traerCuentasUsuario(idUsuario);
				request.setAttribute("listaCuentas", lista);
				List<Movimiento> listaHistorial = negMo.traerHistorial();
				request.setAttribute("listaHistorial", listaHistorial);
				RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
				rd.forward(request, response);
			}
			
		}
		
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
			
			iNegocioMovimientos negMo = new NegocioMovimientos();
			
			HttpSession miSession= request.getSession(true);
			miSession.setAttribute("usuarioIngresado", usuarioIngresado);
			miSession.setAttribute("tipoDeUsuario", usuarioIngresado.isEsAdmin());
			
			if(usuarioIngresado.getIdUsuario()!= 0) {
				
				if(usuarioIngresado.isEsAdmin()) {					
					RequestDispatcher rd=request.getRequestDispatcher("/Admin/Informe_Admin.jsp");  
					rd.forward(request, response);	
				} 
				if(!usuarioIngresado.isEsAdmin()){
					List<Cuenta> lista = negCu.traerCuentasUsuario(idUsuario);
					request.setAttribute("listaCuentas", lista);
					List<Movimiento> listaHistorial = negMo.traerHistorial();
					request.setAttribute("listaHistorial", listaHistorial);
					RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
					rd.forward(request, response);	
				} 
				
			}else {
				usuarioIngresado.setIdUsuario(0);
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
				rd.forward(request, response);	
				}
		}
	
		if(request.getParameter("btnVerHistorial")!=null) {
			iNegocioMovimientos negMo = new NegocioMovimientos();
			int idCuenta = Integer.parseInt(request.getParameter("btnVerHistorial"));
			List<Movimiento> lista = negMo.traerLista(idCuenta);
			request.setAttribute("listaMovimientos", lista);
			List<Movimiento> listaHistorial = negMo.traerHistorial();
			request.setAttribute("listaHistorial", listaHistorial);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
			rd.forward(request, response);	
			System.out.println(idCuenta);
		}
	
		if(request.getParameter("btnVolver")!=null) {
			HttpSession miSession= request.getSession(true);
			Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
			miSession.setAttribute("usuarioIngresado", usuarioLogueado);
			NegocioCuentas ncta = new NegocioCuentas();
			NegocioMovimientos negMo = new NegocioMovimientos();
			
			List<Cuenta> lstCuentasUsuario= ncta.traerCuentasUsuario(usuarioLogueado.getIdUsuario());
			miSession.setAttribute("lstCuentasUsuario", lstCuentasUsuario);
			int idUsuario = usuarioLogueado.getIdUsuario();
			List<Cuenta> lista = ncta.traerCuentasUsuario(idUsuario);
			request.setAttribute("listaCuentas", lista);
			List<Movimiento> listaHistorial = negMo.traerHistorial();
			request.setAttribute("listaHistorial", listaHistorial);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/InicioUsuario.jsp");  
			rd.forward(request, response);
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
			NegocioTelefono negTel = new NegocioTelefono();
			int proximoId = negCli.traerProxId();
			Usuario traerCliente = negCli.traerCliente(request.getParameter("idModificar"));
			Telefono traerTelefono = negTel.traerTelefonoCliente(traerCliente.getIdUsuario());
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
			request.setAttribute("telefonoEditar", traerTelefono);
			request.setAttribute("listaLocalidades", listaLoc);
			request.setAttribute("clienteEditar", traerCliente);
			request.setAttribute("proximoId", proximoId);
			RequestDispatcher rd =  request.getRequestDispatcher("/Admin/ABML_Clientes_Admin.jsp");  
			rd.forward(request, response);
		}
		if(request.getParameter("btnEditarUsuario")!=null) {
			NegocioClientes negCli = new NegocioClientes();
			NegocioTelefono negTel = new NegocioTelefono();
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
			
			Telefono telefono = new Telefono();
			telefono.setIdUsuario(id);
			telefono.setNroTelefono(request.getParameter("txtTelefono"));
			telefono.setEstado(true);
			Usuario u = new Usuario(id,admin,cuil,dni,fechaNac,usuario,contrasenia,nombre,apellido,sexo,new Localidad(localidad),calle,altura,new Nacionalidad(nacionalidad),email, cantCuentas,estado);		
			
			if(request.getParameter("txtContrasenia").equals(request.getParameter("txtRepetirContrasenia"))) {
				int filasAfectadas = negCli.modificar(u);
				int filasAfectadasTelefono = negTel.modificar(telefono);
				request.setAttribute("filasAfectadasEditar", filasAfectadas);
			}else {
				request.setAttribute("errorContrasenia", 1);
			}
			
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
			NegocioTelefono negTel = new NegocioTelefono();
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
			
			Telefono telefono = new Telefono();
			telefono.setEstado(true);
			telefono.setIdUsuario(id);
			telefono.setNroTelefono(request.getParameter("txtTelefono"));
			Usuario u = new Usuario(id,admin,cuil,dni,fechaNac,usuario,contrasenia,nombre,apellido,sexo,new Localidad(localidad),calle,altura,new Nacionalidad(nacionalidad),email, cantCuentas,estado);		
			
			int proximoId = negCli.traerProxId();
			if(request.getParameter("txtContrasenia").equals(request.getParameter("txtRepetirContrasenia"))) {
				int filasAfectadas = negCli.agregar(u);
				int filasAfectadasTelefono = negTel.modificar(telefono);
				request.setAttribute("filasAfectadasCrear", filasAfectadas);
			}else {
				request.setAttribute("errorContrasenia", 1);
			}
			
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
