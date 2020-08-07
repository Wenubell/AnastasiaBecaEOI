package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.TipoMovimiento;

public interface MovimientoService {

	void addMovimiento(Cuenta c, Double importe, TipoMovimiento tipo);

	void addMovimientoAmortizaciones(List<Amortizacion> amortizaciones, TipoMovimiento tipo);

	void addInteresAmortizacion(List<Amortizacion> amortizaciones, TipoMovimiento tipoInteres);

}
