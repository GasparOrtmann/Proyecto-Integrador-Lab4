DROP SCHEMA  IF EXISTS  bdbanco;
CREATE SCHEMA bdbanco;

USE bdbanco;


CREATE TABLE Provincias(
IdProvincia int NOT NULL,
Provincia varchar(60),
CONSTRAINT PK_Provincias PRIMARY KEY (IdProvincia)
);

CREATE TABLE Localidades(
IdLocalidad int NOT NULL,
Localidad varchar(60),
IdProvincia int NOT NULL,
CONSTRAINT PK_Localidades PRIMARY KEY (IdLocalidad,IdProvincia),
CONSTRAINT FK_Localidades_Provincias FOREIGN KEY (IdProvincia) REFERENCES Provincias (IdProvincia)
);

CREATE TABLE Nacionalidades(
IdNacionalidad int NOT NULL,
Nacionalidad varchar(60),
CONSTRAINT PK_Nacionalidades PRIMARY KEY (IdNacionalidad)
);

CREATE TABLE Tipos_Movimiento(
IdTipoMovimiento int NOT NULL,
TipoMovimiento varchar(60),
CONSTRAINT PK_Tipos_Movimiento PRIMARY KEY (IdTipoMovimiento)
);

CREATE TABLE Tipos_Cuenta(
IdTipoCuenta int NOT NULL,
TipoCuenta varchar(60),
CONSTRAINT PK_Tipos_Cuenta PRIMARY KEY (IdTipoCuenta)
);

CREATE TABLE Usuarios(
IdUsuario int NOT NULL,
EsAdmin bit,
Usuario varchar(60),
Contrasenia varchar(8),
Nombre varchar(60),
Apellido varchar(60),
Sexo   varchar(30),
FechaNacimiento varchar(10),
Calle varchar(60),
Altura int,
Email varchar(60),
NroCuil varchar(12),
NroDni varchar(8),
Estado bit,
CantidadCuentas int,
IdNacionalidad int NOT NULL,
IdLocalidad int NOT NULL,
CONSTRAINT PK_Usuarios PRIMARY KEY (IdUsuario),
CONSTRAINT FK_Usuarios_Nacionalidades FOREIGN KEY (IdNacionalidad) REFERENCES Nacionalidades (IdNacionalidad),
CONSTRAINT FK_Usuarios_Localidades FOREIGN KEY (IdLocalidad) REFERENCES Localidades (IdLocalidad)
);

CREATE TABLE Telefonos(
NroTelefono varchar(18) NOT NULL,
IdUsuario int NOT NULL,
Estado bit,
CONSTRAINT PK_Telefonos PRIMARY KEY (NroTelefono,IdUsuario),
CONSTRAINT FK_Telefonos_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario)
);

CREATE TABLE Cuentas(
IdCuenta int NOT NULL,
IdUsuario int NOT NULL,
IdTipoCuenta int NOT NULL,
CBU varchar(20),
Saldo float,
FechaAlta varchar(10),
Estado bit,
CONSTRAINT PK_Cuentas PRIMARY KEY (IdCuenta),
CONSTRAINT FK_Cuentas_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario),
CONSTRAINT FK_Cuentas_Tipos_Cuenta FOREIGN KEY (IdTipoCuenta) REFERENCES Tipos_Cuenta (IdTipoCuenta)
);

CREATE TABLE Prestamos(
IdPrestamo int NOT NULL,
IdUsuario int NOT NULL,
MontoPrestamo float,
MontoTotalAdeudado float,
ImporteCuotaFija float,
CantidadCuotas int,
CuotasAdeudadas int,
CuotasPagas int,
IdCuenta int NOT NULL,
FechaAlta varchar(10),
Estado varchar(30),
CONSTRAINT PK_Prestamos PRIMARY KEY (IdPrestamo),
CONSTRAINT FK_Prestamos_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios (IdUsuario),
CONSTRAINT FK_Prestamos_Cuentas FOREIGN KEY (IdCuenta) REFERENCES Cuentas (IdCuenta)
);

