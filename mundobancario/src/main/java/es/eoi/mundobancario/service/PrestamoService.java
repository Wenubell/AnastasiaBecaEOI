package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.PrestamoClienteDto;
import es.eoi.mundobancario.dto.PrestamoCuentaClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	public Prestamo creaPrestamo(PrestamoDto prestamo, Cuenta cuenta);

	public PrestamoClienteDto findPrestamoCliente(Integer idPrestamo);

	public List<PrestamoCuentaClienteDto> findAllPrestamosVivos();

	public List<PrestamoCuentaClienteDto> findAllPrestamosAmortizados();

}
