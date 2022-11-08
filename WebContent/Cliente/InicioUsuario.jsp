<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet"
	type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"rel="stylesheet"integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js"crossorigin="anonymous"></script>
<title>Inicio Usuario</title>

</head>
<body>


<%@include file="MasterPageCliente.jsp"%>

<div style="position: absolute;top:150px;left:400px; width:1400px;">
	
		<div class="containerContentData">
			<div class="containerCuentas">
				<div class="containerCuenta">
					<div class="containerCuentaPrincipal">
						<label>Cuenta N°: 123123</label> <label>$4.000</label>
					</div>
					<label>Cuenta Corriente</label>
					<div class="containerCuentaPrincipal">
						<label>CBU: 2KS91MS91LSA</label>
						<button>Ver historial</button>
					</div>
				</div>
				<div class="containerCuenta">
					<div class="containerCuentaPrincipal">
						<label>Cuenta N°: 17565</label> <label>$7.000</label>
					</div>
					<label>Cuenta Corriente</label>
					<div class="containerCuentaPrincipal">
						<label>CBU: AS59AMS92MAO</label>
						<button>Ver historial</button>
					</div>
				</div>
				<div class="containerCuenta">
					<div class="containerCuentaPrincipal">
						<label>Cuenta N°: 34522</label> <label>$15.000</label>
					</div>
					<label>Caja ahorro</label>
					<div class="containerCuentaPrincipal">
						<label>CBU: ASD19SM10SD0</label>
						<button>Ver historial</button>
					</div>
				</div>
			</div>
			<div class="containerActividad">
				<h2>Tu actividad</h2>
				<div class="containerActividadIndividual">
					<h3>TRANSFERENCIA</h3>
					<div class="containerCuentaPrincipal">
						<label>CBU: 189SM02LSKLMA9</label> <label>$3.500</label>
					</div>
				</div>
				<div class="containerActividadIndividual">
					<h3>TRANSFERENCIA</h3>
					<div class="containerCuentaPrincipal">
						<label>CBU: 189SM02LSKLMA9</label> <label>$3.500</label>
					</div>
				</div>
				<div class="containerActividadIndividual">
					<h3>TRANSFERENCIA</h3>
					<div class="containerCuentaPrincipal">
						<label>CBU: 189SM02LSKLMA9</label> <label>$3.500</label>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"crossorigin="anonymous"></script>
</body>
</html>