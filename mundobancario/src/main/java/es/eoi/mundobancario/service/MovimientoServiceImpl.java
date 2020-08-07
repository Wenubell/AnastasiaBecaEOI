package es.eoi.mundobancario.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository repository;

	@Override
	public void addMovimiento(Cuenta c, Double importe, TipoMovimiento tipo) {
		Movimiento mov = new Movimiento();

		mov.setCuenta(c);
		mov.setDescripcion(tipo.getTipo());
		mov.setFecha(Calendar.getInstance().getTime());
		mov.setImporte(importe);
		mov.setTipoMovimiento(tipo);

		repository.save(mov);

	}

	@Override
	public void addMovimientoAmortizaciones(List<Amortizacion> amortizaciones, TipoMovimiento tipo) {
		
		for (Amortizacion amortizacion : amortizaciones) {
			addMovimiento(amortizacion.getPrestamo().getCuenta(), amortizacion.getImporte(), tipo);
		}
		
	}

	@Override
	public void addInteresAmortizacion(List<Amortizacion> amortizaciones, TipoMovimiento tipo) {
		for (Amortizacion amortizacion : amortizaciones) {
			addMovimiento(amortizacion.getPrestamo().getCuenta(), amortizacion.getImporte()*0.2, tipo);
		}
		
	}

}















