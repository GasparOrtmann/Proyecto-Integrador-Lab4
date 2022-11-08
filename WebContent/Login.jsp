<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Cliente/EstilosGenerales.css" rel="stylesheet" type="text/css">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/2fcd49ae61.js" crossorigin="anonymous"></script>
<title>Inicio Sesion</title>
</head>
<body>

<div class="vh-100">
  <div class="container py-5 h-100" style="background-color:#f4bd48">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6 mb-2">
        <img src="inicio.jpg"
          class="img-fluid" alt="Phone image" style="border-radius:10px;">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 ">
        <form method="get" action="/TPINT_GRUPO_6_LAB4/ServeletClientes">
         <img src="login.png" style="heigh:250px;width:150px;margin-left:35%;margin-bottom:10px;"
          class="img-fluid" alt="Phone image">
          <div class="form-outline mb-2">
            <input type="text" name="txtUsuario" placeholder="ingrese su usuario" class="form-control form-control-lg" />
          </div>
          <div class="form-outline mb-2">
            <input type="password" name="txtContrasenia" placeholder="********"  class="form-control form-control-lg" />
          </div>

          <div class="d-flex justify-content-around align-items-center mb-2">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
              <label class="form-check-label" for="form1Example3"> Recordame </label>
            </div>
            <a href="#!">¿Olvidaste tu contraseña?</a>
          </div>
          <input type="submit" class="btn btn-secondary" name="btnIniciarSesion" style="margin-left:40%;margin-top:5px;value="INICIAR SESION"></input>
        </form>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>