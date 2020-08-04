package es.eoi.mundobancario.util;

import java.util.ArrayList;
import java.util.List;

import es.eoi.mundobancario.dto.AmortizacionDto;
import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClientePassDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;

public class Util {
	public static ClientePassDto convertToClientePassDto(Cliente cliente) {
		ClientePassDto dto = new ClientePassDto();
		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		dto.setPass(cliente.getPass());
		dto.setUsuario(cliente.getUsuario());
		return dto;
	}

	public static ClienteDto convertToClienteDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		dto.setUsuario(cliente.getUsuario());
		return dto;
	}

	public static Cliente convertToCliente(ClientePassDto dto) {
		Cliente cliente = new Cliente();
		cliente.setEmail(dto.getEmail());
		cliente.setNombre(dto.getNombre());
		cliente.setPass(dto.getPass());
		cliente.setUsuario(dto.getUsuario());
		return cliente;
	}

	public static Cliente convertToClienteSimple(ClienteDto dto) {
		Cliente cliente = new Cliente();
		cliente.setEmail(dto.getEmail());
		cliente.setNombre(dto.getNombre());
		cliente.setUsuario(dto.getUsuario());
		return cliente;
	}

	public static CuentaDto convertToCuentaDto(Cuenta c) {
		CuentaDto dto = new CuentaDto();

		dto.setNum_cuenta(c.getNum_cuenta());
		dto.setAlias(c.getAlias());
		dto.setSaldo(c.getSaldo());

		dto.setCliente(convertToClienteDto(c.getCliente()));

		return dto;
	}

	public static Cuenta convertToCuenta(CuentaDto dto) {
		Cuenta cuenta = new Cuenta();

		cuenta.setNum_cuenta(dto.getNum_cuenta());
		cuenta.setAlias(dto.getAlias());
		cuenta.setSaldo(dto.getSaldo());

		return cuenta;
	}
	

	public static ClienteCuentasSimpleDto convertToClienteCuentasSimple(Cliente cliente) {
		ClienteCuentasSimpleDto dto = new ClienteCuentasSimpleDto();
		List<CuentaDto> cuentasDto = new ArrayList<CuentaDto>();

		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		dto.setUsuario(cliente.getUsuario());

		for (Cuenta cuenta : cliente.getCuantas()) {
			cuentasDto.add(convertToCuentaDto(cuenta));
		}
		dto.setCuantas(cuentasDto);

		return dto;
	}

	public static TipoMovimientoDto convertToTipoMovimientoDto(TipoMovimiento tipo) {
		TipoMovimientoDto dto = new TipoMovimientoDto();

		dto.setTipo(tipo.getTipo());

		return dto;
	}

	public static MovimientoDto convertToMovimientoDto(Movimiento mov) {
		MovimientoDto dto = new MovimientoDto();
		dto.setDescripcion(mov.getDescripcion());
		dto.setFecha(mov.getFecha());
		dto.setImporte(mov.getImporte());
		dto.setTipoMovimiento(convertToTipoMovimientoDto(mov.getTipoMovimiento()));

		return dto;
	}

	public static AmortizacionDto convertToAmortizacionDto(Amortizacion am) {
		AmortizacionDto dto = new AmortizacionDto();
		dto.setFecha(am.getFecha());
		dto.setImporte(am.getImporte());
		return dto;
	}

	public static PrestamoDto convertToPrestamoDto(Prestamo pres) {
		PrestamoDto dto = new PrestamoDto();
		List<AmortizacionDto> amorDto = new ArrayList<AmortizacionDto>();

		dto.setDescripcion(pres.getDescripcion());
		dto.setFecha(pres.getFecha());
		dto.setImporte(pres.getImporte());
		dto.setPlazos(pres.getPlazos());

		for (Amortizacion amortizacion : pres.getAmortizaciones()) {
			amorDto.add(convertToAmortizacionDto(amortizacion));
		}

		dto.setAmortizaciones(amorDto);

		return dto;
	}

	public static Prestamo convertToPrestamo(PrestamoDto dto) {
		Prestamo pres = new Prestamo();
		// pres.setAmortizaciones(dto.getAmortizaciones());
		pres.setDescripcion(dto.getDescripcion());
		pres.setFecha(dto.getFecha());
		pres.setImporte(dto.getImporte());
		pres.setPlazos(dto.getPlazos());

		return pres;
	}
}
