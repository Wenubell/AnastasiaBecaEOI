package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;

public interface CuentaService {

	List<CuentaDto> findAll(); //Devuelve un listado con todas las cuentas (Toda la información y datos del cliente).
	
	List<CuentaDto> findAllDeudoras(); //Devuelve un listado de las cuentas con saldo negativo (Toda la información y datos del cliente).

	CuentaDto findCuentaById(Integer id); //Devuelve la cuenta solicitada (Toda la información y datos del cliente).

	void crearCuenta(CuentaDto cuenta); //Añadimos una nueva cuenta.

	void updateAliasCuenta(CuentaDto cuenta); //Modifica el campo alias de la cuenta solicitada

}