CREATE TABLE Cuotas(
NroCuota int NOT NULL,
IdPrestamo int NOT NULL,
FechaPago varchar(10),
FechaVto varchar(10) NOT NULL,
CONSTRAINT PK_Cuotas PRIMARY KEY (NroCuota,IdPrestamo),
CONSTRAINT FK_Cuotas_Prestamos FOREIGN KEY (IdPrestamo) REFERENCES Prestamos (IdPrestamo) ON DELETE CASCADE
);

CREATE TABLE Movimientos(
IdMovimiento int NOT NULL,
IdTipoMovimiento int NOT NULL,
IdCuenta int NOT NULL,
Fecha varchar(10),
Importe float,
Detalle varchar(60),
CONSTRAINT PK_Movimientos PRIMARY KEY (IdMovimiento),
CONSTRAINT FK_Movimientos_Tipos_Movimiento FOREIGN KEY (IdTipoMovimiento) REFERENCES Tipos_Movimiento (IdTipoMovimiento),
CONSTRAINT FK_Movimientos_Cuentas FOREIGN KEY (IdCuenta) REFERENCES Cuentas (IdCuenta)
);

USE `bdbanco`;
DROP procedure IF EXISTS `SP_agregarcuenta`;

DELIMITER $$
USE `bdbanco`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_agregarcuenta`(IN sIdC int, IN sIdU int, IN sIdT int, IN sCBU varchar(20), IN sSaldo float, IN sFechaAlta varchar(10), IN sEstado bit)
BEGIN 
	INSERT INTO Cuentas (IdCuenta,IdUsuario,IdTipoCuenta,CBU,Saldo,FechaAlta,Estado) values (sIdc,sIdU,sIdT,sCBU,sSaldo,sFechaAlta,sEstado);
    END$$

DELIMITER ;

 USE `bdbanco`;
	DROP procedure IF EXISTS `SP_agregarPrestamo`;
	
	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_agregarPrestamo` (IN pId int, IN pIdUsu int, IN monto float,IN cuotaValor float, IN cantCuota int,IN idCuenta int, IN fechaAlta varchar(10))
	BEGIN
	INSERT INTO Prestamos (IdPrestamo,IdUsuario,MontoPrestamo,MontoTotalAdeudado,ImporteCuotaFija,CantidadCuotas,CuotasAdeudadas,CuotasPagas,IdCuenta,FechaAlta,Estado) 
					values (pId,pIdUsu,monto,null,cuotaValor,cantCuota,null,null,idCuenta,fechaAlta,'Pendiente');
	END$$
	
	DELIMITER ;

