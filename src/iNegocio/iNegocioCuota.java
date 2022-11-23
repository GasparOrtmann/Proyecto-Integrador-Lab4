package iNegocio;

import Entidades.Cuota;

public interface iNegocioCuota {


	Boolean pagarCuota(Cuota cuota,String fechaPago);
}
