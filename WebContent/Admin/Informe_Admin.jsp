<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>

<%@include file="MasterPageAdmin.jsp"%>

<div style="position: absolute;top:150px;left:400px;">

	<div align="center"
		style="background-color: white; height: 250px; width: 50%; float: left">
		<label>Montos de Ingreso y Egreso</label><br> <br> <br>
		Desde: <input type="text" placeholder="dd/mm/aaaa"
			name="txtFechaInicio"> Hasta: <input type="text"
			placeholder="dd/mm/aaaa" name="txtFechaFin"><br></br> <br>

		<table border=1>
			<tr>
				<th>Ingreso</th>
				<th>Egreso</th>
			</tr>
			<tr>
				<td>$500000</td>
				<td>$900000</td>
			</tr>
		</table>
		<br> <br> <input type="submit" name="btnCalcularIngresos"
			value="Calcular" style="width: 70px; height: 30px">
	</div>

	<div align="center"
		style="background-color: white; height: 250px; width: 25%; float: left">
		<label>Nuevos Usuarios</label><br> <br> <input type="text"
			placeholder="50" name="txtNuevosUsuarios"> <br> <br>
		<label>Transferencias generadas</label><br> <br> <input
			type="text" placeholder="50860" name="txtTransferenciasGeneradas">
	</div>
	<div align="center"
		style="background-color: white; height: 250px; width: 45%; float: left">
		<label>Calcular Ganancias por Interes</label><br> <br> <select>
			<option value="1">Enero</option>
			<option value="2">Febrero</option>
			<option value="3">Marzo</option>
			<option value="4">Abril</option>
			<option value="5">Mayo</option>
			<option value="6">Junio</option>
			<option value="7">Julio</option>
			<option value="8">Agosto</option>
			<option value="9">Septiembre</option>
			<option value="10">Octubre</option>
			<option value="11">Noviembre</option>
			<option value="12">Diciembre</option>
		</select><input type="text" placeholder="Resultado" name="txtGananciaInteres"><br>
		<br> <input type="submit" name="btnCalcularIntereses"
			value="Calcular" style="width: 70px; height: 30px"> <br>
		<br> <label>Dinero prestado por el banco segun el mes:</label> <br>
		<input type="text" placeholder="Digite un año">
		 <input type="submit" name="btnCalcularIntereses" value="Calcular"
			style="width: 70px; height: 30px">
		<canvas id="myChart" width="400" height="200"></canvas>
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
						'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre',
						'Noviembre', 'Diciembre' ],
				datasets : [ {
					label : 'Dinero prestado por el banco segun cada mes',
					data : [ 12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3 ],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
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
</body>
</html>