USE `bdbanco`;
	DROP procedure IF EXISTS `SP_agregarMovimiento`;

	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_agregarMovimiento` (IN sIdM int, IN sTM int, IN sIdC int, IN sFecha varchar(10), IN sImporte float, IN sDetalle varchar(40))
	BEGIN
		INSERT INTO movimientos (IdMovimiento, IdTipoMovimiento, IdCuenta, Fecha, Importe, Detalle) values (sIdM,sTM,sIdC,sFecha,sImporte,sDetalle);
	END$$

	DELIMITER ;
    
USE `bdbanco`;
	DROP procedure IF EXISTS `SP_autorizarPrestamo`;

	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_autorizarPrestamo` (IN _idPrestamo int, IN _fechaAlta varchar(10))

	BEGIN

		DECLARE  _cuotaMes int DEFAULT 0;
        DECLARE _fchaVtoStringMes varchar(10) DEFAULT _fechaAlta;
		DECLARE _saldo float; 
        DECLARE _montoPrestamo float;
        DECLARE _totalSaldo float;
        DECLARE _idMovimiento int DEFAULT 1;
        DECLARE _idCuenta int;
        
		UPDATE prestamos
		SET MontoTotalAdeudado = (((((CantidadCuotas/12)*85)*MontoPrestamo)/100)+MontoPrestamo),CuotasAdeudadas = CantidadCuotas,CuotasPagas= 0, FechaAlta=_fechaAlta , Estado= 'Aprobado'
		WHERE IdPrestamo = _idPrestamo;

		WHILE _cuotaMes < (SELECT CantidadCuotas FROM Prestamos WHERE IdPrestamo = _idPrestamo) DO  
			SET _fchaVtoStringMes = (DATE_FORMAT(DATE_ADD(STR_TO_DATE(_fchaVtoStringMes, '%d/%m/%Y'), INTERVAL 1 MONTH) , '%d/%m/%Y')) ;
			SET _cuotaMes=(_cuotaMes+1);
          INSERT INTO cuotas (NroCuota,IdPrestamo,FechaPago,FechaVto) VALUES (_cuotaMes,_idPrestamo,null,_fchaVtoStringMes);
        END WHILE;
        
		SET _saldo = (SELECT Saldo FROM cuentas WHERE IdCuenta = (SELECT IdCuenta FROM prestamos WHERE IdPrestamo=_idPrestamo));
        SET _montoPrestamo=  (SELECT MontoPrestamo FROM prestamos WHERE IdPrestamo=_idPrestamo);
        SET _totalSaldo= _saldo+_montoPrestamo;
        UPDATE cuentas SET Saldo=_totalSaldo WHERE IdCuenta = (SELECT IdCuenta FROM prestamos WHERE IdPrestamo=_idPrestamo);
        
        SET _idCuenta = (SELECT IdCuenta FROM prestamos WHERE IdPrestamo=_idPrestamo);
        SET _idMovimiento = (SELECT MAX(IdMovimiento) FROM movimientos);
        
        IF _idMovimiento IS NULL THEN  INSERT INTO movimientos (IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,Importe,Detalle) VALUES (1,2,_idCuenta,_fechaAlta,_montoPrestamo,'(+) Acreditación de prestamo');
        ELSE  INSERT INTO movimientos (IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,Importe,Detalle) VALUES (_idMovimiento,2,_idCuenta,_fechaAlta,_montoPrestamo,'(+) Acreditación de prestamo');
        END IF;
       
        
	END
	$$
	DELIMITER ;
    
