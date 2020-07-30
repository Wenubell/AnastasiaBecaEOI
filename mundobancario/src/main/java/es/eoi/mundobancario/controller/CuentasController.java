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

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.service.CuentaService;

@RestController
public class CuentasController {
	
	@Autowired
	CuentaService service;
	
	@GetMapping("cuentas")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("cuentas/deudoras")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findAllDeudoras() {
		return ResponseEntity.ok(service.findAllDeudoras());
	}
	
	@GetMapping("cuentas/{id}")
	@ResponseBody
	public ResponseEntity<CuentaDto> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findCuentaById(id));
	}
	
	@PostMapping("cuentas")
	public ResponseEntity<String> addCliente(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@PutMapping("cuentas/{id}")
	public ResponseEntity<String> modifyUsuario(@RequestBody CuentaDto cuenta) {
		//service.updateAliasCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("cuentas/{id}/movimientos") //Devuelve los movimientos de la cuenta solicitada. (incluirá el tipo de movimiento)
	@ResponseBody
	public ResponseEntity<CuentaDto> findMovimientosCuenta(@PathVariable Integer id) {
		return null; //ResponseEntity.ok(service.findCuentasByIdUsuario(id));
	}
	
	@GetMapping("cuentas/{id}/prestamos") //Devuelve los préstamos de la cuenta. (incluirán las amortizaciones planificadas)
	@ResponseBody
	public ResponseEntity<CuentaDto> findPrestamosCuenta(@PathVariable Integer id) {
		return null; //ResponseEntity.ok(service.findCuentasByIdUsuario(id));
	}
	
	@GetMapping("cuentas/{id}/prestamosVivos") //Devuelve los préstamos amortizados de la cuenta. (incluirán las amortizaciones planificadas)
	@ResponseBody
	public ResponseEntity<CuentaDto> findPrestamosAmortizadosCuenta(@PathVariable Integer id) {
		return null; //ResponseEntity.ok(service.findCuentasByIdUsuario(id));
	}
	
	@PostMapping("cuentas/{id}/prestamos")
	public ResponseEntity<String> addPrestamo(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	@PostMapping("cuentas/{id}/ingresos")
	public ResponseEntity<String> addIngreso(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	@PostMapping("cuentas/{id}/pagos")
	public ResponseEntity<String> addPago(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	@PostMapping("cuentas/ejecutarAmortizacionesDiarias") //Funcionalidad encargada de ejecutar las amortizaciones en caso de cuya fecha coincida con la del sistema, el funcionamiento se explica en detalle en la parte superior.
	public ResponseEntity<String> ejecutarAmortizacionesDiarias(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
