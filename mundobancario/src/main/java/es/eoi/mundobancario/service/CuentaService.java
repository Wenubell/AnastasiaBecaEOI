package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface CuentaService {

	public List<CuentaDto> findAllDeudoras();

	public CuentaDto findCuentaDtoById(Integer id);
	
	public Cuenta findCuentaById(Integer id);

	public void crearCuenta(CuentaDto cuenta);

	public void updateAliasCuenta(Integer id, CuentaDto cuenta);

	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosVivosByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosAmortizadosByIdCuenta(Integer id);

	public void addPrestamo(Integer id, Prestamo pres);

	public void crearIngreso(Integer id, Double importe);

	public void crearPago(Integer id, Double importe);

}