USE `bdbanco`;
	DROP procedure IF EXISTS `SP_rechazarPrestamo`;

	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_rechazarPrestamo` (IN _idPrestamo int, IN _fechaAlta varchar(10))

	BEGIN
        
		UPDATE prestamos
		SET Estado= 'Rechazado'
		WHERE IdPrestamo = _idPrestamo;

	END
	$$
	DELIMITER ;

USE `bdbanco`;
	DROP procedure IF EXISTS `SP_pagarCuota`;

	DELIMITER $$
	USE `bdbanco`$$
	CREATE PROCEDURE `SP_pagarCuota` (IN _idPrestamo int, IN _nroCuota int, IN _fechaPago varchar(10))

	BEGIN
        
        DECLARE valorCuota float;
        DECLARE saldoCuenta float;
		DECLARE _idMovimiento int;

        DECLARE _idCuenta int;
        
		SET saldoCuenta = (SELECT Saldo FROM prestamos p JOIN cuentas c ON p.IdCuenta=c.IdCuenta WHERE p.IdPrestamo = _idPrestamo);
        SET valorCuota = (SELECT ImporteCuotaFija FROM prestamos WHERE IdPrestamo = _idPrestamo);
        SET _idCuenta = (SELECT IdCuenta FROM prestamos WHERE IdPrestamo = _idPrestamo );
        
        IF(valorCuota<=saldoCuenta)THEN
			
            UPDATE cuotas
			SET FechaPago= _fechaPago
			WHERE IdPrestamo = _idPrestamo AND NroCuota=_nroCuota;

			SET _idMovimiento = (SELECT MAX(IdMovimiento) FROM movimientos)+1;
            
			IF _idMovimiento IS NULL 
            THEN  INSERT INTO movimientos (IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,Importe,Detalle) VALUES (1,3,_idCuenta,_fechaPago,valorCuota,'(-) Pago de cuota');
			ELSE  INSERT INTO movimientos (IdMovimiento,IdTipoMovimiento,IdCuenta,Fecha,Importe,Detalle) VALUES (_idMovimiento,3,_idCuenta,_fechaPago,valorCuota,'(-) Pago de cuota');
			END IF;
            
            UPDATE cuentas
            SET Saldo = Saldo-valorCuota
            WHERE IdCuenta=_idCuenta;
            
            UPDATE prestamos
            SET CuotasAdeudadas = CuotasAdeudadas-1, CuotasPagas = CuotasPagas+1
            WHERE IdPrestamo=_idPrestamo;
             

		END IF;

	END
	$$
	DELIMITER ;



DELIMITER $$
	CREATE TRIGGER tr_crearUsuario BEFORE INSERT ON usuarios
	FOR EACH ROW
	BEGIN
		IF (exists(SELECT 1 FROM usuarios WHERE Usuario=NEW.Usuario) && exists(SELECT 1 FROM usuarios WHERE NroDni=NEW.NroDni) && exists(SELECT 1 FROM usuarios WHERE NroCuil=NEW.NroCuil))THEN
			INSERT INTO usuarios (IdUsuario,EsAdmin,Usuario,Contrasenia,Nombre,Apellido,Sexo,FechaNacimiento,Calle,Altura,Email,NroCuil,NroDni,Estado,CantidadCuentas,IdNacionalidad,IdLocalidad)
			VALUES (NEW.IdUsuario,NEW.EsAdmin,NEW.Usuario,NEW.Contrasenia,NEW.Nombre,NEW.Apellido,NEW.Sexo,NEW.FechaNacimiento,NEW.Calle,NEW.Altura,NEW.Email,NEW.NroCuil,NEW.NroDni,NEW.Estado,NEW.CantidadCuentas,NEW.IdNacionalidad,NEW.IdLocalidad);
		END IF;
	END$$;
DELIMITER ;

DELIMITER $$
CREATE TRIGGER tr_crearCuenta BEFORE INSERT ON cuentas
	FOR EACH ROW
	BEGIN
		IF (exists(SELECT 1 FROM cuentas WHERE IdCuenta=NEW.IdCuenta) && exists(SELECT 1 FROM cuentas WHERE IdUsuario=NEW.IdUsuario) && exists(SELECT 1 FROM cuentas WHERE CBU=NEW.CBU))THEN
			INSERT INTO cuentas (IdCuenta,IdUsuario,IdTipoCuenta,CBU,Saldo,FechaAlta,Estado)
			VALUES (NEW.IdCuenta,NEW.IdUsuario,NEW.IdTipoCuenta,NEW.CBU,NEW.Saldo,NEW.FechaAlta,NEW.Estado);
	END IF;
END$$;
DELIMITER ;

INSERT INTO nacionalidades (IdNacionalidad, Nacionalidad)
SELECT 1, 'Argentina' UNION
SELECT 2, 'Brasilera' UNION
SELECT 3, 'Uruguaya';

INSERT INTO provincias (IdProvincia, Provincia)
SELECT 1, 'Buenos Aires' UNION
SELECT 2, 'Santa Fe' UNION
SELECT 3, 'Corrientes' UNION
SELECT 4, 'Cordoba' UNION
SELECT 5, 'La Rioja' UNION
SELECT 6, 'Entre Rios';

INSERT INTO localidades (IdLocalidad, IdProvincia, Localidad)
SELECT 1,1,'Pacheco' UNION
SELECT 2,1,'Pilar' UNION
SELECT 3,1,'Tigre' UNION
SELECT 4,1,'San Isidro' UNION
SELECT 5,1,'Martinez';

INSERT INTO usuarios (IdUsuario, EsAdmin, Usuario, Contrasenia, Nombre, Apellido, Sexo, FechaNacimiento, Calle, Altura, Email, NroCuil, NroDni, Estado, CantidadCuentas, IdNacionalidad, IdLocalidad)
SELECT 1, 1, 'admin', 'admin', 'admin','admin', 'admin', '14/11/2022', 'admin', 1000, 'admin@gmail.com', '20439131080', '43913108', 1,1,1,1 UNION
SELECT 2, 0, 'gaspar', '12345', 'Gaspar','Ortmann', 'Masculino', '11/12/2001', 'Chubut', 1262, 'gasparortmann@gmail.com', '20430000000', '43000000', 1,1,1,2 UNION
SELECT 3, 0, 'lautaro', '12345', 'Lautaro','Roa', 'Masculino', '14/11/2022', 'Calle', 1000, 'lautaro@gmail.com', '20432222220', '43222222', 1,1,1,1 UNION
SELECT 4, 0, 'malena', '12345', 'Malena','Constancio', 'Femenino', '14/11/2022', 'Calle', 1000, 'malena@gmail.com', '20433333330', '43333333', 1,1,1,1 UNION
SELECT 5, 0, 'santi', '12345', 'Santiago','Britos', 'Masculino', '14/11/2022', 'Calle', 1000, 'santiago@gmail.com', '20434444440', '43444444', 1,1,1,1 UNION
SELECT 6, 0, 'agus', '12345', 'Agustin','Rojas', 'Masculino', '14/11/2022', 'Calle', 1000, 'agustin@gmail.com', '20435555550', '43555555', 1,1,1,1;

INSERT INTO telefonos (NroTelefono, IdUsuario, Estado)
SELECT '1168082459',2,1 UNION
SELECT '1148111117',3,1 UNION
SELECT '1149512322',4,1 UNION
SELECT '1142477949',5,1 UNION
SELECT '1147600660',6,1;

INSERT INTO tipos_cuenta (IdTipoCuenta, TipoCuenta)
SELECT 1, 'Cuenta Corriente' UNION
SELECT 2, 'Caja de Ahorro';

INSERT INTO cuentas (IdCuenta, IdUsuario, IdTipoCuenta, CBU, Saldo, FechaAlta, Estado)
SELECT 1,2,1,'83609888111000445766', 20000, '14/11/2022', 1 UNION
SELECT 2,3,1,'53406442111000416747', 30000, '17/10/2022', 1 UNION
SELECT 3,4,2,'17435149111000329362', 40000, '08/10/2022', 1 UNION
SELECT 4,5,1,'45680069111000732191', 25000, '11/11/2022', 1 UNION
SELECT 5,6,1,'59089117111000734217', 25000, '20/11/2022', 1 UNION
SELECT 6,2,2,'10578207111000679567', 20000, '21/09/2022', 1 UNION
SELECT 7,3,1,'95379281111000789519', 30000, '22/11/2022', 1 UNION
SELECT 8,4,2,'17435149111000329362', 40000, '19/10/2022', 1 UNION
SELECT 9,5,1,'19956475111000235074', 25000, '04/11/2022', 1 UNION
SELECT 10,6,2,'14986763111000296305', 25000, '10/10/2022', 1 UNION
SELECT 11,2,1,'90443183111000884080', 20000, '14/11/2022', 1 UNION
SELECT 12,3,2,'47727281111000149377', 30000, '21/10/2022', 1 UNION
SELECT 13,4,1,'12058048111000484647', 40000, '14/10/2022', 1 UNION
SELECT 14,5,2,'24709022111000580989', 25000, '11/11/2022', 1 UNION
SELECT 15,6,1,'61312289111000994657', 25000, '22/10/2022', 1;

INSERT INTO tipos_movimiento (IdTipoMovimiento, TipoMovimiento)
SELECT 1, 'Alta de cuenta' UNION
SELECT 2, 'Alta de préstamo' UNION
SELECT 3, 'Pago de préstamo' UNION
SELECT 4, 'Transferencia';

INSERT INTO movimientos (IdMovimiento, IdTipoMovimiento, IdCuenta, Fecha, Importe, Detalle)
SELECT 1,1,1,'14/11/2022',20000,'Alta de cuenta' UNION
SELECT 2,1,2,'17/10/2022',30000,'Alta de cuenta' UNION
SELECT 3,1,3,'08/10/2022',40000,'Alta de cuenta' UNION
SELECT 4,1,4,'11/11/2022',25000,'Alta de cuenta' UNION
SELECT 5,1,5,'20/11/2022',25000,'Alta de cuenta' UNION
SELECT 6,1,6,'21/09/2022',20000,'Alta de cuenta' UNION
SELECT 7,1,7,'22/11/2022',30000,'Alta de cuenta' UNION
SELECT 8,1,8,'19/10/2022',40000,'Alta de cuenta' UNION
SELECT 9,1,9,'04/11/2022',25000,'Alta de cuenta' UNION
SELECT 10,1,10,'10/10/2022',25000,'Alta de cuenta' UNION
SELECT 11,1,11,'14/11/2022',20000,'Alta de cuenta' UNION
SELECT 12,1,12,'21/10/2022',30000,'Alta de cuenta' UNION
SELECT 13,1,13,'14/10/2022',40000,'Alta de cuenta' UNION
SELECT 14,1,14,'11/11/2022',25000,'Alta de cuenta' UNION
SELECT 15,1,15,'22/10/2022',25000,'Alta de cuenta' UNION
SELECT 16,4,1,'23/11/2022',1000,'Transferencia' UNION
SELECT 17,4,2,'23/11/2022',2000,'Transferencia' UNION
SELECT 18,4,3,'23/11/2022',500,'Transferencia' UNION
SELECT 19,4,4,'23/11/2022',4000,'Transferencia' UNION
SELECT 20,4,5,'23/11/2022',1800,'Transferencia' UNION
SELECT 21,4,6,'23/11/2022',5000,'Transferencia' UNION
SELECT 22,4,7,'23/11/2022',4000,'Transferencia' UNION
SELECT 23,4,8,'23/11/2022',700,'Transferencia' UNION
SELECT 24,4,9,'23/11/2022',2000,'Transferencia' UNION
SELECT 25,4,10,'23/11/2022',1000,'Transferencia';

INSERT INTO prestamos (IdPrestamo, IdUsuario, MontoPrestamo, ImporteCuotaFija, CantidadCuotas, IdCuenta, FechaAlta,Estado)
SELECT 1,2,20000,3083.3333,12,1,'23/11/2021','Pendiente' UNION
SELECT 2,3,10000,1541.6666,12,2,'02/10/2021','Pendiente' UNION
SELECT 3,4,10000,1541.6666,12,3,'04/09/2021','Pendiente' UNION
SELECT 4,5,20000,3083.3333,12,4,'14/02/2021','Pendiente' UNION
SELECT 5,6,30000,3375,24,5,'15/07/2021','Pendiente' UNION
SELECT 6,2,20000,3083.3333,12,6,'14/03/2021','Pendiente' UNION
SELECT 7,3,50000,4583.3335,48,7,'17/11/2021','Pendiente' UNION
SELECT 8,4,20000,3083.3333,12,8,'06/05/2021','Pendiente' UNION
SELECT 9,5,20000,3083.3333,12,9,'14/07/2021','Pendiente' UNION
SELECT 10,6,50000,4583.3335,48,10,'14/11/2021','Pendiente' UNION
SELECT 11,2,20000,3083.3333,12,11,'07/07/2021','Pendiente' UNION
SELECT 12,3,20000,3083.3333,12,12,'14/05/2021','Pendiente' UNION
SELECT 13,4,40000,930.5557,36,13,'18/03/2021','Pendiente' UNION
SELECT 14,5,20000,3083.3333,12,14,'14/11/2021','Pendiente' UNION
SELECT 15,6,30000,3375,24,15,'19/05/2021','Pendiente';

CALL SP_autorizarPrestamo(1,'23/11/2021');
CALL SP_autorizarPrestamo(2,'23/11/2022');
CALL SP_autorizarPrestamo(3,'23/11/2022');
CALL SP_autorizarPrestamo(4,'23/11/2022');
CALL SP_autorizarPrestamo(5,'23/11/2022');
CALL SP_autorizarPrestamo(6,'23/11/2022');
CALL SP_autorizarPrestamo(7,'23/11/2022');
CALL SP_autorizarPrestamo(8,'23/11/2022');
CALL SP_autorizarPrestamo(9,'23/11/2022');
CALL SP_autorizarPrestamo(10,'23/11/2022');
CALL SP_autorizarPrestamo(11,'23/11/2022');
CALL SP_autorizarPrestamo(12,'23/11/2022');
CALL SP_autorizarPrestamo(13,'23/11/2022');
CALL SP_autorizarPrestamo(14,'23/11/2022');
CALL SP_autorizarPrestamo(15,'23/11/2022');
