<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet"
	type="text/css">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js"
	crossorigin="anonymous"></script>
<title>Dashboard</title>
</head>
<body>

	<%
	int cantClientes = 0;
	int cantPrestamos = 0;
	int cantTransacciones = 0;
	float montoPrestamo = 0;
	float gananciaPorInteres = 0;
	int[] prestamosCedidos = new int[12];

	if (request.getAttribute("cantClientes") != null) {
		cantClientes = (int) request.getAttribute("cantClientes");
	}
	if (request.getAttribute("cantPrestamos") != null) {
		cantPrestamos = (int) request.getAttribute("cantPrestamos");
	}
	if (request.getAttribute("cantTransacciones") != null) {
		cantTransacciones = (int) request.getAttribute("cantTransacciones");
	}
	if (request.getAttribute("montoPrestado") != null) {
		montoPrestamo = (float) request.getAttribute("montoPrestado");
	}
	if (request.getAttribute("gananciaPorInteres") != null) {
		gananciaPorInteres = (float) request.getAttribute("gananciaPorInteres");
	}
	if (request.getAttribute("prestamosCedidos") != null) {
		prestamosCedidos = (int[]) request.getAttribute("prestamosCedidos");
	}
	%>

	<%@include file="MasterPageAdmin.jsp"%>

	<div style="position: absolute; top: 150px; left: 400px;">
		<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletDashboard">
			<div align="center"
				style="background-color: white; height: 250px; width: 25%; float: left">
				<div class="card" style="width: 18rem; margin-bottom: 10px;">
					<div class="card-body">
						<h5 class="card-title">CLIENTES</h5>
						<h6 class="card-subtitle mb-2 text-muted">Cantidad de
							clientes registrados</h6>
						<p class="card-text text-center"><%=cantClientes%></p>
					</div>
				</div>
				<div class="card" style="width: 18rem; margin-bottom: 10px;">
					<div class="card-body">
						<h5 class="card-title">TRANSACCIONES</h5>
						<h6 class="card-subtitle mb-2 text-muted">Cantidad de
							transacciones registradas</h6>
						<p class="card-text text-center"><%=cantTransacciones%></p>
					</div>
				</div>
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">PRESTAMOS</h5>
						<h6 class="card-subtitle mb-2 text-muted">Cantidad de
							prestamos registrados</h6>
						<p class="card-text text-center"><%=cantPrestamos%></p>
					</div>
				</div>
			</div>
		</form>

		<div align="center"
			style="background-color: white; height: 250px; width: 40%; float: left; margin-left: 50px;">
			<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletDashboard">
				<label class="fs-3">Montos de Ingreso y Egreso</label><br> <br>
				<br> Desde: <input type="date" name="txtFechaInicio" required>
				Hasta: <input type="date" name="txtFechaFin" required><br></br>
				<br>

				<table class="table text-center table-hover">
					<thead class="table-dark">
						<tr>
							<th>Ganancia por interes</th>
							<th>Monto prestado</th>
						</tr>
					</thead>
					<tbody class="table-striped">
						<tr>
							<td>$<%=gananciaPorInteres%></td>
							<td>$<%=montoPrestamo%></td>
						</tr>
					</tbody>
				</table>
				<input class="btn btn-primary" type="submit" name="btnCalcular"
					value="Calcular">
			</form>
		</div>

		<div align="center"
			style="background-color: white; height: 250px; width: 60%; float: left; margin-left: 50px; margin-top: 160px;">
			<form method="POST" action="/TPINT_GRUPO_6_LAB4/ServeletDashboard">
				<label class="fs-3">Prestamos cedidos segun Mes/Año</label>
				<br>
				<input class="form-control mt-2" type="number" name="txtAnioPrestamo" onkeypress="return (event.charCode == 8 || event.charCode == 0 || event.charCode == 13) ? null : event.charCode >= 48 && event.charCode <= 57" placeholder="Inserte el Año" require>
				<input class="btn btn-primary my-2" type="submit" name="btnCalcularPrestamo" value="Calcular">
				<canvas id="myChart" width="400" height="200"></canvas>
			</form>
		</div>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"
			integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA=="
			crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script>
			const ctx = document.getElementById('myChart').getContext('2d');
			const myChart = new Chart(ctx, {
				type : 'bar',
				data : {
					labels : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo',
							'Junio', 'Julio', 'Agosto', 'Septiembre',
							'Octubre', 'Noviembre', 'Diciembre' ],
					datasets : [ {
						label : 'Dinero prestado por el banco segun cada mes',
						data : [ <%= prestamosCedidos[0]%>, 
							    <%= prestamosCedidos[1]%>, 
							    <%= prestamosCedidos[2]%>,
							    <%= prestamosCedidos[3]%>,
							    <%= prestamosCedidos[4]%>, 
								<%= prestamosCedidos[5]%>,
								<%= prestamosCedidos[6]%>,
								<%= prestamosCedidos[7]%>,
							 	<%= prestamosCedidos[8]%>,
								<%= prestamosCedidos[9]%>,
								<%= prestamosCedidos[10]%>,
								<%= prestamosCedidos[11]%>],
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					scales : {
						y : {
							beginAtZero : true
						}
					}
				}
			});
		</script>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>