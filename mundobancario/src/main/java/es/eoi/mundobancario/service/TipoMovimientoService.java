package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.TipoMovimiento;

public interface TipoMovimientoService {

	TipoMovimiento findTipoByNombre(String nombre);

}
