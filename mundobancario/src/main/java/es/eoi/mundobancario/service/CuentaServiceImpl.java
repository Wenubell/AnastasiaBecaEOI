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
	public CuentaDto findCuentaDtoById(Integer id) {
		Cuenta cuenta = repository.findById(id).get();

		CuentaDto cuentaDto = Util.convertToCuentaDto(cuenta);

		return cuentaDto;
	}

	@Override
	public Cuenta findCuentaById(Integer id) {
		return repository.findById(id).get();
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

	@Override
	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer id) {
		Cuenta cuenta = repository.findById(id).get();
		List<MovimientoDto> movDto = new ArrayList<MovimientoDto>();

		for (Movimiento mov : cuenta.getMovimientos()) {
			movDto.add(Util.convertToMovimientoDto(mov));
		}

		return movDto;
	}

	@Override
	public List<PrestamoDto> findPrestamosByIdCuenta(Integer id) {
		Cuenta cuenta = repository.findById(id).get();

		List<PrestamoDto> presDto = new ArrayList<PrestamoDto>();

		for (Prestamo pres : cuenta.getPrestamos()) {
			presDto.add(Util.convertToPrestamoDto(pres));
		}

		return presDto;
	}

	@Override
	public List<PrestamoDto> findPrestamosAmortizadosByIdCuenta(Integer id) {
		// TODO
		return null;
	}

	@Override
	public List<PrestamoDto> findPrestamosVivosByIdCuenta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPrestamo(Integer id, Prestamo pres) {
		Cuenta cuenta = repository.findById(id).get();
		List<Prestamo> prestamos = cuenta.getPrestamos();
		prestamos.add(pres);
		Double saldo = cuenta.getSaldo();
		saldo += pres.getImporte();
		cuenta.setSaldo(saldo);
		cuenta.setPrestamos(prestamos);
		repository.save(cuenta);
	}

	@Override
	public void crearIngreso(Integer id, Double importe) {
		Cuenta cuenta = repository.findById(id).get();

		cuenta.setSaldo(cuenta.getSaldo()+importe);

		repository.save(cuenta);
	}

	@Override
	public void crearPago(Integer id, Double importe) {
		Cuenta cuenta = repository.findById(id).get();

		if(cuenta.getSaldo()>0) {
			cuenta.setSaldo(cuenta.getSaldo()-importe);
			repository.save(cuenta);
		}
		else {
			//TODO
			//throw exception ...
		}
	}

}
