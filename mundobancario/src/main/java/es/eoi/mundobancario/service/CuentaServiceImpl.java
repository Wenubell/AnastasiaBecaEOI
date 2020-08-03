package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	CuentaRepository repository;

	@Override
	public List<CuentaDto> findAllDeudoras() {
		List<Cuenta> deudoras = repository.findDeudoras();
		List<CuentaDto> deudorasDto = new ArrayList<CuentaDto>();

		for (Cuenta cuenta : deudoras) {
			deudorasDto.add(Util.convertToCuentaDto(cuenta));
		}

		return deudorasDto;
	}

	@Override
	public CuentaDto findCuentaById(Integer id) {
		Cuenta cuenta = repository.findById(id).get();

		CuentaDto cuentaDto = Util.convertToCuentaDto(cuenta);

		return cuentaDto;
	}

	@Override
	public void crearCuenta(CuentaDto cuenta) {
		repository.save(Util.convertToCuenta(cuenta));
	}

	@Override
	public void updateAliasCuenta(Integer id, CuentaDto dto) {
		Cuenta cuenta = repository.findById(id).get();
		if (cuenta.getNum_cuenta().equals(dto.getNum_cuenta())) {
			cuenta.setAlias(dto.getAlias());
			repository.save(cuenta);
		}
	}

	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer id) {
		Cuenta cuenta = repository.findById(id).get();
		List<MovimientoDto> movDto = new ArrayList<MovimientoDto>();
		
		for (Movimiento mov : cuenta.getMovimientos()) {
			movDto.add(Util.convertToMovimientoDto(mov));
		}
		
		return movDto;
	}
	
	public List<PrestamoDto> findPrestamosByIdCuenta(Integer id) {
		Cuenta cuenta = repository.findById(id).get();
		
		List<PrestamoDto> presDto = new ArrayList<PrestamoDto>();
		
		for (Prestamo pres : cuenta.getPrestamos()) {
			presDto.add(Util.convertToPrestamoDto(pres));
		}
		
		return presDto;
	}

}

















