package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.PrestamoDto;
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
		return repository.save(pres);
	}

}
