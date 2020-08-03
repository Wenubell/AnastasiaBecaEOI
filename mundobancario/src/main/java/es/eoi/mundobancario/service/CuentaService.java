package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;

public interface CuentaService {

	public List<CuentaDto> findAllDeudoras();

	public CuentaDto findCuentaById(Integer id);

	public void crearCuenta(CuentaDto cuenta);

	public void updateAliasCuenta(Integer id, CuentaDto cuenta);

	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer id);

	public List<PrestamoDto> findPrestamosByIdCuenta(Integer id);

}
