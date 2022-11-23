package Servelet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Cuenta;
import Entidades.Cuota;
import Entidades.Prestamo;
import Entidades.Usuario;
import Negocio.NegocioCuentas;
import Negocio.NegocioCuota;
import Negocio.NegocioPrestamo;
import iNegocio.iNegocioCuentas;
import iNegocio.iNegocioCuota;
import iNegocio.iNegocioPrestamo;


@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
   
    public ServletPrestamos() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession miSession = request.getSession(); 
		
		if(request.getParameter("TraerCuentasUsuario")!=null) {
			
			if(miSession.getAttribute("usuarioIngresado")!=null) {
			
				iNegocioCuentas ncta = new NegocioCuentas();
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
		
		//ADMIN
		if(request.getParameter("TraerListadoPrestamos")!=null) {
			
			iNegocioPrestamo pneg = new NegocioPrestamo();
			List<Prestamo> lstPrestamos = pneg.traerListaPrestamos();
			
			request.setAttribute("lstPrestamos", lstPrestamos);
			RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Prestamos_Admin.jsp");  
			rd.forward(request, response);
			
		}
		
		//CLIENTE
		if(request.getParameter("TraerListadoMisPrestamos")!=null) {
			
			iNegocioPrestamo pneg = new NegocioPrestamo();
			Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
			
			List<Prestamo> lstMisPrestamos = pneg.traerListaMisPrestamos(usuarioLogueado);
			List<Cuota> lstCuotas = pneg.traerListaCuotas(usuarioLogueado);
			
			
			request.setAttribute("lstMisPrestamos", lstMisPrestamos);
			request.setAttribute("lstCuotas", lstCuotas);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/MisPrestamos.jsp");  
			rd.forward(request, response);
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession miSession = request.getSession();  
		iNegocioPrestamo pneg = new NegocioPrestamo();
		iNegocioCuota ctaneg=new NegocioCuota();
		
		if(request.getParameter("btnAceptarPrestamo")!=null) {
			
			 String fechaSolicitud = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
			
			 Prestamo prestamo = new Prestamo();
			 Usuario u = (Usuario)miSession.getAttribute("usuarioIngresado");
			 
			 prestamo.setIdUsuario(u.getIdUsuario());
			 prestamo.setIdPrestamo(pneg.traerProxId());
			 prestamo.setMontoPrestamo(Float.parseFloat(miSession.getAttribute("montoSolicitado").toString()));
			 prestamo.setImporteCuotaFija(Float.parseFloat(miSession.getAttribute("cuotaFija").toString()));
			 prestamo.setCantidadCuotas( Integer.parseInt(miSession.getAttribute("cantCuotas").toString()));
			 prestamo.setIdCuenta(Integer.parseInt(miSession.getAttribute("idCuentaAcreditacion").toString()));
			 prestamo.setFechaAlta(fechaSolicitud);
			 
			 Boolean confirmacionPrestamo=false;
			 
			 if(pneg.agregarPrestamo(prestamo)) {
				 confirmacionPrestamo=true;
			 } else {
				 confirmacionPrestamo=false;
			 }
			 
			 request.setAttribute("confirmacionPrestamo",confirmacionPrestamo);
			 RequestDispatcher rd=request.getRequestDispatcher("/Cliente/SolicitarPrestamo.jsp");  
			 rd.forward(request, response);

		}
		
		if(request.getParameter("btnAutorizar")!=null && request.getParameter("getIdPrestamo")!=null) {

				 String fechaAlta= DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
				 int idPrestamo = Integer.parseInt(request.getParameter("getIdPrestamo"));
				 
				 Boolean autorizacionPrestamo=false;
				 
				 if(idPrestamo!=0) {
					 
					 if(pneg.autorizarPrestamo(idPrestamo, fechaAlta)) {  
						 
						 autorizacionPrestamo=true;
					 } else {
						 autorizacionPrestamo=false;
					 }
				 }
				 
				
				 request.setAttribute("autorizacionPrestamo",autorizacionPrestamo);
				 RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Prestamos_Admin.jsp");  
				 rd.forward(request, response);
			}
		
		
		
		if(request.getParameter("btnRechazar")!=null && request.getParameter("getIdPrestamo")!=null) {
			
			 String fechaAlta= DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
			 int idPrestamo = Integer.parseInt(request.getParameter("getIdPrestamo"));
			 
			 Boolean rechazarPrestamo=false;
			 
			 if(idPrestamo!=0) {
				 
				 if(pneg.rechazarPrestamo(idPrestamo, fechaAlta)) {  
					 
					 rechazarPrestamo=true;
				 } else {
					 rechazarPrestamo=false;
				 }
			 }

			 request.setAttribute("rechazarPrestamo",rechazarPrestamo);
			 RequestDispatcher rd=request.getRequestDispatcher("/Admin/ABML_Prestamos_Admin.jsp");  
			 rd.forward(request, response);
			
		}
		
		
		if(request.getParameter("btnPagar")!=null) {
			
			int idPrestamo= Integer.parseInt(request.getParameter("idPrestamo"));
			int nroCuota=Integer.parseInt(request.getParameter("nroCuota"));
			
			Prestamo prestamo = new Prestamo(idPrestamo);
			
			String fechaPago= DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
			Cuota cuota = new Cuota(prestamo,nroCuota);
			
			Boolean pagarCuota=false;
			
			if(ctaneg.pagarCuota(cuota, fechaPago)) {
				
				pagarCuota=true;
				Usuario usuarioLogueado = (Usuario)miSession.getAttribute("usuarioIngresado");
				
				List<Prestamo> lstMisPrestamos = pneg.traerListaMisPrestamos(usuarioLogueado);
				List<Cuota> lstCuotas = pneg.traerListaCuotas(usuarioLogueado);
				request.setAttribute("lstMisPrestamos", lstMisPrestamos);
				request.setAttribute("lstCuotas", lstCuotas);
				
				
			} if(!ctaneg.pagarCuota(cuota, fechaPago)) {
				pagarCuota=false;
			}
			request.setAttribute("pagarCuota", pagarCuota);
			RequestDispatcher rd=request.getRequestDispatcher("/Cliente/MisPrestamos.jsp");  
			rd.forward(request, response);
			
		}
		
	}

}
