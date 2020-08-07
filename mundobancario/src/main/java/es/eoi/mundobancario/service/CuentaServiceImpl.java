package es.eoi.mundobancario.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaSolaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
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
	public Cuenta crearCuenta(CuentaDto cuentaDto, Cliente cliente) {
		Cuenta cuenta = Util.convertToCuenta(cuentaDto);
		cuenta.setCliente(cliente);
		return repository.save(cuenta);
	}

	@Override
	public void updateAliasCuenta(Integer id, CuentaSolaDto dto) {
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
	public List<PrestamoDto> findPrestamosVivosByIdCuenta(Integer id) {
		Cuenta cuenta = repository.findById(id).get();
		List<PrestamoDto> prestamos = new ArrayList<PrestamoDto>();
		boolean porPagar = false;

		for (Prestamo prestamo : cuenta.getPrestamos()) {
			for (Amortizacion am : prestamo.getAmortizaciones()) {
				if (am.getFecha().after(Calendar.getInstance().getTime())) {
					porPagar = true;
				}
			}
			if (porPagar) {
				prestamos.add(Util.convertToPrestamoDto(prestamo));
			}
			porPagar = false;
		}

		return prestamos;
	}

	@Override
	public List<PrestamoDto> findPrestamosAmortizadosByIdCuenta(Integer id) {

		Cuenta cuenta = repository.findById(id).get();

		List<Prestamo> prestamos = cuenta.getPrestamos();
		boolean pagado = true;
		List<PrestamoDto> presDto = new ArrayList<PrestamoDto>();

		for (Prestamo prestamo : prestamos) {
			pagado = true;
			for (Amortizacion a : prestamo.getAmortizaciones()) {
				if (a.getFecha().after(Calendar.getInstance().getTime())) {
					pagado = false;
				}
			}
			if (pagado) {
				presDto.add(Util.convertToPrestamoDto(prestamo));
			}
		}

		return presDto;
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
	public Cuenta crearIngreso(Integer id, Double importe) {
		Cuenta cuenta = repository.findById(id).get();

		cuenta.setSaldo(cuenta.getSaldo() + importe);

		return repository.save(cuenta);
	}

	@Override
	public Cuenta crearPago(Integer id, Double importe) {
		Cuenta cuenta = repository.findById(id).get();

		if (cuenta.getSaldo() > 0) {
			cuenta.setSaldo(cuenta.getSaldo() - importe);
			return repository.save(cuenta);
		} else {
			// TODO
			// throw exception ...
		}
		return null;
	}

	@Override
	public List<Amortizacion> ejecutarAmortizacionesDiarias() {
		List<Cuenta> cuentas = repository.findAll();
		List<Amortizacion> amortizaciones = new ArrayList<Amortizacion>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = Calendar.getInstance().getTime();
		
		for (Cuenta cuenta : cuentas) {
			for (Prestamo prestamo : cuenta.getPrestamos()) {
				for (Amortizacion amortizacion : prestamo.getAmortizaciones()) {

					Date fecha = amortizacion.getFecha();
					
					if (sdf.format(fecha).equals(sdf.format(todayDate))) {
						Double saldo = cuenta.getSaldo();
						cuenta.setSaldo(saldo - amortizacion.getImporte()*1.2);
						amortizaciones.add(amortizacion);
					}

				}
			}

			repository.save(cuenta);

		}
		return amortizaciones;
	}

}
