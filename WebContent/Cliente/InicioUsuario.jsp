<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio Usuario</title>
<link href="./normalize.css" rel="stylesheet" type="text/css">
<link href="./EstilosGenerales.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar">
		<img src="../logo.png" alt="logo" style="height: 50px" />
		<div>
			<button class="buttonLogout">LOGOUT</button>
			<button class="buttonAdmin">ADMIN</button>
		</div>
	</div>
	<div class="containerContent">
		<div class="navbarLateral">
			<div class="containerData">
				<button>L</button>
				<label>Lautaro Roa</label>
			</div>
			<button class="buttonMenu">INICIO</button>
			<button class="buttonMenu">PRESTAMO</button>
			<button class="buttonMenu">TRANSFERENCIA</button>
		</div>
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
</body>
</html>