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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteCuentasDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;

@RestController
public class CuentasController {

	@Autowired
	CuentaService serviceCuentas;

	@Autowired
	ClienteService serviceClientes;
	
	@Autowired
	MovimientoService serviceMovimiento;

	@GetMapping("cuentas")
	@ResponseBody
	public ResponseEntity<List<ClienteCuentasDto>> findClientesCompletos() {
		return ResponseEntity.ok(serviceClientes.findClientesCompletos());
	}

	@GetMapping("cuentas/deudoras")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findAllDeudoras() {
		return ResponseEntity.ok(serviceCuentas.findAllDeudoras());
	}

	@GetMapping("cuentas/{id}")
	@ResponseBody
	public ResponseEntity<CuentaDto> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findCuentaById(id));
	}

	@PostMapping("cuentas")
	public ResponseEntity<String> addCliente(@RequestBody CuentaDto cuenta) {
		serviceCuentas.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("cuentas/{id}")
	public ResponseEntity<String> modifyUsuario(@PathVariable Integer id, @RequestBody CuentaDto cuenta) {
		serviceCuentas.updateAliasCuenta(id, cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
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

	@GetMapping("cuentas/{id}/prestamosVivos") // Devuelve los préstamos amortizados de la cuenta. (incluirán las
												// amortizaciones planificadas)
	@ResponseBody
	public ResponseEntity<CuentaDto> findPrestamosAmortizadosCuenta(@PathVariable Integer id) {
		return null; // ResponseEntity.ok(service.findCuentasByIdUsuario(id));
	}

	@PostMapping("cuentas/{id}/prestamos")
	public ResponseEntity<String> addPrestamo(@RequestBody CuentaDto cuenta) {
		// service.crearCuenta(cuenta);
		return null;
	}

	@PostMapping("cuentas/{id}/ingresos")
	public ResponseEntity<String> addIngreso(@RequestBody CuentaDto cuenta) {
		// service.crearCuenta(cuenta);
		return null;
	}

	@PostMapping("cuentas/{id}/pagos")
	public ResponseEntity<String> addPago(@RequestBody CuentaDto cuenta) {
		// service.crearCuenta(cuenta);
		return null;
	}

	@PostMapping("cuentas/ejecutarAmortizacionesDiarias") // Funcionalidad encargada de ejecutar las amortizaciones en
															// caso de cuya fecha coincida con la del sistema, el
															// funcionamiento se explica en detalle en la parte
															// superior.
	public ResponseEntity<String> ejecutarAmortizacionesDiarias(@RequestBody CuentaDto cuenta) {
		// service.crearCuenta(cuenta);
		return null;
	}

}
