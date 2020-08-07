package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.PrestamoClienteDto;
import es.eoi.mundobancario.dto.PrestamoCuentaClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository repository;

	@Override
	public Prestamo creaPrestamo(PrestamoDto prestamo, Cuenta cuenta) {
		Prestamo pres = Util.convertToPrestamo(prestamo);
		pres.setCuenta(cuenta);
		pres.setFecha(Calendar.getInstance().getTime());
		List<Amortizacion> amorList = new ArrayList<Amortizacion>();

		Calendar assignableCalendar = Calendar.getInstance();

		for (int i = 0; i < prestamo.getPlazos(); i++) {
			Amortizacion amor = new Amortizacion();
			amor.setImporte(pres.getImporte() / prestamo.getPlazos());

			assignableCalendar.add(Calendar.MONTH, 1);
			amor.setFecha(assignableCalendar.getTime());

			amor.setPrestamo(pres);
			amorList.add(amor);
		}

		pres.setAmortizaciones(amorList);

		return repository.save(pres);
	}

	@Override
	public PrestamoClienteDto findPrestamoCliente(Integer idPrestamo) {
		Prestamo pres = repository.findById(idPrestamo).get();

		return Util.convertToPrestamoClienteDto(pres);
	}

	private boolean prestamoAmortizado(Prestamo pres) {
		boolean porPagar = false;
		for (Amortizacion am : pres.getAmortizaciones()) {
			if (am.getFecha().after(Calendar.getInstance().getTime())) {
				porPagar = true;
			}
		}

		return !porPagar;
	}

	@Override
	public List<PrestamoCuentaClienteDto> findAllPrestamosVivos() {

		List<PrestamoCuentaClienteDto> prestamosVivos = new ArrayList<PrestamoCuentaClienteDto>();

		for (Prestamo prestamo : repository.findAll()) {
			if (!prestamoAmortizado(prestamo)) {
				prestamosVivos.add(Util.convertToPrestamoCuentaClienteDto(prestamo));
			}
		}

		return prestamosVivos;
	}

	@Override
	public List<PrestamoCuentaClienteDto> findAllPrestamosAmortizados() {
		List<PrestamoCuentaClienteDto> dto = new ArrayList<PrestamoCuentaClienteDto>();

		for (Prestamo prestamo : repository.findAll()) {
			if (prestamoAmortizado(prestamo)) {
				dto.add(Util.convertToPrestamoCuentaClienteDto(prestamo));
			}
		}

		return dto;
	}

}
