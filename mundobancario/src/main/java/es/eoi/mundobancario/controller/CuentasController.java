package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaSolaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.TiposMovimiento;
import es.eoi.mundobancario.service.AmortizacionService;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;
import es.eoi.mundobancario.service.TipoMovimientoService;

@RestController
public class CuentasController {

	@Autowired
	CuentaService serviceCuentas;

	@Autowired
	ClienteService serviceClientes;

	@Autowired
	PrestamoService servicePrestamo;

	@Autowired
	AmortizacionService serviceAmortizacion;

	@Autowired
	TipoMovimientoService serviceTipoMovimiento;

	@Autowired
	MovimientoService serviceMovimiento;

	@GetMapping("cuentas")
	@ResponseBody
	public ResponseEntity<List<ClienteCuentasSimpleDto>> findClientesCompletos() {
		return ResponseEntity.ok(serviceClientes.findClientesCompletos());
	}

	@GetMapping("cuentas/deudoras")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findAllDeudoras() {
		return ResponseEntity.ok(serviceCuentas.findAllDeudoras());
	}

	@GetMapping("cuentas/{id}")
	@ResponseBody
	public ResponseEntity<CuentaDto> findClienteByIdCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findCuentaDtoById(id));
	}

	@PostMapping("cuentas")
	public ResponseEntity<String> addCuentaCliente(@RequestBody CuentaDto cuenta) {
		Cliente cliente = serviceClientes.findClienteByUsuario(cuenta.getCliente().getUsuario());
		serviceCuentas.crearCuenta(cuenta, cliente);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("cuentas/{id}")
	public ResponseEntity<String> modifyAliasCuenta(@PathVariable Integer id, @RequestBody CuentaSolaDto cuenta) {
		if (id == cuenta.getNum_cuenta()) {
			serviceCuentas.updateAliasCuenta(id, cuenta);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	@GetMapping("cuentas/{id}/movimientos")
	@ResponseBody
	public ResponseEntity<List<MovimientoDto>> findMovimientosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findMovimientosCuentaByIdCuenta(id));
	}

	@GetMapping("cuentas/{id}/prestamos")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosByIdCuenta(id));
	}

	@GetMapping("cuentas/{id}/prestamosVivos")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosVivosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosVivosByIdCuenta(id));
	}

	@GetMapping("cuentas/{id}/prestamosAmortizados")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosAmortizadosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosAmortizadosByIdCuenta(id));
	}

	@PostMapping("cuentas/{id}/prestamos")
	public ResponseEntity<String> addPrestamo(@PathVariable Integer id, @RequestBody PrestamoDto prestamo) {
		Cuenta cuenta = serviceCuentas.findCuentaById(id);
		boolean sinPrestamo = true;
		for (Cuenta c : cuenta.getCliente().getCuantas()) {
			if (!serviceCuentas.findPrestamosVivosByIdCuenta(c.getNum_cuenta()).isEmpty()) {
				sinPrestamo = false;
			}
		}
		if (sinPrestamo) {
			Prestamo pres = servicePrestamo.creaPrestamo(prestamo, cuenta);

			serviceCuentas.addPrestamo(id, pres);
			serviceAmortizacion.addAmortizacion(pres);
			TipoMovimiento tipo = serviceTipoMovimiento.findTipoByNombre(TiposMovimiento.PRESTAMO.toString());
			serviceMovimiento.addMovimiento(pres.getCuenta(), pres.getImporte(), tipo);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	@PostMapping("cuentas/{id}/ingresos")
	public ResponseEntity<String> addIngreso(@PathVariable Integer id, @RequestParam Double importe) {
		Cuenta c = serviceCuentas.crearIngreso(id, importe);
		TipoMovimiento tipo = serviceTipoMovimiento.findTipoByNombre(TiposMovimiento.INGRESO.toString());
		serviceMovimiento.addMovimiento(c, importe, tipo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("cuentas/{id}/pagos")
	public ResponseEntity<String> addPago(@PathVariable Integer id, @RequestParam Double importe) {
		Cuenta c = serviceCuentas.crearPago(id, importe);
		TipoMovimiento tipo = serviceTipoMovimiento.findTipoByNombre(TiposMovimiento.PAGO.toString());
		serviceMovimiento.addMovimiento(c, importe, tipo);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("cuentas/ejecutarAmortizacionesDiarias")
	public ResponseEntity<String> ejecutarAmortizacionesDiarias() {
		List<Amortizacion> amortizaciones = serviceCuentas.ejecutarAmortizacionesDiarias();
		TipoMovimiento tipo = serviceTipoMovimiento.findTipoByNombre(TiposMovimiento.AMORTIZACION.toString());
		TipoMovimiento tipoInteres = serviceTipoMovimiento.findTipoByNombre(TiposMovimiento.INTERES.toString());
		serviceMovimiento.addMovimientoAmortizaciones(amortizaciones, tipo);
		serviceMovimiento.addInteresAmortizacion(amortizaciones, tipoInteres);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

}
