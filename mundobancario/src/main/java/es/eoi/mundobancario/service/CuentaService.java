package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaSolaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface CuentaService {

	public List<CuentaDto> findAllDeudoras();

	public CuentaDto findCuentaDtoById(Integer id);
	
	public Cuenta findCuentaById(Integer id);

	public Cuenta crearCuenta(CuentaDto cuentaDto, Cliente cliente);

	public void updateAliasCuenta(Integer id, CuentaSolaDto cuenta);

	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosByIdCuenta(Integer id);
	
	// ---------

	public List<PrestamoDto> findPrestamosVivosByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosAmortizadosByIdCuenta(Integer id);
	
	// ---------

	public void addPrestamo(Integer id, Prestamo pres);

	public Cuenta crearIngreso(Integer id, Double importe);

	public Cuenta crearPago(Integer id, Double importe);

	public List<Amortizacion> ejecutarAmortizacionesDiarias();

}
