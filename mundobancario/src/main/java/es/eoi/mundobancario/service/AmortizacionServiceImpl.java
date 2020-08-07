package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {
	
	@Autowired
	AmortizacionRepository repository;

	@Override
	public void addAmortizacion(Prestamo pres) {
		for (Amortizacion a : pres.getAmortizaciones()) {
			
			repository.save(a);
		}
		
	}

}
