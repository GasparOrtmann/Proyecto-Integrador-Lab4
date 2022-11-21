<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Entidades.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>
<title></title>
</head>

<body>

<div class="container-fluid">

	<div class="navbarSuperior">
		<img src="/TPINT_GRUPO_6_LAB4/Logo_Banco_Credicoop.svg"alt="logo" style="height: 90px ; width:200px; margin-left:35px;" />
		<button type="submit" class="btn btn-secondary " style="margin-left:75%;">
			<i class="fa-solid fa-arrow-right-from-bracket"></i> Cerrar Sesión
		</button>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	
	<div class="row">
		<div class="col-3">
					<div class="navbarLateral">
				<div class="flex-shrink-0 p-3"style="width: 280px; background-color: #B4B1C4; height: 100rem">
					<%  HttpSession miSession=request.getSession(); 
						if(miSession.getAttribute("usuarioIngresado")!=null){
							Usuario usuarioLogin = (Usuario)miSession.getAttribute("usuarioIngresado");
							String nombre = usuarioLogin.getNombre().toUpperCase();
							String apellido = usuarioLogin.getApellido().toUpperCase();
							char inicialNombre = nombre.charAt(0);
							char inicialApellido = apellido.charAt(0);	
					%>
					<form method="get" action="Cliente/Cuenta_Cliente.jsp">
					<input type="submit" class="btnUser" style="color:white" value="<%=inicialNombre+" "+inicialApellido%>"></input>
					<br> <br> <a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
						<span class="fs-5 fw-semibold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=usuarioLogin.getNombre()+" "+usuarioLogin.getApellido()%></span>
					</a>
					<% } %>
					</form>
					<ul class="list-unstyled ps-0">
							<li class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed"><a href="/TPINT_GRUPO_6_LAB4/ServeletClientes?TraerCuentasUsuario=1" style="color: white;text-decoration: none;">INICIO</a></li>
						<li class="border-top my-3"></li>
						<li class="mb-1">
							<button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">PRESTAMOS</button>
							<div class="collapse" id="orders-collapse" style="">
								<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
									<li><a href="Cliente/MisPrestamos" class="link-dark d-inline-flex text-decoration-none rounded">Mis Prestamos</a></li>
									<li><a href="/TPINT_GRUPO_6_LAB4/ServletPrestamos?TraerCuentasUsuario=1"class="link-dark d-inline-flex text-decoration-none rounded">Solicitar Prestamo</a></li>
								</ul>
							</div>
						</li>
						<li class="border-top my-3"></li>
						<li class="mb-1">
							<button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">TRANSFERENCIAS</button>
							<div class="collapse" id="account-collapse" style="">
								<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
									<li><a href="/TPINT_GRUPO_6_LAB4/ServletMovimientos?TraerCuentasUsuario=1"
										class="link-dark d-inline-flex text-decoration-none rounded">A
											Cuenta Propia</a></li>
									<li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">A Otras Cuentas</a></li>
								</ul>
							</div>
						</li>
						<li class="border-top my-3"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>


	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>