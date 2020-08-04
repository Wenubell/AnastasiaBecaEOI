package es.eoi.mundobancario.service;

import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	public Prestamo creaPrestamo(PrestamoDto prestamo, Cuenta cuenta);

}
